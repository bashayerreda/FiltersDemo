package com.example.filtersdemo.views.ui

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.filtersdemo.views.viewmodels.EditImagesViewModel
import com.example.filtersdemo.adapters.ImagesAdapter
import com.example.filtersdemo.R
import com.example.music_app.other.Status
import dagger.hilt.android.AndroidEntryPoint
import jp.co.cyberagent.android.gpuimage.GPUImage
import kotlinx.android.synthetic.main.activity_edit.*

@AndroidEntryPoint
class EditActivity : AppCompatActivity() {
    companion object{
         const val KEY_FILTERED_IMG_URI = "filteredImageUri"
    }
    private lateinit var imagesAdapter: ImagesAdapter
    private lateinit var rv: RecyclerView
    private lateinit var gpuImages : GPUImage
    private lateinit var originalBitmap: Bitmap
    var filteredBitmaps = MutableLiveData<Bitmap>()
    private val editViewModel: EditImagesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        rv = rec
        imagesAdapter = ImagesAdapter(mutableListOf())
        rv.adapter = imagesAdapter
        prepareImageTakenFromView()
        setListeners()
        observerFromEditViewModel()
        filteredImages()

    }

    fun prepareImageTakenFromView() {
        gpuImages = GPUImage(applicationContext)
        intent.getParcelableExtra<Uri>(MainActivity.IMAGE_URI_CODE).let { img ->
            editViewModel.returnDataFromEditRepo(img!!)
        }
    }

       /* fun displayImagesFromPreview() {

            intent.getParcelableExtra<Uri>(MainActivity.IMAGE_URI_CODE).let { img ->
                val inp = img?.let {
                    contentResolver.openInputStream(it)
                }
                val bitmap = BitmapFactory.decodeStream(inp)
                img_preview.setImageBitmap(bitmap)
                img_preview.visibility = View.VISIBLE

            }
        }*/

        fun setListeners() {
            imgBack.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            img_preview.setOnLongClickListener {
                img_preview.setImageBitmap(originalBitmap)
                return@setOnLongClickListener false
            }
            img_preview.setOnClickListener {
                img_preview.setImageBitmap(filteredBitmaps.value)
            }
            imgSave.setOnClickListener {
                filteredBitmaps.value?.let { savedImages ->
                    editViewModel.saveImagesIntoFilesTakenFromViewToRepo(
                        savedImages
                    )
                }
            }
        }


        fun observerFromEditViewModel() {
            editViewModel.FilterPreview.observe(this, Observer {
                it?.let { result ->
                    when (result.status) {
                        Status.SUCCESS -> {
                            result.data.let { bitmap ->
                                //default photo when show should be normal Filter
                                originalBitmap = bitmap!!
                                filteredBitmaps.value = bitmap
                                with(originalBitmap){
                                    gpuImages.setImage(this)
                                    progressBar.visibility = View.GONE
                                    img_preview.setImageBitmap(bitmap)
                                    editViewModel.returnDataFromEditRepoAfterApplyFilters(this)
                                    img_preview.visibility = View.VISIBLE
                                }

                            }
                        }else -> Unit
                    }
                }
            })

            editViewModel.hasError.observe(this, Observer {
                it?.getContentIfNotHandled()?.let { result ->
                    when (result.status) {
                        Status.ERROR -> {
                          errorText.text =  result.message.toString()

                        }
                        else -> Unit
                    }
                }


            })
            editViewModel.FiltersList.observe(this, Observer {
                it?.let { result ->
                    when (result.status) {
                        Status.SUCCESS -> {
                            result.data.let { filters ->
                                if (filters != null) {
                                    imagesAdapter.setData(filters)
                                }
                            }
                        }   else -> Unit
                    }
                }
            })

            filteredBitmaps.observe(this, Observer {
                  img_preview.setImageBitmap(it)
            })
            editViewModel.uriCheck.observe(this, Observer {
              it.let {  result ->
                  when (result.status){
                      Status.SUCCESS -> {
                          result.data.let { uri ->
                              Intent(applicationContext, FilteredImages::class.java).also { savedIntent ->
                                  savedIntent.putExtra(KEY_FILTERED_IMG_URI, uri)
                                  startActivity(savedIntent)
                              }

                          }
                      } Status.LOADING -> {
                         img_preview.visibility = View.GONE
                          progressBar.visibility = View.VISIBLE

                      } Status.ERROR -> {
                     errorText.text =   result.message.toString()
                        progressBar.visibility = View.GONE
                      }
                  }
              }
            })
        }

       fun filteredImages(){
           imagesAdapter.setItemClickListener {
               with(it){
                   with(gpuImages){
                       setFilter(filter)
                       filteredBitmaps.value = bitmapWithFilterApplied
                   }
               }

           }

    }
    }

