package com.example.uthmfoodordering.ViewHolder
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uthmfoodordering.Interface.ItemClickListener
import com.example.uthmfoodordering.R


class MenuViewHolder : RecyclerView.ViewHolder, View.OnClickListener {
    lateinit var txtMenuName: TextView
    lateinit var imageView: ImageView
    private lateinit var itemClickListener: ItemClickListener

    constructor(itemView: View) : super(itemView)
    {
        txtMenuName= itemView.findViewById(R.id.menu_name)
        imageView= itemView.findViewById(R.id.menu_image)

        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        itemClickListener.onClick(view,adapterPosition,false);
    }

    fun setItemClickListener(any: Any) {

    }

}