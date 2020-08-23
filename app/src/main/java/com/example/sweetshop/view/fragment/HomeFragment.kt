package com.example.sweetshop.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.example.sweetshop.R
import com.example.sweetshop.view.adapter.CategoriesListAdapter
import com.example.sweetshop.view.adapter.MostPopularListAdapter
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    lateinit var categoryAdapter: CategoriesListAdapter
    lateinit var mostPopularListAdapter: MostPopularListAdapter
    private var  list: ArrayList<String> = ArrayList()
    private var  listString: ArrayList<String> = ArrayList()
    private var  categoryArrayList: ArrayList<String> = ArrayList()
    private var  mostPopularList: ArrayList<String> = ArrayList()





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addCategoryAdapter()
        addMostPopularAdapter()
        initCategoryAdapter()
        initMostPopularAdapter()
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

    fun initCategoryAdapter() {

        for(i in 0..2) {
            var data = "Category"
            list.add(data)
        }

            categoryArrayList = list
              categoryAdapter= activity?.let {
               CategoriesListAdapter(
                    requireContext(),
                   categoryArrayList
                )
            }!!
            categories_rec_view.adapter = categoryAdapter
    }

    fun initMostPopularAdapter() {

        for(i in 0..4) {
            var data = "Biscuits with chocolate"
            listString.add(data)
        }

        mostPopularList = listString
        mostPopularListAdapter = activity?.let {
            MostPopularListAdapter(
                requireContext(),
                mostPopularList
            )
        }!!
        most_popular_list_rec_view.adapter = mostPopularListAdapter
    }





}
