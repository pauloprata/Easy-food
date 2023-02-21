package com.example.easyfood.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.easyfood.R
import com.example.easyfood.adapter.CategoryMealsAdapter
import com.example.easyfood.databinding.ActivityCategoryMealsBinding
import com.example.easyfood.databinding.CategoryItemBinding
import com.example.easyfood.fragments.HomeFragment
import com.example.easyfood.viewmodel.CategoryMealsViewModel

class CategoryMealsActivity : AppCompatActivity() {
    lateinit var binding: ActivityCategoryMealsBinding
    lateinit var categoryMealsBinding: CategoryMealsViewModel
    lateinit var categoryMealsAdapter: CategoryMealsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareRecyclerView()

        categoryMealsBinding = ViewModelProviders.of(this)[CategoryMealsViewModel::class.java]
        categoryMealsBinding.getMealsByCategory(intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!)



        categoryMealsBinding.observeMealsLiveData().observe(this, Observer {mealslList->
            binding.tvCetogory.text = mealslList.size.toString()
            categoryMealsAdapter.setMealsList(mealslList)
        })
    }

    private fun prepareRecyclerView(){
        categoryMealsAdapter = CategoryMealsAdapter()
        binding.rvMeals.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter = categoryMealsAdapter
        }
    }
}