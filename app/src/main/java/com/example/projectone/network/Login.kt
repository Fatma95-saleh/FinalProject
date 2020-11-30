package com.example.projectone.network

import com.google.gson.annotations.SerializedName




data class Login (

	@SerializedName("user") val user : User,
	@SerializedName("token") val token : String
)