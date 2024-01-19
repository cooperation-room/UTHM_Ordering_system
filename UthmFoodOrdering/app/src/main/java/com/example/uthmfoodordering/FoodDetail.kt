package com.example.uthmfoodordering

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton
import com.example.uthmfoodordering.Model.Food
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class FoodDetail : AppCompatActivity() {

    var food_name: TextView? = null
    var food_price: android.widget.TextView? = null
    var food_description: android.widget.TextView? = null
    var food_image: ImageView? = null
    var collapsingToolbarLayout: CollapsingToolbarLayout? = null
    var btnCart: FloatingActionButton? = null
    var numberButton: ElegantNumberButton? = null

    var foodId = ""
    var currentFood: Food? = null


    lateinit var database: FirebaseDatabase
    lateinit var foods: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_detail)

        // Init Firebase
        database = FirebaseDatabase.getInstance();
        foods = database.getReference("Foods")

        /*init view
        numberButton = findViewById(android.R.id.number_button);
        btnCart = findViewById(android.R.id.btnCart);

        btnCart.setOnClickListener(new View . OnClickListener () {
            @Override
            public void onClick(View v) {
                new Database baseContext.addToCart(
                    new Order foodId,
                    currentFood.getName(),
                    numberButton.getNumber(),
                    currentFood.getPrice(),
                    currentFood.getDiscount()
                ));
                Toast.makeText(FoodDetails.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });

        food_name = findViewById(android.R.id.food_name);
        food_image = findViewById(android.R.id.img_food);
        food_description = findViewById(android.R.id.food_description);
        food_price = findViewById(android.R.id.food_price);

        collapsingToolbarLayout = findViewById(android.R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(android.R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(android.R.style.CollapsedAppbar);

        // get food id from intent
        if (intent != null)
            foodId = intent.getStringExtra("FoodId")!!;
        if (!foodId.isEmpty() && foodId != null)
            if (Common.isConnectedToInternet(baseContext))
                getDetailFood(foodId);
            else {
                Toast.makeText(this, "Please Check the Internet Connection", Toast.LENGTH_SHORT)
                    .show();
                return;
            }


    }

    private fun getDetailFood(foodId: String) {
        foods.child(foodId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                currentFood = dataSnapshot.getValue(Food::class.java)

                // setting the image from firebase into appbar;
                Picasso.get().load(currentFood!!.Image).into(food_image)
                //set title in appbar
                collapsingToolbarLayout!!.title = currentFood!!.Name
                food_price.setText(currentFood.getPrice())
                food_description.setText(currentFood.getDescription())
                food_name!!.text = currentFood!!.Name
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }*/
    }
}