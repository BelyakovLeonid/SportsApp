package com.modo.modo.sportsapp.admin.camera.presentation

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.barcode.Barcode
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.admin.camera.domain.BarcodeAnalyzer
import com.modo.modo.sportsapp.admin.camera.presentation.model.PersonUiModel
import com.modo.modo.sportsapp.admin.personfound.PersonFragment
import com.modo.modo.sportsapp.databinding.FragmentCameraBinding
import com.modo.modo.sportsapp.user.base.utils.mainExecutor
import com.modo.modo.sportsapp.user.base.utils.observeFlow
import com.modo.modo.sportsapp.user.base.utils.showToast
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean

class CameraFragment : Fragment(R.layout.fragment_camera) {

    private val binding by viewBinding(FragmentCameraBinding::bind)

    private val viewModel by viewModel<CameraViewModel>()

    private lateinit var camera: Camera

    private val cameraExecutor by lazy {
        Executors.newSingleThreadExecutor()
    }

    private val cameraPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        when {
            granted -> startCameraPreview()
            else -> showToast(R.string.denied_toast)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cameraPermission.launch(Manifest.permission.CAMERA)
        observeFlow(viewModel.personFound) {
            if (it != null) {
                binding.progress.animate()
                    .setDuration(300)
                    .alpha(1F)
                    .withEndAction {
                        binding.progress.postDelayed({ showPerson(it) }, 1300)
                    }
            } else {
                binding.progress.alpha = 0F
            }
        }
    }

    private fun showPerson(person: PersonUiModel) {
        Navigation.findNavController(requireActivity(), R.id.activityContent)
            .navigate(
                R.id.action_cameraFragment_to_personFragment,
                bundleOf(PersonFragment.PERSON_EXTRA to person)
            )
    }

    private fun startCameraPreview() {
        ProcessCameraProvider.getInstance(requireContext()).let { cameraFuture ->
            cameraFuture.addListener(onCameraReady(cameraFuture), mainExecutor)
        }
    }

    private fun onCameraReady(cameraProviderFuture: ListenableFuture<ProcessCameraProvider>) = Runnable {
        val preview = Preview.Builder().build()
        preview.setSurfaceProvider(binding.previewView.surfaceProvider)

        val imageAnalysis = ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_BLOCK_PRODUCER)
            .build()

        imageAnalysis.setAnalyzer(cameraExecutor, BarcodeAnalyzer { barcode ->
            searchBarcode(barcode)
        })

        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        runCatching {
            cameraProviderFuture.get().let { provider ->
                provider.unbindAll()
                camera = provider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis)
            }
        }
    }

    private fun searchBarcode(barcode: Barcode) {
        viewModel.onBarcodeFind(barcode)
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    companion object {
        const val EVENT_ID_EXTRA = "event_id_exrtra"
    }
}