

package com.example.projectone.network

import com.google.gson.annotations.SerializedName




data class Posts (

	@SerializedName("data") val data : List<Post>,
	@SerializedName("message") val message : String,
	@SerializedName("status") val status : Boolean
)

