package com.modo.modo.sportsapp.user.base.utils

import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.util.concurrent.Executor

fun Fragment.showToast(textId: Int) {
    Toast.makeText(requireContext(), textId, Toast.LENGTH_SHORT).show()
}

val Fragment.mainExecutor: Executor
    get() = ContextCompat.getMainExecutor(requireContext())