package com.example.pruebaceibasoftware.utils

import android.app.Activity
import android.view.View
import com.example.pruebaceibasoftware.R
import com.example.pruebaceibasoftware.databinding.ViewLoadingBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class UtilsProyect {

    companion object {

        fun showLoading(activity: Activity, message: String): androidx.appcompat.app.AlertDialog {
            val dialog = MaterialAlertDialogBuilder(activity)
            val view: View = activity.layoutInflater.inflate(R.layout.view_loading, activity.findViewById(android.R.id.content), false)
            val binding = ViewLoadingBinding.bind(view)
            dialog.setView(view)
            binding.txtMessage.text = message
            dialog.setCancelable(false)
            return dialog.show()
        }

    }
}