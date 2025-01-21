package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RecipeItemBinding

class RecipesAdapter(
    private val onItemClicked: ((Recipe) -> Unit)? = null
) : ListAdapter<Recipe, RecipesAdapter.RecipeViewHolder>(RecipeDiffCallback()) {

    inner class RecipeViewHolder(private val binding: RecipeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.apply {
                recipeTitle.text = recipe.title
                recipeDescription.text = recipe.description

                // Set a click listener on the root view
                root.setOnClickListener {
                    onItemClicked?.invoke(recipe)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = RecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // DiffUtil to optimize RecyclerView updates
    class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }
}
