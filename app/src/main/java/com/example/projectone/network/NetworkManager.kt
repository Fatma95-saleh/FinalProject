package com.example.projectone.network

import android.content.Context
import android.preference.PreferenceManager
import com.example.projectone.HomeActivity
import com.example.projectone.PostsAdapter
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class NetworkManager {

companion object{
    fun fetchData(context: Context):ApiInterface {
         val httpClient =  OkHttpClient.Builder()
        val preferences =
            PreferenceManager.getDefaultSharedPreferences(context)
        httpClient.addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val token = preferences.getString("token","")

                val original: Request = chain.request()
                val request: Request = original.newBuilder()
                    .header("Authorization","Bearer $token")
                    .header("Accept", "application/json")
                    .method(original.method(), original.body())
                    .build()
                return chain.proceed(request)
            }
        })
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL).client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiInterface::class.java)
     }
   }
}
