@file:Suppress("DEPRECATION")

package com.example.practicaldemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practicaldemo.model.Movie
import com.example.practicaldemo.adapter.MovieAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import com.android.volley.toolbox.Volley
import com.android.volley.VolleyError
import org.json.JSONException
import org.json.JSONArray
import com.android.volley.toolbox.JsonArrayRequest
import android.app.ProgressDialog
import android.util.Log
import android.view.View
import com.android.volley.Response


class MainActivity : AppCompatActivity() {

    lateinit var movieList: ArrayList<Movie>
    lateinit var adapter: MovieAdapter
    var url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        url = "http://192.168.43.196/Volley/data.json"
        getData()
        setAdapter()
        data(10,20)
        data(10,"ss")
    }

    private fun setAdapter() {
        movieList = ArrayList<Movie>()
        adapter = MovieAdapter(applicationContext, movieList)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun getData() {
        progressBar.visibility=View.VISIBLE

        val jsonArrayRequest = JsonArrayRequest(url,
            Response.Listener<JSONArray> { response ->
                for (i in 0 until response.length()) {
                    try {
                        val jsonObject = response.getJSONObject(i)

                        val movie = Movie(
                            jsonObject.getString("title"),
                            jsonObject.getInt("rating"),
                            jsonObject.getInt("releaseYear")
                        )

                        movieList.add(movie)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        progressBar.visibility=View.GONE

                    }

                }
                adapter.notifyDataSetChanged()
                progressBar.visibility=View.GONE
            }, Response.ErrorListener { error ->
            Log.e("Volley", error.toString())
            progressBar.visibility=View.GONE
        })
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(jsonArrayRequest)
    }


     fun data(a:Int,b:Int){

     }

     fun data(a:Int,b:String){

     }
}
