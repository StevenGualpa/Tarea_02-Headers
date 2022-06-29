package com.geek.prueba_tarea_02

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class customerAdapter constructor(context_: Context,
                                  did: List<String>,
                                  dinam: List<String>,
                                  didescription: List<String>,
                                  dimodified: List<String>,
                                  dithumbnail: List<String>) : RecyclerView.Adapter<customerAdapter.ViewHolder>() {

    val context: Context = context_

    //Creamos los list con valores por defectos para luego cambiarlos con los datos de la Api
    val datos_id = did
    val datos_name = dinam
    val datos_description = didescription
    val datos_modified = dimodified
    var datos_thumbnail = dithumbnail

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): customerAdapter.ViewHolder {
        val v =
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.cardview_personaje_descripcion_basica, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: customerAdapter.ViewHolder, i: Int) {

        viewHolder.itemid.text = datos_id.get(i)
        viewHolder.itemname.text = datos_name.get(i)
        viewHolder.itemdescription.text = datos_name.get(i)
        viewHolder.itemmodified.text = datos_modified.get(i)
      //  viewHolder.itemthumbnail.text = datos_thumbnail.get(i)
        var l="https://i.annihil.us/u/prod/marvel/i/mg/3/40/4bb4680432f73.jpg"

        Glide.with(context).load(l)
            .placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_background)
            .into(viewHolder.itemimage)
    }

    override fun getItemCount(): Int {
        return datos_id.count()
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        var itemimage: ImageView
        var itemid: TextView
        var itemname: TextView
        var itemdescription: TextView
        var itemmodified: TextView
       //var itemthumbnail: TextView
        init {
            itemimage=itemView.findViewById(R.id.item_image_personaje)
            itemid=itemView.findViewById(R.id.item_id_personaje)
            itemname=itemView.findViewById(R.id.item_name_personaje)
            itemdescription=itemView.findViewById(R.id.item_description_personaje)
            itemmodified=itemView.findViewById(R.id.item_modified_personaje)
         //   itemthumbnail=itemView.findViewById(R.id.item_thumbnail_personaje)
        }
    }
}
