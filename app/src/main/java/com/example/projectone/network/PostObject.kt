
package com.example.projectone.network

import com.google.gson.annotations.SerializedName
import java.lang.reflect.Constructor

data class PostObject (

	@SerializedName("body") val body : String,
	@SerializedName("title") val title : String,
	@SerializedName("author_id") val author_id : Int,
	@SerializedName("updated_at") val updated_at : String,
	@SerializedName("created_at") val created_at : String,
	@SerializedName("id") val id : Int)