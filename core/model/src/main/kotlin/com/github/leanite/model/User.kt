package com.github.leanite.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    var username: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("avatar_url")
    var avatarUrl: String? = null,

    @SerializedName("location")
    var location: String? = null,

    @SerializedName("blog")
    var websiteUrl: String? = null,

    @SerializedName("public_repos")
    var amountRepositories: Int = 0
)