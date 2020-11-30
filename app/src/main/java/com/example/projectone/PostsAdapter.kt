package com.example.projectone

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectone.network.NetworkManager.Companion.fetchData
import com.example.projectone.network.Post
import com.example.projectone.network.Posts
import kotlinx.android.synthetic.main.row.view.*
import retrofit2.Call
import retrofit2.Response



class PostsAdapter(private var context: Context) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    var arr = ArrayList<Post>()
    fun setData(arr: ArrayList<Post>?){
        if (arr != null) {
            this.arr = arr
        notifyDataSetChanged()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.row,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return arr?.size!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val post:Post= arr[position]
        holder.title.text= "Title: " + post.title
        holder.body.text="Body: "+ post.body
        holder.authorId.text= "ID:" + post.id
        holder.createAt.text=post.created_at
        holder.updateAt.text=post.updated_at

        holder.cardView.setOnClickListener {
         val intent = Intent(context,UpdatePost::class.java)
           intent.putExtra("title",post.title)
           intent.putExtra("body",post.body)
            intent.putExtra("idPost",post.id)

           context.startActivity(intent)


       }

        holder.deleteIcon.setOnClickListener {

          // holder.jj.text=position.toString()

            arr[position].id?.let { it1 ->
                fetchData(context).deletePost(it1).enqueue(object :retrofit2.Callback<Posts>{


                    override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                        arr.removeAt(position)
                        notifyItemRemoved(position)


                    }

                    override fun onFailure(call: Call<Posts>, t: Throwable) {

                    }
                })
            }


        }
    }


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val title=itemView.title as TextView
        val body=itemView.body as TextView
        val authorId=itemView.authorId as TextView
        val createAt=itemView.createAt as TextView
        val updateAt=itemView.updateAt as TextView
       val cardView = itemView.cardView as CardView
       val deleteIcon=itemView.deleteIcon as ImageView


    }

}