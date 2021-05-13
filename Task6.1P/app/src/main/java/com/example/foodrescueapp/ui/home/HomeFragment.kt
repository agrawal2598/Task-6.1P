package com.example.foodrescueapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrescueapp.R
import com.example.foodrescueapp.adapter.FoodAdapter
import com.example.foodrescueapp.data.DatabaseHelper
import com.example.foodrescueapp.ui.BaseFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val databaseHelper = context?.let { DatabaseHelper.getInstance(it) }
        val foodNotFoundLabel = view.findViewById<TextView>(R.id.no_food_found_label)
        val foodRecyclerView = view.findViewById<RecyclerView>(R.id.food_recycler_view)
        val food = databaseHelper?.getFood(1)
        foodRecyclerView.adapter = food?.let { FoodAdapter(it) }
        view.findViewById<FloatingActionButton>(R.id.add_fab).setOnClickListener {
            navController.navigate(R.id.action_nav_home_to_addFoodFragment)
        }
        view.findViewById<ImageView>(R.id.logout_image_view).setOnClickListener {
            navController.navigate(R.id.action_nav_home_to_loginFragment)
        }
        if (food?.isNullOrEmpty() == true)
            foodNotFoundLabel.visibility = View.VISIBLE
    }
}