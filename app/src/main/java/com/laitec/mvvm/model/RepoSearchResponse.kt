package com.laitec.mvvm.model

import com.google.gson.annotations.SerializedName


/**
 * Data class to hold repo responses from searchRepo API calls.
 */
data class RepoSearchResponse(
    @SerializedName("total_count") val total: Int = 0,

    @SerializedName("items") val items: List<RepoSearch> = emptyList(),

    val nextPage: Int? = null
)
