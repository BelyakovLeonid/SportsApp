package com.modo.modo.sportsapp.qr.presentation

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.databinding.FragmentQrBinding

class QrFragment : Fragment(R.layout.fragment_qr) {

    private val binding by viewBinding(FragmentQrBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.close.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.activityContent).navigateUp()
        }
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

    private fun observeData() = with(binding) {
        val text = "Whatever you need to encode in the QR code"
        val multiFormatWriter = MultiFormatWriter()
        try {
            val bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 300, 300);
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.createBitmap(bitMatrix)
            qrCode.setImageBitmap(bitmap)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}