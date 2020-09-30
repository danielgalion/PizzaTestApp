package com.github.danielgalion.pizzatestapp.utils

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Environment
import android.widget.ImageView
import android.widget.Toast
import com.github.danielgalion.pizzatestapp.MainApplication
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

fun savePhoto(imageView: ImageView?) {
    val bitmap = (imageView?.drawable as BitmapDrawable).bitmap

    try {
        val dir = File(Environment.getExternalStorageDirectory()?.absolutePath + "/DCIM/Camera")

        if (!dir.isDirectory) dir.mkdirs()

        val fileUri = dir.absolutePath + File.separator + System.currentTimeMillis() + ".jpg"
        val outputStream = FileOutputStream(fileUri)

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.flush()
        outputStream.close()

        Toast.makeText(
            MainApplication.getInstance()?.applicationContext, "Pobrano zdjęcie",
            Toast.LENGTH_LONG
        ).show()
    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(
            MainApplication.getInstance()?.applicationContext, "Nie można pobrać zdjęcia, sprawdź uprawnienia",
            Toast.LENGTH_LONG
        ).show()
    }
}
