package com.example.easyfood.retrofit

import com.example.easyfood.models.CategoryList
import com.example.easyfood.models.MealsByCategorieList
import com.example.easyfood.models.MealsList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsAPI {
    @GET("random.php")
   suspend fun getRandonMeal(): Response<MealsList>

    @GET("lookup.php?")
    fun getMealDetails(@Query("i")id:String): Call<MealsList>

   @GET("filter.php")
  suspend fun getPopularItems(@Query("c") categoryName:String): Response<MealsByCategorieList>

    @GET("categories.php")
    suspend fun getCategories(): Response<CategoryList>

    @GET("filter.php")
    fun getMealsByCateory(@Query("c") categoryName: String): Call<MealsByCategorieList>


}