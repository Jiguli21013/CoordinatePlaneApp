package com.yanchelenko.coordinateplaneapp.presentation

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.core.app.ActivityCompat

object PermissionHelper {

    const val WRITE_PERMISSION = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    const val READ_PERMISSION = android.Manifest.permission.READ_EXTERNAL_STORAGE
    const val REQUEST_CODE = 100
    private const val FILE_NAME = "graph_image.png"

    /**
     * Запрос разрешений в зависимости от версии Android.
     */
    fun requestPermissions(
        activity: Activity,
        permissions: List<String>,
        requestCode: Int,
        view: View
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val bitmap = getBitmapFromView(view = view)
            saveBitmapToScopedStorage(activity, bitmap = bitmap, fileName = FILE_NAME)
        } else {
            ActivityCompat.requestPermissions(activity, permissions.toTypedArray(), requestCode)
        }
    }

    fun saveFileToScopeStorage(
        view: View,
        onStart: () -> Unit,
        onComplete: () -> Unit
    ) {
        onStart.invoke()
        val bitmap = getBitmapFromView(view = view)
        saveBitmapToScopedStorage(
            context = view.context,
            bitmap = bitmap,
            fileName = "graph_image.png",
        )

        onComplete()
    }

    private fun saveBitmapToScopedStorage(context: Context, bitmap: Bitmap, fileName: String): Boolean {
        return try {
            val resolver = context.contentResolver
            val values = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, fileName) // Имя файла
                put(MediaStore.Images.Media.MIME_TYPE, "image/png") // Тип файла
                put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES) // Путь
            }

            val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            uri?.let {
                resolver.openOutputStream(it)?.use { outputStream ->
                    // Сохранение Bitmap в формате PNG
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                    outputStream.flush()
                }
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    private fun getBitmapFromView(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        view.draw(canvas)
        return bitmap
    }
}
