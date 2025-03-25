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
            ImageItem("گل 1", "flower1"),
            ImageItem("مرد عنکبوتی 1", "spiderman1"),
            ImageItem("مرد عنکبوتی 2", "spiderman2"),
            ImageItem("مرد عنکبوتی 3", "spiderman3"),
            ImageItem("مرد عنکبوتی 4", "spiderman4"),
            ImageItem("شیر 1", "line1"),
            ImageItem("شیر 2", "lion2"),
            ImageItem("پرنده 1", "bird1"),
            ImageItem("قروزن 1", "frozen1"),
            ImageItem("فروزن 2", "frozen2"),
            ImageItem("مینیون 1", "minion1"),
            ImageItem("مینیون 2", "minion2"),
            ImageItem("پاندا 1", "panda1"),
            ImageItem("پاندا 2", "panda2"),
            ImageItem("کیتی 1", "kitty1"),
            ImageItem("کیتی 2", "kitty2"),
            ImageItem("کیتی 3", "kitty3"),
            ImageItem("کیتی 4", "kitty4"),
            ImageItem("گروت 1", "groot1"),
            ImageItem("ایرون من 1", "ironman1"),
            ImageItem("ایرون من 2", "ironman2"),
            ImageItem(" سونیک 1", "sonic1"),
            ImageItem("سونیک 2", "sonic2"),
            ImageItem("سونیک 3", "sonic3"),
            ImageItem("میکی موس 1", "mickeymouse1"),
            ImageItem("میکی موس 2", "mickeymouse2"),
            ImageItem("سوپر ماریو 1", "supermario1"),
            ImageItem("اساسین کرید 1", "assassinscreed1"),
            ImageItem("ریک اند مورتی 1", "rickandmorty1"),
            ImageItem("اسنوپی 1", "snoopy1"),
            ImageItem("اسنوپی 2", "snoopy2"),
            ImageItem("اسنوپی 3", "snoopy3"),

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
