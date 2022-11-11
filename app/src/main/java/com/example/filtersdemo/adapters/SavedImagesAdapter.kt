package com.example.filtersdemo.adapters

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filtersdemo.R
import com.example.filtersdemo.data.ImageFilter
import com.example.filtersdemo.interfaces.ImageClicked
import com.example.filtersdemo.views.ui.ShowSavedImages
import kotlinx.android.synthetic.main.activity_edit.view.*
import kotlinx.android.synthetic.main.filter_images_row.view.*
import kotlinx.android.synthetic.main.item_container_layout.view.*
import java.io.File

class SavedImagesAdapter(var apiList: List<Pair<File,Bitmap>>,  val clicked: ImageClicked) : RecyclerView.Adapter<SavedImagesAdapter.ImagesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        return ImagesViewHolder(
            LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_container_layout,
                parent,
                false
            )
        )
    }
    class ImagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img_saved = itemView.image_saved_view


    }
    var onItemClickListener : ((file : File) -> Unit)? = null

    fun setItemClickListener(listener: (file: File) -> Unit){
        onItemClickListener = listener
    }
    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        with(holder) {
            with(apiList[position]) {
                img_saved.setImageBitmap(second)
                itemView.apply {
                    setOnClickListener {
                      clicked.onImageClicked(first)
                    }
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return apiList.size
    }
}