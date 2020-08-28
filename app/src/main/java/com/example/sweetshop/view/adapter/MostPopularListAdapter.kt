package com.example.sweetshop.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.sweetshop.R
import com.example.sweetshop.model.MostPopularProductsModel
import com.example.sweetshop.utils.AddToBasketAnimUtil

class MostPopularListAdapter(var context: Context, var list:MostPopularProductsModel):
    RecyclerView.Adapter<MostPopularListAdapter.ViewHolder>() {

    private val  addToBasketAnimUtil: AddToBasketAnimUtil by lazy { AddToBasketAnimUtil() }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.most_popular_item, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return list.data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        addToBasketAnimUtil.initVariables(context)


         holder.bindItems(context,list.data[position].productName,list.data[position].price,list.data[position].imageUrl)
        holder.addcklickListener(addToBasketAnimUtil)

    }



    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        var sweetText:TextView
        var sweetImage: ImageView
        var sweetPrice: TextView
        var addBasketBtn:ImageButton
        var incrementButton: ImageButton
        var decrementButton: ImageButton
        var cardView :RelativeLayout
        var isOpen = false

        init {
            sweetText = itemView.findViewById(R.id.sweet_title)
            sweetImage = itemView.findViewById(R.id.sweet_image_view)
            sweetPrice = itemView.findViewById(R.id.sweet_price)
            addBasketBtn = itemView.findViewById(R.id.add_bascket_btn)
            incrementButton = itemView.findViewById(R.id.increment_btn)
            decrementButton = itemView.findViewById(R.id.decrement_btn)
            cardView  = itemView.findViewById(R.id.counter_layout)

        }

       fun addcklickListener(addToBasketAnimUtil:AddToBasketAnimUtil){
           sweetPrice.setOnClickListener {

               if(isOpen) {
                   incrementButton.startAnimation(addToBasketAnimUtil.fabClose)
                   decrementButton.startAnimation(addToBasketAnimUtil.fabClose)
                   sweetPrice.startAnimation(addToBasketAnimUtil.moveLeft)
                   addBasketBtn.startAnimation(addToBasketAnimUtil.moveLeft)
                   incrementButton.isClickable = false
                   cardView.visibility = View.INVISIBLE
                   isOpen = false
               }
               else{
                   incrementButton.startAnimation(addToBasketAnimUtil.slideOpen)
                   decrementButton.startAnimation(addToBasketAnimUtil.slideOpen)
                   sweetPrice.startAnimation(addToBasketAnimUtil.moveRight)
                   addBasketBtn.startAnimation(addToBasketAnimUtil.slideRight)
                   incrementButton.isClickable = true
                   cardView.visibility = View.VISIBLE
                   isOpen = true
               }
           }
       }



        fun bindItems(context:Context, sweetTitle :String, price: Int,imageUrl:String?){
         sweetText.text = sweetTitle
         sweetPrice.text =  price.toString() + "դ/kg"

            if(imageUrl != null) {
                Glide.with(context).load(imageUrl)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(22)))
                    .into(sweetImage)
            }
            else{
                Glide.with(context).load("https://homepages.cae.wisc.edu/~ece533/images/fruits.png")
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(22)))
                    .into(sweetImage)

            }


        }

    }

}