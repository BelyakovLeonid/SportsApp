package com.modo.modo.sportsapp.admin.camera.presentation

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.barcode.Barcode
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.user.base.utils.mainExecutor
import com.modo.modo.sportsapp.user.base.utils.showToast
import com.modo.modo.sportsapp.admin.camera.domain.BarcodeAnalyzer
import com.modo.modo.sportsapp.databinding.FragmentCameraBinding
import java.util.concurrent.Executors

class CameraFragment : Fragment(R.layout.fragment_camera) {

    private val binding by viewBinding(FragmentCameraBinding::bind)

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
        Log.d("MyTag", "barcode = $barcode")
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}