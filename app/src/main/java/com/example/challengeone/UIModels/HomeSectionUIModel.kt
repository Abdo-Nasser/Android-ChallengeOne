package com.example.challengeone.UIModels

data class HomeSectionUIModel(
    val id: String,
    val title: String,
    val hasSeeAll: Boolean,
    var isCardView: Boolean,
    val items: List<PlaceUIModel>
)
