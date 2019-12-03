package com.example.practicaldemo.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaldemo.model.Movie
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.practicaldemo.R


class MovieAdapter(private val context: Context, private val list: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(position,holder)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       public var textTitle: TextView
       public var textRating: TextView
       public var textYear: TextView

        init {
            textTitle = itemView.findViewById(R.id.main_title)
            textRating = itemView.findViewById(R.id.main_rating)
            textYear = itemView.findViewById(R.id.main_year)
        }

        fun bindData(position: Int,holder:ViewHolder){
            textTitle.text=list[position].title
            textRating.text=list[position].rating.toString()
            textYear.text=list[position].year.toString()

        }
    }
}