package com.example.filtersdemo.repositories

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import com.example.filtersdemo.interfaces.EditImageInterface
import com.example.filtersdemo.data.ImageFilter
import jp.co.cyberagent.android.gpuimage.GPUImage
import jp.co.cyberagent.android.gpuimage.filter.*
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception

class EditImageRepository(private val context : Context) : EditImageInterface {
    override suspend fun filterImages(image: Bitmap): MutableList<ImageFilter> {
        val gpuImage = GPUImage(context).apply {
            setImage(image)
        }

        val imagesFilter: ArrayList<ImageFilter> = ArrayList()
        GPUImageFilter().also { filter ->
            gpuImage.setFilter(filter)
            imagesFilter.add(
                ImageFilter(
                    name = "Normal",
                    filter = filter,
                    preview = gpuImage.bitmapWithFilterApplied

                )
            )

        }
        //Yeli
      GPUImageColorMatrixFilter(1.0f,
          floatArrayOf(
              1.0f,-0.3831f,0.3883f,0.0f,
              0.0f,1.0f,0.2f,0f,
              -0.1961f,0.0f,0.1f,0f,
              0f,0f,0f,1f
          )
      ).also { filter ->
          gpuImage.setFilter(filter)
          imagesFilter.add(
              ImageFilter(
                  name = "Yeli",
                  filter = filter,
                  preview = gpuImage.bitmapWithFilterApplied

              )
          )

      }
        //old times
        GPUImageColorMatrixFilter(1.0f,
            floatArrayOf(
                1.0f,0.05f,0.0f,0.0f,
                -0.2f,1.1f,-0.2f,0.11f,
                0.2f,0.0f,1.0f,0.0f,
                0.0f,0.0f,0.0f,1.0f
            )
        ).also { filter ->
            gpuImage.setFilter(filter)
            imagesFilter.add(
                ImageFilter(
                    name = "old times",
                    filter = filter,
                    preview = gpuImage.bitmapWithFilterApplied

                )
            )

        }
        //desert
        GPUImageColorMatrixFilter(1.0f,
            floatArrayOf(
                0.6f,0.4f,0.2f,0.05f,
                0.0f,0.08f,0.3f,0.05f,
                0.3f,0.3f,0.5f,0.08f,
                0.0f,0.0f,0.0f,1.0f
            )
        ).also { filter ->
            gpuImage.setFilter(filter)
            imagesFilter.add(
                ImageFilter(
                    name = "desert",
                    filter = filter,
                    preview = gpuImage.bitmapWithFilterApplied

                )
            )

        }
        //atom
        GPUImageColorMatrixFilter(1.0f,
            floatArrayOf(
                0.5162f,0.3799f,0.3247f,0.0f,
                0.039f,1.0f,0f,0f,
                -0.4773f,0.461f,1.0f,0f,
                0f,0f,0f,1f
            )
        ).also { filter ->
            gpuImage.setFilter(filter)
            imagesFilter.add(
                ImageFilter(
                    name = "Atom",
                    filter = filter,
                    preview = gpuImage.bitmapWithFilterApplied

                )
            )

        }
        //Classic
        GPUImageColorMatrixFilter(1.0f,
            floatArrayOf(
                0.763f,0.0f,0.2062f,0f,
                0.0f,0.9416f,0.0f,0f,
                -0.1623f,0.2614f,0.8052f,0f,
                0f,0f,0f,1f
            )
        ).also { filter ->
            gpuImage.setFilter(filter)
            imagesFilter.add(
                ImageFilter(
                    name = "Classic",
                    filter = filter,
                    preview = gpuImage.bitmapWithFilterApplied

                )
            )

        }
        //Aero
        GPUImageColorMatrixFilter(1.0f,
            floatArrayOf(
                0f,0f,1f,0f,
                1f,0f,0f,0f,
                0f,1f,0f,0f,
                0f,0f,0f,1f
            )
        ).also { filter ->
            gpuImage.setFilter(filter)
            imagesFilter.add(
                ImageFilter(
                    name = "Aero",
                    filter = filter,
                    preview = gpuImage.bitmapWithFilterApplied

                )
            )

        }
        //muli
        GPUImageColorMatrixFilter(1.0f,
            floatArrayOf(
                1.0f,0.0f,0.0f,0.0f,
                1.0f,0.0f,0.0f,0.0f,
                1.0f,0.0f,0.0f,0.0f,
                0.0f,0.0f,0.0f,1.0f
            )
        ).also { filter ->
            gpuImage.setFilter(filter)
            imagesFilter.add(
                ImageFilter(
                    name = "Muli",
                    filter = filter,
                    preview = gpuImage.bitmapWithFilterApplied

                )
            )

        }
        //Clue
        GPUImageColorMatrixFilter(1.0f,
            floatArrayOf(
                0.0f,0.0f,1.0f,0.0f,
                0.0f,0.0f,1.0f,0.0f,
                0.0f,0.0f,1.0f,0.0f,
                0.0f,0.0f,0.0f,1.0f
            )
        ).also { filter ->
            gpuImage.setFilter(filter)
            imagesFilter.add(
                ImageFilter(
                    name = "Clue",
                    filter = filter,
                    preview = gpuImage.bitmapWithFilterApplied

                )
            )

        }
        //BW
        GPUImageColorMatrixFilter(1.0f,
            floatArrayOf(
                0.0f,1.0f,0.0f,0.0f,
                0.0f,1.0f,0.0f,0.0f,
                0.0f,1.0f,0.0f,0.0f,
                0.0f,1.0f,0.0f,1.0f
            )
        ).also { filter ->
            gpuImage.setFilter(filter)
            imagesFilter.add(
                ImageFilter(
                    name = "BW",
                    filter = filter,
                    preview = gpuImage.bitmapWithFilterApplied

                )
            )

        }
        //Milk
        GPUImageColorMatrixFilter(1.0f,
            floatArrayOf(
                0.0f,1.0f,0.0f,0.0f,
                0.0f,1.0f,0.0f,0.0f,
                0.0f,0.46f,0.5f,0.0f,
                0.0f,1.0f,0.0f,1.0f
            )
        ).also { filter ->
            gpuImage.setFilter(filter)
            imagesFilter.add(
                ImageFilter(
                    name = "Milk",
                    filter = filter,
                    preview = gpuImage.bitmapWithFilterApplied

                )
            )

        }
        //Bright
        GPUImageRGBFilter(1.1f,1.3f,1.6f).also { filter ->
            gpuImage.setFilter(filter)
            imagesFilter.add(
                ImageFilter(
                    name = "Bright",
                    filter = filter,
                    preview = gpuImage.bitmapWithFilterApplied

                )
            )

        }
        //Neutron
        GPUImageColorMatrixFilter(1.0f,
            floatArrayOf(
                0f,1f,0f,0f,
                0f,1f,0f,0f,
                0f,0.6f,1f,0f,
                0f,0f,0f,1f
            )
        ).also { filter ->
            gpuImage.setFilter(filter)
            imagesFilter.add(
                ImageFilter(
                    name = "Neutron",
                    filter = filter,
                    preview = gpuImage.bitmapWithFilterApplied

                )
            )

        }
        //Wole
     GPUImageSaturationFilter(2.0f).also { filter ->
         gpuImage.setFilter(filter)
         imagesFilter.add(
             ImageFilter(
                 name = "Wole",
                 filter = filter,
                 preview = gpuImage.bitmapWithFilterApplied

             )
         )
     }
        //Solar
        GPUImageColorMatrixFilter(1.0f,
            floatArrayOf(
                1.5f,0f,0f,0f,
                0f,1f,0f,0f,
                0f,0f,1f,0f,
                0f,0f,0f,1f
            )
        ).also { filter ->
            gpuImage.setFilter(filter)
            imagesFilter.add(
                ImageFilter(
                    name = "Solar",
                    filter = filter,
                    preview = gpuImage.bitmapWithFilterApplied

                )
            )

        }
        //sepia
        GPUImageSepiaToneFilter().also { filter ->
            gpuImage.setFilter(filter)
            imagesFilter.add(
                ImageFilter(
                    name = "Sepia",
                    filter = filter,
                    preview = gpuImage.bitmapWithFilterApplied

                )
            )
        }
        //Limo
        GPUImageColorMatrixFilter(1.0f,
            floatArrayOf(
                1.0f,0.0f,0.08f,0.0f,
                0.4f,1.0f,0.0f,0.0f,
                0.0f,0.0f,1.0f,0.1f,
                0.0f,0.0f,0.0f,1.0f
            )
        ).also { filter ->
            gpuImage.setFilter(filter)
            imagesFilter.add(
                ImageFilter(
                    name = "Limo",
                    filter = filter,
                    preview = gpuImage.bitmapWithFilterApplied

                )
            )

        }
        //Hume
        GPUImageColorMatrixFilter(1.0f,
            floatArrayOf(
                1.25f,0.0f,0.2f,0.0f,
                0.0f,1.0f,0.2f,0.0f,
                0.0f,0.3f,1.0f,0.3f,
                0.0f,0.0f,0.0f,1.0f
            )
        ).also { filter ->
            gpuImage.setFilter(filter)
            imagesFilter.add(
                ImageFilter(
                    name = "Hume",
                    filter = filter,
                    preview = gpuImage.bitmapWithFilterApplied

                )
            )

        }

        //Just
        GPUImageColorMatrixFilter(0.9f,
            floatArrayOf(
                0.4f,0.6f,0.5f,0.0f,
                0.0f,0.4f,1.0f,0.0f,
                0.05f,0.1f,0.4f,0.4f,
                1.0f,1.0f,1.0f,1.0f
            )
        ).also { filter ->
            gpuImage.setFilter(filter)
            imagesFilter.add(
                ImageFilter(
                    name = "Just",
                    filter = filter,
                    preview = gpuImage.bitmapWithFilterApplied

                )
            )

        }
        //Retro
        GPUImageColorMatrixFilter(0.9f,
            floatArrayOf(
                1.0f,0.0f,0.0f,0.0f,
                0.0f,1.0f,0.2f,0.0f,
                0.1f,0.1f,1.0f,0.0f,
                1.0f,0.0f,0.0f,1.0f
            )
        ).also { filter ->
            gpuImage.setFilter(filter)
            imagesFilter.add(
                ImageFilter(
                    name = "Retro",
                    filter = filter,
                    preview = gpuImage.bitmapWithFilterApplied

                )
            )

        }
    return imagesFilter

    }



