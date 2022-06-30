package com.example.a13volleyjsonarrayrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.a13volleyjsonarrayrequest.adapters.UsersAdapter
import com.example.a13volleyjsonarrayrequest.databinding.ActivityMainBinding
import com.example.a13volleyjsonarrayrequest.models.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var requestQueue: RequestQueue

    val url = "https://jsonplaceholder.typicode.com/users"
    private  val TAG = "MainActivity"
    lateinit var usersAdapter: UsersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestQueue = Volley.newRequestQueue(this)

        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET,url,null,object :Response.Listener<JSONArray>{
            override fun onResponse(response: JSONArray?) {
                val  type = object :TypeToken<List<User>>(){}.type
                var  list:List<User> = Gson().fromJson(response.toString(),type)

                usersAdapter = UsersAdapter(list)
                binding.rv.adapter = usersAdapter
            }
        },object :Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?) {
                Toast.makeText(this@MainActivity, "Internet ulanmagan", Toast.LENGTH_SHORT).show()
            }
        })
        requestQueue.add(jsonArrayRequest)
    }
}