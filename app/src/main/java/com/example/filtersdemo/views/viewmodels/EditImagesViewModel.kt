package com.example.filtersdemo.views.viewmodels

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filtersdemo.repositories.EditImageRepository
import com.example.filtersdemo.data.ImageFilter
import com.example.filtersdemo.utilies.Event
import com.example.music_app.other.Resource
import com.example.music_app.other.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditImagesViewModel  @Inject constructor(private val editImageReposotry: EditImageRepository) : ViewModel() {
    private val _hasError = MutableLiveData<Event<Resource<Boolean>>>()
    val hasError: LiveData<Event<Resource<Boolean>>> = _hasError

    private val _isLoading = MutableLiveData<Resource<Boolean>>()
    val isLoading: MutableLiveData<Resource<Boolean>> = _isLoading

    private val _FilterPreview = MutableLiveData<Resource<Bitmap?>>()
    val FilterPreview: MutableLiveData<Resource<Bitmap?>> = _FilterPreview

    private val _FiltersList = MutableLiveData<Resource<MutableList<ImageFilter>>>()
    val FiltersList: MutableLiveData<Resource<MutableList<ImageFilter>>> = _FiltersList

    private val _uriCheck = MutableLiveData<Resource<Uri>>()
    val uriCheck: MutableLiveData<Resource<Uri>> = _uriCheck

   /* fun returnDataFromEditRepo(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            editImageReposotry.loadImagePreview(uri)
                .onStart {
               isLoading.postValue( Resource.loading(null))
            }
                .catch {
                    _hasError.postValue(
                        Event(
                            Resource.error(
                                "Couldn't catch images from gallery.",
                                null
                            )
                        ))
                }
                .collect {
                    FilterPreview.postValue(Resource.success(it))

                    print(FilterPreview.value?.data)
                }

        }
    }*/
    fun returnDataFromEditRepo(uri: Uri) {
       viewModelScope.launch(Dispatchers.IO) {
           kotlin.runCatching {
               _isLoading.postValue(Resource(Status.LOADING,true,null))
               editImageReposotry.loadImagePreview(uri)
           }.onSuccess {
               _FilterPreview.postValue(Resource(Status.SUCCESS,it,null))
           }.onFailure {
               _hasError.postValue(
                   Event(
                       Resource.error(
                           "Couldn't catch images from gallery.",
                           null
                       )
                   )
               )
           }
       }
    }

    fun returnDataFromEditRepoAfterApplyFilters(uri: Bitmap) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _isLoading.postValue(Resource(Status.LOADING,true,null))
                editImageReposotry.filterImages(prepareImageForFilters(uri))
            }.onSuccess {
                _FiltersList.postValue(Resource(Status.SUCCESS,it,null))
            }.onFailure {
                _hasError.postValue(
                    Event(
                        Resource.error(
                            "Couldn't Apply Filters ",
                            null
                        )
                    )
                )
            }
        }
    }
    fun prepareImageForFilters(bitmap: Bitmap) : Bitmap {
       return kotlin.runCatching {
            val width = 180
            val height = bitmap.height * width / bitmap.width
            Bitmap.createScaledBitmap(bitmap, width, height, false)
        }.getOrDefault(bitmap)
    }

    fun saveImagesIntoFilesTakenFromViewToRepo(bitmap: Bitmap){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _isLoading.postValue(Resource(Status.LOADING,true,null))
                editImageReposotry.saveFilteredImages(bitmap)
            }.onSuccess {  uri ->
               _uriCheck.postValue(Resource(Status.SUCCESS,uri,null))

            }.onFailure { _hasError.postValue(
                Event(
                    Resource.error(
                        "Couldn't catch uri ",
                        null
                    )
                )
            )
            }
        }
    }
}
