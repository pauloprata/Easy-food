package com.example.easyfood.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyfood.models.*
import com.example.easyfood.retrofit.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel: ViewModel() {
    private var randonMealLiveData = MutableLiveData<Meal>()
    private var popularItemsLiveData = MutableLiveData<List<MealsByCategory>>()
    private var categoriesLiveData = MutableLiveData<List<Category>>()

    fun getRandoMeal(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitInstance.api.getRandonMeal()
            withContext(Dispatchers.Main) {
                if (response.body()!= null){
                    val randonMeal: Meal = response.body()!!.meals[0]
                    randonMealLiveData.value = randonMeal
                }else
                    return@withContext
            }
        }
    }

    fun getPopularItems(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitInstance.api.getPopularItems("Seafood")
            withContext(Dispatchers.Main) {
                if (response.body()!= null){
                    popularItemsLiveData.value = response.body()!!.meals
                }
            }
        }
    }

    fun getCategories(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitInstance.api.getCategories()
            withContext(Dispatchers.Main) {
                response.body()?.let { categoryList ->
                    categoriesLiveData.postValue(categoryList.categories)
                }
            }
        }
    }
    fun observeRandomMeal(): LiveData<Meal>{
        return randonMealLiveData
    }

    fun observePopularItemsLiveData(): LiveData<List<MealsByCategory>>{
        return popularItemsLiveData
    }

    fun observeCategories(): LiveData<List<Category>>{
        return categoriesLiveData
    }
}
