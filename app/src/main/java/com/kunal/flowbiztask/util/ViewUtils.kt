package com.kunal.flowbiztask.util

import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

fun Context.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun View.snackBar(message: String){
    Snackbar.make(this,message,Snackbar.LENGTH_LONG).also {snackbar ->
        snackbar.setAction("OK"){
            snackbar.dismiss()
        }
    }.show()
}

