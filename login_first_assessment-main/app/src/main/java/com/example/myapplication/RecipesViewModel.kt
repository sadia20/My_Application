package com.example.myapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RecipesViewModel : ViewModel() {

    // List of all recipes
    private val allRecipes = listOf(
        Recipe(1, "Spaghetti Bolognese", "A classic Italian dish."),
        Recipe(2, "Chicken Curry", "A spicy and flavorful curry."),
        Recipe(3, "Vegetable Stir Fry", "A quick and healthy stir fry."),
        Recipe(4, "Pancakes", "A sweet breakfast favorite."),
        Recipe(5, "Grilled Cheese", "A simple and delicious sandwich.")
    )

    // StateFlow to expose filtered recipes
    private val _recipesStateFlow = MutableStateFlow(allRecipes)
    val recipesStateFlow: StateFlow<List<Recipe>> get() = _recipesStateFlow

    /**
     * Filters recipes based on the query.
     * If the query is less than 3 characters, all recipes are shown.
     */
    fun searchRecipes(query: String) {
        val trimmedQuery = query.trim()
        if (trimmedQuery.length < 3) {
            // Show all recipes for short or empty queries
            _recipesStateFlow.value = allRecipes
            return
        }

        // Filter recipes based on the query
        val filteredRecipes = allRecipes.filter { recipe ->
            recipe.title.contains(trimmedQuery, ignoreCase = true) ||
                    recipe.description.contains(trimmedQuery, ignoreCase = true)
        }

        // Update the state if the result has changed
        if (filteredRecipes != _recipesStateFlow.value) {
            _recipesStateFlow.update { filteredRecipes }
        }
    }
}
