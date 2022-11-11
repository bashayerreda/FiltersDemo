package com.example.filtersdemo.views.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.filtersdemo.R
import com.example.filtersdemo.adapters.SavedImagesAdapter
import com.example.filtersdemo.interfaces.ImageClicked
import com.example.filtersdemo.views.viewmodels.ShowSavedImagesViewModel
import com.example.music_app.other.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_show_saved_images.*
import java.io.File

@AndroidEntryPoint
class ShowSavedImages : AppCompatActivity(),ImageClicked {

    private lateinit var savedimagesAdapter: SavedImagesAdapter
    private lateinit var rv: RecyclerView

    private val showSavedImagesViewModel: ShowSavedImagesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_saved_images)
        rv = show_rec
        savedimagesAdapter = SavedImagesAdapter(mutableListOf(), this)
        rv.adapter = savedimagesAdapter
        showSavedImagesViewModel.showDataFromFiles()
        setObservers()
        setListeners()



    }

    fun setObservers() {
        showSavedImagesViewModel.bitmapLoadedFromFile.observe(this, Observer {
            it.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        result.data.let {
                            savedimagesAdapter = SavedImagesAdapter(mutableListOf(), this)
                            rv.adapter = savedimagesAdapter
                            if (it != null) {
                                savedimagesAdapter.apiList = it

                            }

                            rv.visibility = View.VISIBLE
                            progress_Bar_in_Show_images.visibility = View.GONE

                        }
                    }
                    Status.LOADING -> {
                        progress_Bar_in_Show_images.visibility = View.VISIBLE

                    }
                    Status.ERROR -> {
                        progress_Bar_in_Show_images.visibility = View.GONE
                        it.message.toString()
                    }
                }
            }
        })


    }

    fun setListeners() {
        imgBack2.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()

        }
    }


    override fun onImageClicked(file: File) {
        val fileUri = FileProvider.getUriForFile(
            applicationContext, "${packageName}.provider", file
        )
        Intent(applicationContext, FilteredImages::class.java).also { intent ->
            intent.putExtra(EditActivity.KEY_FILTERED_IMG_URI, fileUri)
            startActivity(intent)
        }

    }
}