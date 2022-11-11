package com.example.filtersdemo.interfaces

import android.graphics.Bitmap
import android.net.Uri
import com.example.filtersdemo.data.ImageFilter
import java.io.File

interface EditImageInterface {
    suspend fun loadImagePreview(uri: Uri) : Bitmap?
    suspend fun filterImages(image : Bitmap) : List<ImageFilter>
    suspend fun saveFilteredImages(bitmap: Bitmap) : Uri?

}