package com.example.rectovideo

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.rectovideo.Game as Game

import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    internal var games: ArrayList<Game> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // GET THE DATA FROM THE API
        // DEFINE THE LAYOUT MANAGER
        setContentView(R.layout.activity_main)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation= LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = GameListAdapter(games)
        fetchData(this)
        itemsswipetorefresh.setOnRefreshListener {
            fetchData(this)
        }
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
    }

    // Get the data from the API
    private fun fetchData(context: Context){

        val queue = Volley.newRequestQueue(context)
        val jsonObjectRequest = JsonArrayRequest(Request.Method.GET, URL, null,
            Response.Listener<JSONArray> { response: JSONArray ->
                for (i in 0 until response.length()){
                    val sObject = response.get(i).toString()
                    val mItemObject = JSONObject(sObject)

                    val name = mItemObject.getString("name")
                    val description = mItemObject.getString("description")
                    val image = mItemObject.getString("img")
                    val id = mItemObject.getInt("id")

                    val game = Game(name, description, image, id)

                    games.add(game)
                    recyclerView.adapter?.notifyDataSetChanged()
                    itemsswipetorefresh.isRefreshing = false
                }
            },
            Response.ErrorListener { error ->
                Log.e("FetchData", error.message.toString())
            }
        )
        queue.add(jsonObjectRequest)

    }

    companion object{
        const val URL = "https://my-json-server.typicode.com/bgdom/cours-android/games"
    }


}
