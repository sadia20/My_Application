package com.example.myapplication

data class RecipeModel(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String
)
data class Recipe(
    val id: Int,
    val title: String,
    val description: String
)