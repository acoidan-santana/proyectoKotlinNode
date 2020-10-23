package com.example.videoclub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.videoclub.models.Peli
import org.json.JSONException

class VideoclubListActivity : AppCompatActivity() {
    private lateinit var pelis: ArrayList<Peli>
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: VideoclubAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videoclub_list1)

        requestQueue = Volley.newRequestQueue(this)

        pelis = ArrayList<Peli>()

        viewManager = LinearLayoutManager(this)

        viewAdapter = VideoclubAdapter(pelis, this)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewVideoclub)

        recyclerView.layoutManager = viewManager

        recyclerView.adapter = viewAdapter

        getAllPelis()
    }

    private fun getAllPelis(){
        val url = "http://192.168.0.187:8080/api/videoclub"
        val request =
                JsonArrayRequest(Request.Method.GET, url, null, { response ->
                    try {
                        Log.v("hola caracola", response.toString())
                        for (i in 0 until response.length()) {
                            val peli = response.getJSONObject(i)
                            val id = peli.getInt("id")
                            val nombre = peli.getString("nombre")
                            val anio = peli.getInt("anio")
                            val director = peli.getString("director")
                            pelis.add(Peli(id, nombre, anio, director))
                            Log.v("hola caracola", pelis.get(i).id.toString())
                        }
                        viewAdapter.peliList = pelis
                        viewAdapter.notifyDataSetChanged()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }, { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }
}