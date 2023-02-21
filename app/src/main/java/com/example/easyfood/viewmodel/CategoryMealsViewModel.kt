package com.example.easyfood.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyfood.models.MealsByCategorieList
import com.example.easyfood.models.MealsByCategory
import com.example.easyfood.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryMealsViewModel: ViewModel() {

    val mealsLiveData = MutableLiveData<List<MealsByCategory>>()

    fun getMealsByCategory(categoryName: String){
        RetrofitInstance.api.getMealsByCateory(categoryName).enqueue(object : Callback<MealsByCategorieList>{
            override fun onResponse(
                call: Call<MealsByCategorieList>,
                response: Response<MealsByCategorieList>
            ) {
                response.body()?.let{
                    mealsList->
                    mealsLiveData.postValue(mealsList.meals)
                }
            }

            override fun onFailure(call: Call<MealsByCategorieList>, t: Throwable) {
                Log.d("CategoryViewModel", t.message.toString())
            }
        })
    }
    fun observeMealsLiveData():LiveData<List<MealsByCategory>>{
        return mealsLiveData
    }
}