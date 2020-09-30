package com.github.danielgalion.pizzatestapp.uicontrollers.recipe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.danielgalion.pizzatestapp.R
import kotlinx.android.synthetic.main.row_recipe_steps.view.*

class RecipeStepsAdapter(private val context: Context, private val steps: List<String>) :
    RecyclerView.Adapter<StepsVH>() {

    companion object {
        private const val TAG = "RecipeStepsAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsVH {
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.row_recipe_steps, parent, false)

        return StepsVH(row)
    }

    override fun onBindViewHolder(holder: StepsVH, position: Int) {
        holder.view.recipe_step_tv.text =
            context.getString(R.string.recipe_step, position + 1, steps[position])
    }

    override fun getItemCount(): Int {
        return steps.size
    }
}

class StepsVH(val view: View) : RecyclerView.ViewHolder(view)
