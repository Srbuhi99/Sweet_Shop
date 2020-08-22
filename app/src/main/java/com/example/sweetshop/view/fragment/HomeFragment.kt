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
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    lateinit var categoryAdapter: CategoriesListAdapter
    private var list: ArrayList<String> = ArrayList()
    private var followersModelArrayList: ArrayList<String> = ArrayList()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addAdapter()
        initAdapter()
    }

    fun addAdapter(){

        categories_rec_view.layoutManager = activity?.let {
            LinearLayoutManager(
                it, LinearLayoutManager.HORIZONTAL,
                false
            )
        }

    }

    fun initAdapter() {

        for(i in 0..2) {
            var data = "Category"
            list.add(data)
        }

            followersModelArrayList = list
            categoryAdapter = activity?.let {
                CategoriesListAdapter(
                    requireContext(),
                    followersModelArrayList!!
                )
            }!!
            categories_rec_view.adapter = categoryAdapter
    }





}
