package com.mkarshnas6.karenstudio.kidscanvas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkarshnas6.karenstudio.kidscanvas.databinding.ActivityColoringBinding

class ColoringActivity : AppCompatActivity() {

    private lateinit var binding: ActivityColoringBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        ......... change color status bar and navigation mob ..............................
        window.statusBarColor = ContextCompat.getColor(this, R.color.dark_green)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.dark_green)


        // Binding initialization
        binding = ActivityColoringBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Creating a list of ImageItems (with Persian and English names)
        val imageList = listOf(
            ImageItem("گل 1", "flower1")
        )

        // Set the LayoutManager for the RecyclerView
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        // Creating the adapter and setting onClick action
        val adapter = ColoringAdapter(imageList) { item ->
            val intent = Intent(this, ImageActivity::class.java)
            intent.putExtra("image_name", item.englishName)
            startActivity(intent)
        }

        // Setting the adapter to the RecyclerView
        binding.recyclerview.adapter = adapter
    }
}
