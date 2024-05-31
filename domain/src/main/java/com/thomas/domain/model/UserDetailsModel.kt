package com.thomas.domain.model

data class UserDetailsModel(
    val nickname: String,
    val name: String,
    val company: String,
    val avatarUrl: String,
    val location: String,
    val followers: Int,
    val following: Int,
    val repoUrl: String
) {
    companion object {
        val initial = UserDetailsModel(
            nickname = "",
            name = "",
            company = "",
            avatarUrl = "",
            location = "",
            followers = 0,
            following = 0,
            repoUrl = ""
        )
    }
}