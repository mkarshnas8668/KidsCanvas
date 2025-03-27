package com.mkarshnas6.karenstudio.kidscanvas

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mkarshnas6.karenstudio.kidscanvas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.dark_green)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.dark_green)

        binding.btnColoring.setOnClickListener { startActivity(Intent(this,ColoringActivity::class.java)) }
        binding.btnPainting.setOnClickListener { startActivity(Intent(this,PaintActivity::class.java)) }
        binding.btnLearning.setOnClickListener { startActivity(Intent(this,SelecteLearningActivity::class.java)) }

    }
}