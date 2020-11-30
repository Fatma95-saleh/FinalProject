package com.example.projectone.network

import com.google.gson.annotations.SerializedName


data class PostData (

	@SerializedName("data") val data : PostObject,
	@SerializedName("message") val message : String,
	@SerializedName("status") val status : Boolean
)