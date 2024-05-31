package com.thomas.domain.model

data class RepositoryModel(
    val id: Int,
    val name: String,
    val description: String,
    val url: String,
    val starsAmount: Int,
    val watchersAmount: Int,
    val language: String,
    val licenseName: String,
)