package com.example.githubuser.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(

    @field:SerializedName("total_count")
    val totalCount: Int? = null,

    @field:SerializedName("incomplete_results")
    val incompleteResults: Boolean? = null,

    @field:SerializedName("items")
    val items: List<UserResponse> = mutableListOf()
)