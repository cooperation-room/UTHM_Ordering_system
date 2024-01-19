package com.example.uthmfoodordering

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uthmfoodordering.Interface.ItemClickListener
import com.example.uthmfoodordering.Model.Category
import com.example.uthmfoodordering.ViewHolder.MenuViewHolder
import com.example.uthmfoodordering.databinding.ActivityHomeBinding
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso


class Home : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding

    lateinit var database: FirebaseDatabase
    lateinit var category: DatabaseReference
    lateinit var recyclerMenu: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager

    private lateinit var adapter: FirebaseRecyclerAdapter<Category, MenuViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         binding = ActivityHomeBinding.inflate(layoutInflater)
         setContentView(binding.root)


        binding.appBarHome.toolbar.title = "Menu"
        setSupportActionBar(binding.appBarHome.toolbar)

        //InitFirebase
        database = FirebaseDatabase.getInstance()
        category = database.getReference("Category")

        binding.appBarHome.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_menu, R.id.nav_cart, R.id.nav_order, R.id.nav_logOut
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //Load menu
        recyclerMenu = findViewById<RecyclerView>(R.id.recycler_menu)
        recyclerMenu.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerMenu.layoutManager = layoutManager

        loadMenu()
    }

    private fun loadMenu() {
        // Build the FirebaseRecyclerOptions
        val options = FirebaseRecyclerOptions.Builder<Category>()
            .setQuery(category, Category::class.java)
            .build()

        // Create the FirebaseRecyclerAdapter
        val adapter = object : FirebaseRecyclerAdapter<Category, MenuViewHolder>(options) {
            override fun onBindViewHolder(holder: MenuViewHolder, position: Int, model: Category) {
                holder.txtMenuName.text = model.name
                Picasso.get().load(model.image).into(holder.imageView)

                val clickItem: Category = model
                holder.setItemClickListener(object : ItemClickListener {
                    override fun onClick(view: View, position: Int, isLongClick: Boolean) {
                        //Toast.makeText(this@Home, "" + model.image, Toast.LENGTH_SHORT).show()
                        // Get CategoryId and send food to new Activity
                        val foodList = Intent(this@Home, FoodList::class.java)
                        // Get key of the item
                        foodList.putExtra("CategoryId", adapter.getRef(position).key)
                        startActivity(foodList)


                    }
                })
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.menu_item, parent, false)
                return MenuViewHolder(view)
            }
        }

        // Set the adapter to the RecyclerView
        recyclerMenu.adapter = adapter

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}