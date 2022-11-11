package com.example.filtersdemo.di

import android.content.Context
import com.example.filtersdemo.repositories.EditImageRepository
import com.example.filtersdemo.repositories.ShowDataFromFilesImpl
import com.example.filtersdemo.views.viewmodels.EditImagesViewModel
import com.example.filtersdemo.views.viewmodels.ShowSavedImagesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun RepoClass(@ApplicationContext appContext: Context) = EditImageRepository(appContext)
    @Provides
    fun viewModelClass(@ApplicationContext appContext: Context) =  EditImagesViewModel(RepoClass(appContext))
    @Provides
    fun showDataFromFilesRepo(@ApplicationContext appContext: Context) = ShowDataFromFilesImpl(appContext)
    @Provides
    fun showSavedImagesviewModelClass(@ApplicationContext appContext: Context) =  ShowSavedImagesViewModel(showDataFromFilesRepo(appContext))
    }
