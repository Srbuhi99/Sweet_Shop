package com.example.sweetshop.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

import com.example.sweetshop.R
import com.example.sweetshop.api.ApiHelper
import com.example.sweetshop.api.RetrofitBuilder
import com.example.sweetshop.common.Status
import com.example.sweetshop.databinding.FragmentProductDetailsBinding
import com.example.sweetshop.model.MostPopularProductsModel
import com.example.sweetshop.model.SearchByIdModel
import com.example.sweetshop.utils.AddToBasketAnimUtil
import com.example.sweetshop.viewmodel.ProductDetailsViewModel
import com.example.sweetshop.viewmodel.base.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_product_details.*

class ProductDetailsFragment : Fragment(),View.OnClickListener{

    private val  addToBasketAnimUtil: AddToBasketAnimUtil by lazy {AddToBasketAnimUtil()}
    lateinit var viewModel:ProductDetailsViewModel
    lateinit var binding: FragmentProductDetailsBinding
    var isOpen = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        viewModel = ViewModelProviders.of(this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(ProductDetailsViewModel::class.java)
        binding =
            DataBindingUtil.inflate(inflater,R.layout.fragment_product_details, container, false)
         binding.viewModel = viewModel
         binding.lifecycleOwner = this
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var id = requireArguments().getInt("productId")

        add_to_basket_fab.setOnClickListener(this)
        add_to_basket_fab.shrink()
        addToBasketAnimUtil.initVariables(requireContext())
        setupObservers(id)


    }


    fun addAnimationToFAB(){

        if(add_to_basket_fab.isExtended) {
            add_to_basket_fab.shrink()
        }
        else{
            add_to_basket_fab.extend()
        }

        if(isOpen){

            increment_btn.startAnimation(addToBasketAnimUtil.fabClose)
            decrement_btn.startAnimation(addToBasketAnimUtil.fabClose)
            increment_btn.isClickable = false
            counter_layout.visibility = View.INVISIBLE
            isOpen = false
        }
        else{

            increment_btn.startAnimation(addToBasketAnimUtil.fabOpen)
            decrement_btn.startAnimation(addToBasketAnimUtil.fabOpen)
            increment_btn.isClickable = true
            counter_layout.visibility = View.VISIBLE
            isOpen = true

        }
    }

    private fun setupObservers(id : Int){
        viewModel.getProductById(id).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {

                        resource.data?.let { product -> addDataToLayout(product) }
                    }
                    Status.ERROR -> {

                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {

                    }
                }
            }
        })
    }

    fun addDataToLayout(data: SearchByIdModel){
     sweet_name_text_view.text = data.data.productName
     price_text_view.text = data.data.price.toString() + "դ/kg"


        if(data.data.imageUrl != null) {
            Glide.with(requireContext()).load(data.data.imageUrl)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(50)))
                .into(product_image_view)
        }
        else{
            Glide.with(requireContext()).load("https://homepages.cae.wisc.edu/~ece533/images/fruits.png")
                .apply(RequestOptions.bitmapTransform(RoundedCorners(50)))
                .into(product_image_view)

        }


    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.add_to_basket_fab ->  addAnimationToFAB()
        }
    }


}
