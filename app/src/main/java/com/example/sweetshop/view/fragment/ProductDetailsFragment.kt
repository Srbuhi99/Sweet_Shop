package com.example.sweetshop.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.marginRight

import com.example.sweetshop.R
import com.example.sweetshop.utils.AddToBasketAnimUtil
import kotlinx.android.synthetic.main.fragment_product_details.*

class ProductDetailsFragment : Fragment(),View.OnClickListener{



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add_to_basket_fab.setOnClickListener(this)
        add_to_basket_fab.shrink()


    }


    fun addAnimationToFAB(){

        if(add_to_basket_fab.isExtended) {
            add_to_basket_fab.shrink()
        }
        else{
            add_to_basket_fab.extend()
        }
    }

    override fun onClick(v: View?) {
       addAnimationToFAB()
    }


}
