package com.modo.modo.sportsapp.qr.presentation

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.base.utils.observeFlow
import com.modo.modo.sportsapp.databinding.FragmentQrBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class QrFragment : Fragment(R.layout.fragment_qr) {

    private val binding by viewBinding(FragmentQrBinding::bind)

    private val viewModel by viewModel<QrViewModel> {
        parametersOf(arguments?.getString(EVENT_ID_EXTRA))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observeData()
    }

    override fun onStart() {
        super.onStart()
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
    }

    override fun onStop() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
        super.onStop()
    }

    private fun setupView() = with(binding) {
        close.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.activityContent).navigateUp()
        }
    }

    private fun observeData() = with(binding) {
        observeFlow(viewModel.qrBitmap) {
            qrCode.setImageBitmap(it)
        }
    }

    companion object {
        const val EVENT_ID_EXTRA = "event_id_exrtra"
    }
}