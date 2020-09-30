package com.github.danielgalion.pizzatestapp.models

data class Recipe(
    val title: String,
    val description: String,
    val ingredients: ArrayList<String>,
    val preparing: ArrayList<String>,
    val imgs: ArrayList<String>
)