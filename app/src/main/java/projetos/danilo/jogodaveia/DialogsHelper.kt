package com.example.gameplayveia

import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

fun toastLong(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun toastShort(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun alertDialogPersonal(ctx: Context, title: String, message: String, btnPositive: String?, btnNegative: String?) {

    if (btnPositive != null && btnNegative != null) {
        AlertDialog.Builder(ctx)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(btnPositive,
                 { dialog, id ->
                     toastLong(ctx, "CLICOU NO SIM")
                })
            .setNegativeButton(btnNegative, { dialog, id ->
                    toastLong(ctx, "CLICOU NO SIM")
            })
            .show()
    } else if (btnPositive != null && btnNegative == null) {
        AlertDialog.Builder(ctx)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(btnPositive, null)
            .show()
    } else {
        AlertDialog.Builder(ctx)
            .setTitle(title)
            .setMessage(message)
            .show()
    }
}
