package com.example.pr9


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private lateinit var list:List<DataRow>//  = DataTable().getList()
    //fun getList()= DataTable().getList()
    fun setList(newList : List<DataRow>) {
        list = newList
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var cinema : TextView = itemView.findViewById(R.id.cinema)
        var film : TextView = itemView.findViewById(R.id.film)
        var hall : TextView = itemView.findViewById(R.id.hall)
        var date : TextView = itemView.findViewById(R.id.date)
        var time : TextView = itemView.findViewById(R.id.time)
        var index : TextView = itemView.findViewById(R.id.index)
        var photo : ImageView = itemView.findViewById(R.id.photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cinema.text = list[position].cinema
        holder.film.text = list[position].film
        holder.hall.text = list[position].hall.toString()
        holder.date.text = list[position].date
        holder.time.text = list[position].time
        holder.index.text = (position+1).toString()
        holder.photo.setImageBitmap(list[position].photo)
    }

    override fun getItemCount() = list.size


}