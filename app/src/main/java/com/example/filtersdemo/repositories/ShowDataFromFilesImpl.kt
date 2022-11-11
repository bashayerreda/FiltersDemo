package com.example.filtersdemo.repositories

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import com.example.filtersdemo.interfaces.ShowDataFromFiles
import java.io.File

class ShowDataFromFilesImpl(private val context: Context) : ShowDataFromFiles {
    override suspend fun returnDataFromFileOutPutStream(): List<Pair<File, Bitmap>>? {
           val savedImages = ArrayList<Pair<File,Bitmap>>()
         val dir = File(
             context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
             "saved Images"
         )
        dir.listFiles()?.let { data ->
            data.forEach { file ->  
                savedImages.add(Pair(file,getPreviewBitmap(file)))
            }
            return savedImages
            
        }
            ?:return null
    }
    fun getPreviewBitmap(file: File) : Bitmap{
        val originalImage = BitmapFactory.decodeFile(file.absolutePath)
        val width = 150
        val height = ((originalImage.height*width)/originalImage.width)
        return Bitmap.createScaledBitmap(originalImage,width,height,false)
    }
}