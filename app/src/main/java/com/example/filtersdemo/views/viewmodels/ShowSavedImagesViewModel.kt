package com.example.filtersdemo.views.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filtersdemo.repositories.ShowDataFromFilesImpl
import com.example.filtersdemo.utilies.Event
import com.example.music_app.other.Resource
import com.example.music_app.other.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ShowSavedImagesViewModel @Inject constructor(private val showDataFromFilesImpl: ShowDataFromFilesImpl) : ViewModel(){
    private val _hasError = MutableLiveData<Event<Resource<Boolean>>>()
    val hasError: LiveData<Event<Resource<Boolean>>> = _hasError
    private val _isLoading = MutableLiveData<Resource<Boolean>>()
    val isLoading: MutableLiveData<Resource<Boolean>> = _isLoading

    private val _bitmapLoadedFromFile = MutableLiveData<Resource<List<Pair<File,Bitmap>>>>()
    val bitmapLoadedFromFile : MutableLiveData<Resource<List<Pair<File,Bitmap>>>> = _bitmapLoadedFromFile
    fun showDataFromFiles(){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _isLoading.postValue(Resource(Status.LOADING,true,null))
                    showDataFromFilesImpl.returnDataFromFileOutPutStream()
            }.onSuccess {
                _bitmapLoadedFromFile.postValue(Resource(Status.SUCCESS,it,null))
            }.onFailure {
                _hasError.postValue(
                    Event(
                        Resource.error(
                            "Couldn't Access File ",
                            null
                        )
                    )
                )
            }
        }

    }
}