package com.example.sweetshop.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.sweetshop.R

class MostPopularListAdapter(var context: Context, var list:ArrayList<String>):
    RecyclerView.Adapter<MostPopularListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.most_popular_item, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bindItems(context,list[position])
    }



    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        var sweetText:TextView
        var sweetImage: ImageView

        init {
            sweetText = itemView.findViewById(R.id.sweet_title)
            sweetImage = itemView.findViewById(R.id.sweet_image_view)

        }

        fun bindItems(context:Context, sweetTitle :String){
         sweetText.text = sweetTitle

            Glide.with(context).load("https://homepages.cae.wisc.edu/~ece533/images/fruits.png")
                .apply(RequestOptions.bitmapTransform(RoundedCorners(22)))
                .into(sweetImage)

        }

    }

}