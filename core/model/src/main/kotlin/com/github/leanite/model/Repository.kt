package com.github.leanite.model

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("owner")
    var owner: User,

    @SerializedName("name")
    var name: String,

    @SerializedName("full_name")
    var fullName: String,

    @SerializedName("url")
    var url: String,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("forks_count")
    var forks: Int = 0,

    @SerializedName("stargazers_count")
    var stars: Int = 0,

    @SerializedName("open_issues_count")
    var issues: Int = 0
)