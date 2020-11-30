package com.example.projectone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import com.example.projectone.network.*
import com.example.projectone.network.NetworkManager.Companion.fetchData
import kotlinx.android.synthetic.main.activity_add_post.*
import kotlinx.android.synthetic.main.activity_update_post.*
import kotlinx.android.synthetic.main.activity_update_post.bodyText
import kotlinx.android.synthetic.main.activity_update_post.titleText
import kotlinx.android.synthetic.main.row.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdatePost : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_post)

    }

    override fun onStart() {
        super.onStart()
        var textOne = intent?.extras?.getString("title")
        var textTwo = intent?.extras?.getString("body")

        titleText.setText(textOne)
        bodyText.setText(textTwo)
        var idPost = intent?.extras?.getString("idPost").toString()

        updateBtn.setOnClickListener {

            updatePost(idPost, AddTitle(bodyText.text.toString(), titleText.text.toString()))
        }
    }
        fun updatePost(id:String,AddTitle:AddTitle){

            fetchData(this).updatePost(id,AddTitle).enqueue(object:Callback<PostObject>{
                override fun onFailure(call: Call<PostObject>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<PostObject>,
                    response: Response<PostObject>
                ) {

                }
            })
        }
    }



      //  var addDate=AddTitle(textTitle.toString(),textBody.toString())





