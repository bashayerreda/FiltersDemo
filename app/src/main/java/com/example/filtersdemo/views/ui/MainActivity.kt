package com.example.filtersdemo.views.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.filtersdemo.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object{
        private const val REQUEST_CODE = 100
        const val IMAGE_URI_CODE = "imageUri"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setIntentToOpenGallery()
        setIntentToShowSavedImages()
    }
    fun setIntentToOpenGallery(){
        btnEditImg.setOnClickListener {
         Intent(Intent.ACTION_PICK,
         MediaStore.Images.Media.EXTERNAL_CONTENT_URI
         ).also { pickIntent ->
             pickIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
             startForResult.launch(pickIntent)
         }
        }
    }
    fun setIntentToShowSavedImages(){
        btnSaveImg.setOnClickListener {
            startActivity(Intent(applicationContext,ShowSavedImages::class.java))
        }
    }

    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intentData = result.data?.data
             Intent(this, EditActivity::class.java).also { editImagIntent ->
               editImagIntent.putExtra(IMAGE_URI_CODE, intentData)
                 startActivity(editImagIntent)
             }
    }




    }
}