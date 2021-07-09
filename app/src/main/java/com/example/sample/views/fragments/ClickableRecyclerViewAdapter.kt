package com.example.sample.views.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.animation.core.animateDpAsState
import androidx.recyclerview.widget.RecyclerView
import com.example.sample.R
import com.example.sample.views.model.Movie

class ClickableRecyclerViewAdapter(private var items: ArrayList<Movie>) :
    RecyclerView.Adapter<ClickableRecyclerViewAdapter.ViewHolder>() {

    var onItemClick: ((Movie,Int) -> Unit)? = null

    fun setItems(items: List<Movie>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var movie = items[position]

        holder?.txtTitle?.text = movie.title
        holder?.txtCast?.text = movie.cast.toString()
        holder?.txtYear?.text = movie.year.toString()
        holder?.txtGenres?.text = movie.genres.toString()
        holder?.txtRating?.text = movie.rating.toString()

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTitle: TextView? = null
        var txtCast: TextView? = null
        var txtYear: TextView? = null
        var txtGenres: TextView? = null
        var txtRating: TextView? = null

        init {

            this.txtTitle = itemView?.findViewById(R.id.txt_title)
            this.txtCast = itemView?.findViewById(R.id.txt_cast)
            this.txtYear = itemView?.findViewById(R.id.txt_year)
            this.txtGenres = itemView?.findViewById(R.id.txt_genres)
            this.txtRating = itemView?.findViewById(R.id.txt_rating)

            itemView.setOnClickListener {
                onItemClick?.invoke(items[adapterPosition], adapterPosition)
            }

        }
    }


}