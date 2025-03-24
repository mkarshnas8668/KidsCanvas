package com.mkarshnas6.karenstudio.kidscanvas

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkarshnas6.karenstudio.kidscanvas.databinding.ActivityColoringBinding

class ColoringActivity : AppCompatActivity() {

    private lateinit var binding: ActivityColoringBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            Toast.makeText(this, "Clicked on ${item.persianName}", Toast.LENGTH_SHORT).show()
        }

        // Setting the adapter to the RecyclerView
        binding.recyclerview.adapter = adapter
    }
}
