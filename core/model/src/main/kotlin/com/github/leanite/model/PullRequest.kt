package com.github.leanite.model

import com.google.gson.annotations.SerializedName

data class PullRequest(
    @SerializedName("user")
    var creator: User,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("state")
    var state: String? = null,

    @SerializedName("body")
    var body: String? = null,

    @SerializedName("html_url")
    var url: String? = null,

    @SerializedName("created_at")
    var createdAt: String? = null,

    @SerializedName("merged_at")
    var mergedAt: String? = null
)