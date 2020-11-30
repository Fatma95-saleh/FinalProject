package com.example.projectone

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.projectone.network.Login
import com.example.projectone.network.NetworkManager.Companion.fetchData
import com.example.projectone.network.data
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
   }


    override fun onStart() {
        super.onStart()

        loginBtn.setOnClickListener {
            val phoneId=phoneId.text
            val passwordId=passwordId.text
            val passwordREGEX = Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$")

            if(phoneId.isEmpty() && passwordId.isEmpty()){bothText.text="Phone Number and Password is Required"}

//                    && passwordREGEX.matcher(passwordId).matches()
         // if (phoneId.isNotEmpty()&& phoneId.length==11 && passwordId.isNotEmpty()&&passwordId.length==8 ){
              val data = data(phoneId.toString(),passwordId.toString())
          // val data = data("user@user.com","12345678")

            login(data)

          //}
        }
    }

    private fun login(data:data){
        val call=  fetchData(this).login(data)
        val preferences =
            PreferenceManager.getDefaultSharedPreferences(this)
        var item=call.enqueue(object: Callback<Login> {
            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                response.body()?.user?.email?.let { Log.e("Response is::: ", it) }
                val token = response.body()?.token
                val editor = preferences.edit()
                editor.putString("token", token)
                editor.commit()

                val intent = Intent(applicationContext,HomeActivity::class.java)
                startActivity(intent)
                finish()

            }
            override fun onFailure(call: Call<Login>, t: Throwable) {

            }
        })
       }
    }

