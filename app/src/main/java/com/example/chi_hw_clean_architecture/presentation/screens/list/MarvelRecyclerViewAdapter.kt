package com.example.chi_hw_clean_architecture.presentation.screens.list

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.chi_hw_clean_architecture.data.model.Marvel
import com.example.chi_hw_clean_architecture.databinding.FragmentMarvelBinding
import com.example.chi_hw_clean_architecture.presentation.model.MarvelPresentationModel
import com.squareup.picasso.Picasso

class MarvelRecyclerViewAdapter(
    private val values: List<MarvelPresentationModel>
) : RecyclerView.Adapter<MarvelRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentMarvelBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.name.text = item.name
        holder.altName.text = item.realname
        Picasso.get().load(item.imageurl).into(holder.img)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentMarvelBinding) : RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.name
        val altName: TextView = binding.altName
        val img: ImageView = binding.image

        override fun toString(): String {
            return super.toString() + " '" + name.text + "'"
        }
    }

}