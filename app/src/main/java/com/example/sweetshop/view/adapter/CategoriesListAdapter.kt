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
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sweetshop.R
import com.example.sweetshop.model.CategoryModel
import com.example.sweetshop.utils.RecyclerItemColorsUtil


class CategoriesListAdapter(val context: Context, var category : CategoryModel)
    :RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
       return category.data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(context, category.data[position].categoryName
                                , category.data[position].imageUrl,
                                  category.data[position].productCount)

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
        var productCount:TextView

        init {
            categoryTitle = itemView!!.findViewById(R.id.category_title)
            cardViewMain = itemView.findViewById(R.id.card_view_main)
            parentLayout = itemView.findViewById(R.id.horizontal_view)
            cardRecView = itemView.findViewById(R.id.card_rec_view)
            categoryImage = itemView.findViewById(R.id.category_image)
            productCount = itemView.findViewById(R.id.product_count_text)
        }


        fun bindItems(context: Context, title: String,imageUrl:String, count:Int) {

            categoryTitle.text  = title
            productCount.text = count.toString()

            Glide.with(context).load(imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(categoryImage)
        }

        fun addColor(position: Int){

            val arrayIndex = recyclerItemColorsUtil.colorArray.size-1
            val colorArraySize = recyclerItemColorsUtil.colorArray.size

             if(position <= arrayIndex){
            cardViewMain.setBackgroundResource(recyclerItemColorsUtil.colorArray[position])
             }
            else{
                 cardViewMain.setBackgroundResource(recyclerItemColorsUtil.colorArray[position - colorArraySize])
             }

        }

        fun addItemSizes(context: Context){

            val displaymetrics = DisplayMetrics()
            (context as Activity).windowManager.defaultDisplay.getMetrics(displaymetrics)

            val devicewidth = displaymetrics.widthPixels / 2 - 10

            val deviceheight = displaymetrics.heightPixels / 4 + 20

            parentLayout.getLayoutParams().width = devicewidth
            parentLayout.getLayoutParams().height = deviceheight


            categoryImage.layoutParams.width = devicewidth/2
            categoryImage.layoutParams.height = devicewidth/2

            cardRecView.layoutParams.width = devicewidth - 120
        }
    }
}