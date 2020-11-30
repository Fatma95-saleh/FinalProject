
package com.example.projectone.network

import com.google.gson.annotations.SerializedName



data class Post (

    @SerializedName("id") val id : String?,
    @SerializedName("title") val title : String?,
    @SerializedName("body") val body : String?,
    @SerializedName("author_id") var author_id : String?,
    @SerializedName("created_at") val created_at : String?,
    @SerializedName("updated_at") val updated_at : String?)