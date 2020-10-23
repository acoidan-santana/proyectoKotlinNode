package com.example.videoclub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.videoclub.models.Peli

class VideoclubAdapter(var peliList: ArrayList<Peli>, val context: Context) : RecyclerView.Adapter<VideoclubAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.videoclub_list_row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(peliList[position], context)
    }

    override fun getItemCount(): Int {
        return peliList.size;
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindView(p: Peli, context: Context){
            val txtNombre: TextView = itemView.findViewById(R.id.textViewNombre)
            val txtAnio: TextView = itemView.findViewById(R.id.textViewPalabraAnio)
            val txtDirector: TextView = itemView.findViewById(R.id.textViewPalabraDirector)

            txtNombre.text = p.nombre
            txtAnio.text = p.anio.toString()
            txtDirector.text = p.director
        }
    }
}