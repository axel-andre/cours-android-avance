package com.example.rectovideo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class GameListAdapter(private val games:  ArrayList<Game>): RecyclerView.Adapter<GameListAdapter.GameListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameListViewHolder{
        val viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_layout, parent, false)
        return GameListViewHolder(viewHolder)
    }

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: GameListViewHolder, position: Int) = holder.bind(games[position].name,games[position].image)

    class GameListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val Name: TextView = itemView.findViewById(R.id.name)
        private val Image: ImageView = itemView.findViewById(R.id.img)

        fun bind(name: String, imageURL: String){
            // Change the TextView name
            Name.text= name
            // Define the ImageView image data
            Picasso.get().load(imageURL).into(Image)
        }

    }
}