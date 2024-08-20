package com.example.challengeone.UIModels

data class PlaceUIModel(
    val id: String,
    val title: String,
    val subtitle: String,
    val imageULR: String,
    val rate: Double,
    val isFavourite: Boolean,
    val isHotDeal: Boolean,
    val nCount: Int,
    val dCount: Int
)