    override suspend fun loadImagePreview(uri: Uri): Bitmap? {
        openInputStream(uri)?.let { inputStream ->
            val originalBitmap = BitmapFactory.decodeStream(inputStream)
            val width = context.resources.displayMetrics.widthPixels
            val height = (originalBitmap.height * width) / originalBitmap.width
            return Bitmap.createScaledBitmap(originalBitmap, width, height, false)
        }?: return null


    }

    private fun openInputStream(uri: Uri) : InputStream? {
        return  context.contentResolver.openInputStream(uri)
    }

    override suspend fun saveFilteredImages(bitmap: Bitmap): Uri? {
          return try {
             val mediaStoragDirectory = File(
                 context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
             "saved Images"
             )
              if (!mediaStoragDirectory.exists()){
                  mediaStoragDirectory.mkdir()
              }
              val fileName = "IMG_${System.currentTimeMillis()}.jpg"
              val file = File(mediaStoragDirectory,fileName)
              saveFile(file,bitmap)
              FileProvider.getUriForFile(context,"${context.packageName}.provider",file)
          }catch (exception : Exception){
              null
          }

    }
    fun saveFile(file: File , bitmap: Bitmap){
       with(FileOutputStream(file)){
           bitmap.compress(Bitmap.CompressFormat.JPEG,100,this)
           flush()
           close()

       }
    }
}