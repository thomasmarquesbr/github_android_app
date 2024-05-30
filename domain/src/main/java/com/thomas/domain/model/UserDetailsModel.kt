package com.thomas.domain.model

data class UserDetailsModel(
    val nickname: String,
    val name: String,
    val email: String,
    val avatarUrl: String,
    val repoUrl: String
) {
    companion object {
        val initial = UserDetailsModel("", "","", "", "")
    }
}