package com.example.myapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.RecipeItemBinding

class RecipeAdapter(
    private var recipes: List<RecipeModel> = emptyList(),
    private val onItemClick: (Int) -> Unit,
    private val onButtonClick: (String, Int) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    // ViewHolder to bind recipe data to UI
    inner class RecipeViewHolder(private val binding: RecipeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: RecipeModel) {
            binding.apply {
                recipeTitle.text = recipe.title
                recipeDescription.text = recipe.description

                // Handle like and share button clicks
                likeButton.setOnClickListener { onButtonClick("like", recipe.id) }
                shareButton.setOnClickListener { onButtonClick("share", recipe.id) }

                // Handle item click
                root.setOnClickListener { onItemClick(recipe.id) }

                // Load recipe image using Glide
                Glide.with(recipeImage.context)
                    .load(recipe.imageUrl)
                    .placeholder(R.drawable.placeholder_image) // Optional placeholder
                    .error(R.drawable.error_image) // Optional error image
                    .into(recipeImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = RecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newRecipes: List<RecipeModel>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }
}
