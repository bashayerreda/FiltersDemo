package com.example.filtersdemo.views.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.filtersdemo.R
import kotlinx.android.synthetic.main.activity_filtered_images.*

class FilteredImages : AppCompatActivity() {
    lateinit var fileUri : Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtered_images)
        returnDataFromEditPart()
        sendPhotoToOtherApps()
    }
    fun returnDataFromEditPart(){
        intent.getParcelableExtra<Uri>(EditActivity.KEY_FILTERED_IMG_URI).let { uri ->
            fileUri = uri!!
              saving_img.setImageURI(uri)
        }

    }
    fun sendPhotoToOtherApps(){
        fab_sharing.setOnClickListener {
            Intent(
                Intent.ACTION_SEND
            ).also { intent ->
                intent.putExtra(Intent.EXTRA_STREAM, fileUri)
                    .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    .type = "image/*"
                startActivity(intent)
            }
        }




        }

    }
