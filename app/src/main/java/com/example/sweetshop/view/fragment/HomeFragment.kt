package com.example.sweetshop.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.sweetshop.R
import com.example.sweetshop.api.ApiHelper
import com.example.sweetshop.api.RetrofitBuilder
import com.example.sweetshop.common.Status
import com.example.sweetshop.model.MostPopularProductsModel
import com.example.sweetshop.model.CategoryModel
import com.example.sweetshop.utils.OnItemClickListener
import com.example.sweetshop.utils.addOnItemClickListener
import com.example.sweetshop.view.adapter.CategoriesListAdapter
import com.example.sweetshop.view.adapter.MostPopularListAdapter
import com.example.sweetshop.viewmodel.HomeViewModel
import com.example.sweetshop.viewmodel.base.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    lateinit var categoryAdapter: CategoriesListAdapter
    lateinit var mostPopularListAdapter: MostPopularListAdapter


    private lateinit var  category: CategoryModel
    private lateinit var  mostPopularProduct:  MostPopularProductsModel


    lateinit var navController: NavController
    private lateinit var viewModel: HomeViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        setupViewModel()

        addCategoryAdapter()
        addMostPopularAdapter()
        addItemClickListener()
        setupObserversToCategory()
        setupObserversToProducts()
        addScrollListener()
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(HomeViewModel::class.java)
    }

    private fun setupObserversToCategory() {
        viewModel.getCategories().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { category -> initCategoryAdapter(category) }
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

    private fun setupObserversToProducts(){
        viewModel.getMostPopularProducts().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {

                        resource.data?.let { product -> initMostPopularAdapter(product) }
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




   private fun addCategoryAdapter(){

        categories_rec_view.layoutManager = activity?.let {
            LinearLayoutManager(
                it, LinearLayoutManager.HORIZONTAL,
                false
            )
        }

    }

   private  fun addMostPopularAdapter(){
        most_popular_list_rec_view.layoutManager = activity?.let {
            LinearLayoutManager(
                it, LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

    fun initCategoryAdapter(list: CategoryModel) {

            category = list
              categoryAdapter= activity?.let {
               CategoriesListAdapter(
                    requireContext(),
                   category
                )
            }!!
            categories_rec_view.adapter = categoryAdapter
    }


    fun initMostPopularAdapter(data: MostPopularProductsModel) {

        mostPopularProduct = data
        mostPopularListAdapter = activity?.let {
            MostPopularListAdapter(
                requireContext(),
                mostPopularProduct
            )
        }!!
        most_popular_list_rec_view.adapter = mostPopularListAdapter
    }

    fun addItemClickListener(){
        most_popular_list_rec_view.addOnItemClickListener(object:
            OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                var bundle = Bundle()
                var id = mostPopularProduct.data[position].id
                 bundle.putInt("productId", id)

                navController.navigate(R.id.action_homeFragment_to_productDetailsFragment,bundle)
            }
        })
    }

    fun addScrollListener(){
        categories_rec_view.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
        }
    }

   }
