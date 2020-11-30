package com.example.projectone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.projectone.network.AddTitle
import com.example.projectone.network.NetworkManager
import com.example.projectone.network.NetworkManager.Companion.fetchData
import com.example.projectone.network.PostObject
import kotlinx.android.synthetic.main.activity_add_post.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.projectone.network.PostData as PostData

class AddPost : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)



    }

    override fun onStart() {
        super.onStart()

        addBtn.setOnClickListener {

            addPost(AddTitle(titleText.text.toString(),bodyText.text.toString()))
        }

    }



    fun addPost(AddTitle: AddTitle){


        val call=  fetchData(this).addPost(AddTitle)

        var item=call.enqueue(object:Callback<PostObject> {

            override fun onResponse(call: Call<PostObject>, response: Response<PostObject>) {
            //    var test  =response.body()?..let { Log.e("Response is::: ", it) }

              //  resText.text= response.body()?.data?.created_at


            }
            override fun onFailure(call: Call<PostObject>, t: Throwable) {
              //  resText.text= "error"
            }
        })
    }

}