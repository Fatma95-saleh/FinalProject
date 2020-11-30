package com.example.projectone.network

import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @POST("login")
    fun login(@Body data:data): Call<Login>

     @GET("posts")
    fun getPosts():Call<Posts>

    @POST("posts")
    fun addPost(@Body AddTitle:AddTitle):Call<PostObject>


    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id:String):Call<Posts>

    @PUT("posts/{id}")
    fun updatePost(@Path("id")id:String,@Body AddTitle: AddTitle):Call<PostObject>


}