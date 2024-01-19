package com.example.uthmfoodordering

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uthmfoodordering.Interface.ItemClickListener
import com.example.uthmfoodordering.Model.Food
import com.example.uthmfoodordering.ViewHolder.FoodViewHolder
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class FoodList : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager

    lateinit var database: FirebaseDatabase
    lateinit var foodList: DatabaseReference
    private var categoryId: String = ""

    private lateinit var adapter: FirebaseRecyclerAdapter<Food, FoodViewHolder>
    private lateinit var options: FirebaseRecyclerOptions<Food>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_list)

        //Firebase
        database = FirebaseDatabase.getInstance()
        foodList = database.getReference("Foods")

        recyclerView = findViewById<RecyclerView>(R.id.recycler_food)
        recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        //Get Intent here
        if (intent != null)
            categoryId = intent.getStringExtra("CategoryId").toString()
        if (!categoryId.isEmpty() && categoryId != null) {
            loadListFood(categoryId)
        }

        /* Get Intent here backup code
        val intent = intent
        val categoryId = intent?.getStringExtra("CategoryId")
        if (!categoryId.isNullOrEmpty()) {
            loadListFood(categoryId)
        }*/

    }

    private fun loadListFood(categoryId: String) {

        val options = FirebaseRecyclerOptions.Builder<Food>()
            .setQuery(foodList.orderByChild("MenuId").equalTo(categoryId), Food::class.java)
            .build()
        // Create the FirebaseRecyclerAdapter
        adapter = object : FirebaseRecyclerAdapter<Food, FoodViewHolder>(options) {
            override fun onBindViewHolder(
                viewHolder: FoodViewHolder,
                position: Int,
                model: Food
            ) {
                viewHolder.food_name.setText(model.Name);
                Picasso.get().load(model.Image).into(viewHolder.food_image)

                val local: Food = model

                viewHolder.setItemClickListener(object : ItemClickListener {
                    override fun onClick(view: View, position: Int, isLongClick: Boolean) {
                        Toast.makeText(this@FoodList, "" + local.Name, Toast.LENGTH_SHORT).show()
                    }
                })
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.food_item, parent, false)
                return FoodViewHolder(view)
            }

        }
        // Set the adapter to the RecyclerView
        Log.d("TAG",""+adapter.itemCount)
        recyclerView.adapter = adapter
    }
}