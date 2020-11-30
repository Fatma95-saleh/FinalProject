package com.example.projectone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectone.network.NetworkManager.Companion.fetchData
import com.example.projectone.network.Post
import com.example.projectone.network.Posts
// import com.example.projectone.network.Posts
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    lateinit var adapter : PostsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        adapter = PostsAdapter(this)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter= adapter
    }


    override fun onStart() {
        super.onStart()
       addPostBtn.setOnClickListener {
            val intent = Intent(this,AddPost::class.java)
            startActivity(intent)
        }
        fetchData(this).getPosts()?.enqueue(object: Callback<Posts> {
            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                Log.e("response::", response.body().toString())
                val dataArr = response.body()
                adapter.setData(dataArr?.data as ArrayList<Post>?)
            }
            override fun onFailure(call: Call<Posts>, t: Throwable) {
                Log.e("err", t.message!!)
            }
        })
    }
}