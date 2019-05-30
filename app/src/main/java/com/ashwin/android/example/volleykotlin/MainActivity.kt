package com.ashwin.android.example.volleykotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var queue: RequestQueue
    val url = "https://gist.githubusercontent.com/ashwindmk/1e2097ac3de60a40c469ec1a60f35b41/raw/profile.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        queue = Volley.newRequestQueue(this)

        swiperefresh_layout.setOnRefreshListener {
            request()
        }
    }

    private fun request() {
        response_textview.text = "Fetching..."

        val jsonRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener<JSONObject> { response ->
                response_textview.text = "${response.toString(2)}"
                swiperefresh_layout.isRefreshing = false
            },
            Response.ErrorListener {
                response_textview.text = "That didn't work!"
                swiperefresh_layout.isRefreshing = false
            }
        )

        jsonRequest.setShouldCache(false)

        queue.add(jsonRequest)
    }
}
