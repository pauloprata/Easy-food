package com.example.easyfood.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.easyfood.activities.CategoryMealsActivity
import com.example.easyfood.activities.MealActivity
import com.example.easyfood.adapter.CategoriesAdapter
import com.example.easyfood.adapter.MostPopularAdapter
import com.example.easyfood.databinding.FragmentHomeBinding
import com.example.easyfood.models.MealsByCategory
import com.example.easyfood.models.Meal
import com.example.easyfood.viewmodel.HomeViewModel


class HomeFragment : Fragment() {
   private lateinit var homeMvvm: HomeViewModel
   private lateinit var binding: FragmentHomeBinding
   private lateinit var randomMeal:Meal
   private lateinit var popularItemsAdapetr: MostPopularAdapter
   private lateinit var categoriesItemsAdapter: CategoriesAdapter

   companion object{
       const val MEAL_ID = "com.example.easyfood.fragments.idMeal"
       const val MEAL_NAME = "com.example.easyfood.fragments.nameMeal"
       const val MEAL_THUMB = "com.example.easyfood.fragments.thumbMeal"
       const val CATEGORY_NAME = "com.example.easyfood.fragments.categoryName"
   }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProviders.of(this)[HomeViewModel::class.java]

        popularItemsAdapetr = MostPopularAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preparePopularItemRecyclerView()
        homeMvvm.getRandoMeal()
        observeRandoMeal()
        randoMealClick()

        homeMvvm.getPopularItems()
        observePopularItemsLiveData()

        onPopularItemClick()

        homeMvvm.getCategories()
        observeCategorieLiveData()
        prepareCategoriesRecyclerView()
        onCategoryClick()


    }

    private fun onCategoryClick() {
        categoriesItemsAdapter.onItemClick = { category->
            val intent = Intent(activity,CategoryMealsActivity::class.java)
            intent.putExtra(CATEGORY_NAME, category.strCategory)
            startActivity(intent)
        }
    }

    private fun prepareCategoriesRecyclerView() {
        categoriesItemsAdapter = CategoriesAdapter()
        binding.recyclerViewCategories.apply {
            layoutManager = GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
            adapter = categoriesItemsAdapter
        }
    }

    private fun observeCategorieLiveData() {
             homeMvvm.observeCategories().observe(viewLifecycleOwner, Observer { categories->
                  categoriesItemsAdapter.setCategoryList(categories)
             })
    }

    private fun onPopularItemClick() {
        popularItemsAdapetr.onItemClick = {meal->
            val intent= Intent(activity,MealActivity::class.java)
            intent.putExtra(MEAL_ID,meal.idMeal)
            intent.putExtra(MEAL_NAME,meal.strMeal)
            intent.putExtra(MEAL_THUMB,meal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun preparePopularItemRecyclerView() {
        binding.recViewMealsPopular.apply {
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            adapter = popularItemsAdapetr
        }
    }

    private fun observePopularItemsLiveData() {
         homeMvvm.observePopularItemsLiveData().observe(viewLifecycleOwner,{ mealList->
             popularItemsAdapetr.setMeals(mealsList = mealList as ArrayList<MealsByCategory>)
         })
    }

    private fun randoMealClick() {
        binding.randomMeal.setOnClickListener {
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID,randomMeal.idMeal)
            intent.putExtra(MEAL_NAME,randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB,randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observeRandoMeal() {
        homeMvvm.observeRandomMeal().observe(viewLifecycleOwner,
            {meal->
                Glide.with(this@HomeFragment).load(meal!!.strMealThumb).into(binding.imgRandomMeal)
                this.randomMeal = meal

        })

    }


}