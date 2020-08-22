package com.example.sweetshop.view.adapter

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.marginLeft
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sweetshop.R
import com.example.sweetshop.utils.RecyclerItemColorsUtil


class CategoriesListAdapter(val context: Context, var data:ArrayList<String>)
    :RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(context, data[position])
        holder.addColor(position)
        holder.addItemSizes(context)

    }




    class ViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {

        var recyclerItemColorsUtil = RecyclerItemColorsUtil()


        var categoryTitle: TextView
        var cardViewMain: CardView
        var parentLayout: RelativeLayout
        var cardRecView: RelativeLayout
        var categoryImage :ImageView

        init {
            categoryTitle = itemView!!.findViewById(R.id.category_title)
            cardViewMain = itemView.findViewById(R.id.card_view_main)
            parentLayout = itemView.findViewById(R.id.image_view)
            cardRecView = itemView.findViewById(R.id.card_rec_view)
            categoryImage = itemView.findViewById(R.id.category_image)
        }

        fun bindItems(context: Context, title: String) {

            categoryTitle.text  = title

            Glide.with(context).load("https://homepages.cae.wisc.edu/~ece533/images/fruits.png")
                .apply(RequestOptions.circleCropTransform())
                .into(categoryImage)
        }

        fun addColor(position: Int){

             if(position <= recyclerItemColorsUtil.colorArray.size-1)
            cardViewMain.setBackgroundResource(recyclerItemColorsUtil.colorArray[position])

        }

        fun addItemSizes(context: Context){

            val displaymetrics = DisplayMetrics()
            (context as Activity).windowManager.defaultDisplay.getMetrics(displaymetrics)

            val devicewidth = displaymetrics.widthPixels / 2

            val deviceheight = displaymetrics.heightPixels / 4

            parentLayout.getLayoutParams().width = devicewidth
            parentLayout.getLayoutParams().height = deviceheight


            categoryImage.layoutParams.width = devicewidth/2 -10
            categoryImage.layoutParams.height = deviceheight/2 -10

            cardRecView.layoutParams.width = devicewidth - 110
        }
    }
}