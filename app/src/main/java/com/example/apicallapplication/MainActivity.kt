package com.example.apicallapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.apicallapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.button.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.getvisitapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val api = retrofit.create(Api::class.java)
            val registrationPayload = JSONObject()
            try {
                registrationPayload.put("password", "123123")
                registrationPayload.put("email", "doc@doc.com")
                registrationPayload.put("platform", "ANDROID")
            } catch (e: Exception) {
                e.printStackTrace()
                binding.textView.text = e.message.toString()
            }

            val call = api.authenticate(registrationPayload)
            call.enqueue(object : Callback<JSONObject>{
                override fun onResponse(call: Call<JSONObject>, response: Response<JSONObject>) {
                    binding.textView.text = response.errorBody().toString() + "  response"
                }

                override fun onFailure(call: Call<JSONObject>, t: Throwable) {
                    binding.textView.text = t.message.toString()
                }
            })
            /*val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("")
                .build()

            val api = retrofit.create(Api::class.java)

            val call = api.getHarry()

            call.enqueue(object : Callback<List<JSONObject>>{
                override fun onResponse(
                    call: Call<List<JSONObject>>,
                    response: Response<List<JSONObject>>
                ) {
                    binding.textView.text = response.message().toString()
                }

                override fun onFailure(call: Call<List<JSONObject>>, t: Throwable) {
                    binding.textView.text = t.message.toString()
                }

            })*/
            /*val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val api = retrofit.create(Api::class.java)
            val registrationPayload = JSONObject()
            try {
                registrationPayload.put("title", "foo")
                registrationPayload.put("body", "bar")
                registrationPayload.put("userId", 1)
            } catch (e: Exception) {
                e.printStackTrace()
                binding.textView.text = e.message.toString()
            }
            val call = api.posts(registrationPayload)
            call.enqueue(object : Callback<JSONObject>{
                override fun onResponse(call: Call<JSONObject>, response: Response<JSONObject>) {
                    binding.textView.text = response.code().toString()
                }

                override fun onFailure(call: Call<JSONObject>, t: Throwable) {
                    binding.textView.text = t.message.toString()
                }
            })*/

        }
    }
}