package com.example.easyfood.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyfood.models.Meal
import com.example.easyfood.models.MealsList
import com.example.easyfood.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel: ViewModel() {
    private var mealDetailsLiveData = MutableLiveData<Meal>()

    fun getMealDetails(id:String){
        RetrofitInstance.api.getMealDetails(id).enqueue(object: Callback<MealsList>{
            override fun onResponse(call: Call<MealsList>, response: Response<MealsList>) {
                if (response.body()!= null){
                    mealDetailsLiveData.value = response.body()!!.meals[0]
                }else
                    return
            }

            override fun onFailure(call: Call<MealsList>, t: Throwable) {
                Log.d("MealActivity", t.message.toString())
            }
        })
    }
    fun observeMealDetailsLiveData():LiveData<Meal>{
        return mealDetailsLiveData
    }
}