package com.github.danielgalion.pizzatestapp.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.github.danielgalion.pizzatestapp.models.Recipe
import com.github.danielgalion.pizzatestapp.MainApplication
import com.google.gson.GsonBuilder

class FetcherViewModel : ViewModel() {

    companion object {
        private const val TAG = "FetcherViewModel"
        private const val BASE_URL = "https://moodup.team/"
    }

    private var recipe = MutableLiveData<Recipe>()
    private val queue = Volley.newRequestQueue(MainApplication.getInstance()?.applicationContext)

    fun getRecipe(): LiveData<Recipe>? {
        return recipe
    }

    fun fetchRecipe() {
        val url = "${BASE_URL}test/info.php"

        val stringRequest = StringRequest(Request.Method.GET, url, {
            val gson = GsonBuilder().create()

            recipe.value = gson.fromJson(it, Recipe::class.java)
        }, {

        })

        queue.add(stringRequest)
    }
}