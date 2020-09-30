package com.github.danielgalion.pizzatestapp.models

data class Recipe(
    val title: String,
    val description: String,
    val ingredients: ArrayList<String>,
    val preparing: ArrayList<String>,
    val imgs: ArrayList<String>
) {
    val ingredientsStr: String
        get() {
            var str = ""

            for (index in 0 until ingredients.size) {
                str += "- ${ingredients[index]}"

                if (index != ingredients.size - 1) str += '\n'
            }

            return str
        }
}