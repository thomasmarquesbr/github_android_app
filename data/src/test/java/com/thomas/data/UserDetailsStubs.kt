package com.thomas.data

import com.thomas.data.model.UserDetailResponse
import com.thomas.domain.model.UserDetailsModel

internal object UserDetailsStubs {
    val model = UserDetailsModel(
        nickname = "torvalds",
        name = "Linus Torvalds",
        company = "Linux Foundation",
        avatarUrl = "https://avatars.githubusercontent.com/u/1024025?v=4",
        location = "Portland, OR",
        followers = 208485,
        following = 0,
        reposUrl = "https://api.github.com/users/torvalds/repos"
    )

    val response = UserDetailResponse(
        login = "torvalds",
        id = 1024025,
        nodeId = "MDQ6VXNlcjEwMjQwMjU=",
        avatarUrl = "https://avatars.githubusercontent.com/u/1024025?v=4",
        gravatarId = "",
        url = "https://api.github.com/users/torvalds",
        htmlUrl = "https://github.com/torvalds",
        followersUrl = "https://api.github.com/users/torvalds/followers",
        followingUrl = "https://api.github.com/users/torvalds/following",
        gistsUrl = "https://api.github.com/users/torvalds/gists",
        starredUrl = "https://api.github.com/users/torvalds/starred",
        subscriptionsUrl = "https://api.github.com/users/torvalds/subscriptions",
        organizationsUrl = "https://api.github.com/users/torvalds/orgs",
        reposUrl = "https://api.github.com/users/torvalds/repos",
        eventsUrl = "https://api.github.com/users/torvalds/events",
        receivedEventsUrl = "https://api.github.com/users/torvalds/received_events",
        type = "User",
        siteAdmin  = false,
        name = "Linus Torvalds",
        company = "Linux Foundation",
        blog = "",
        location = "Portland, OR",
        email = null,
        hireable = null,
        bio = null,
        twitterUsername = null,
        publicRepos = 7,
        publicGists = 0,
        followers = 208485,
        following = 0,
        createdAt = "2011-09-03T15:26:22Z",
        updatedat = "2023-11-12T20:08:30Z"
    )
}