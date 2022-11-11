package com.example.filtersdemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filtersdemo.R
import com.example.filtersdemo.data.ImageFilter
import kotlinx.android.synthetic.main.filter_images_row.view.*

class ImagesAdapter(var apiList: MutableList<ImageFilter>) : RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        return ImagesViewHolder(
            LayoutInflater.from(parent.getContext()).inflate(
                R.layout.filter_images_row,
                parent,
                false
            )
        )
    }
    class ImagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val img_photo = itemView.img_filter_preview
            val filter_name = itemView.textFilterName

        fun onBind(imageFilters : ImageFilter){
            filter_name.text = imageFilters.name
            img_photo.setImageBitmap(imageFilters.preview)


        }

    }
    var onItemClickListener : ((ImageFilter) -> Unit)? = null

    fun setItemClickListener(listener: (ImageFilter) -> Unit){
               onItemClickListener = listener
   }



    fun setData(apilist: MutableList<ImageFilter>) {
        this.apiList = apilist
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.onBind(apiList[position])
        var p = apiList[position]
        holder.itemView.apply {
            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(p)
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return apiList.size
    }
}