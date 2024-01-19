package com.example.uthmfoodordering.Interface
import android.view.View;

interface ItemClickListener {
    fun onClick(view: View, position: Int, isLongClick: Boolean)
}