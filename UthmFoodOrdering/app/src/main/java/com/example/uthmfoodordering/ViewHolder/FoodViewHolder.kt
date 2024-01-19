package com.example.uthmfoodordering.ViewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uthmfoodordering.Interface.ItemClickListener
import com.example.uthmfoodordering.R

class FoodViewHolder : RecyclerView.ViewHolder, View.OnClickListener {
    lateinit var food_name: TextView
    lateinit var food_image: ImageView
    private lateinit var itemClickListener: ItemClickListener

    constructor(itemView: View) : super(itemView)
    {
        food_name= itemView.findViewById(R.id.food_name)
        food_image= itemView.findViewById(R.id.food_image)

        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        itemClickListener.onClick(view,adapterPosition,false);
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {

    }

}