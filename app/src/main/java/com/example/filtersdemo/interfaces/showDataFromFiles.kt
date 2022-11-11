package com.example.filtersdemo.interfaces

import android.graphics.Bitmap
import java.io.File

interface ShowDataFromFiles {
    suspend fun returnDataFromFileOutPutStream(): List<Pair<File,Bitmap>>?
}