package com.mkarshnas6.karenstudio.kidscanvas

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mkarshnas6.karenstudio.kidscanvas.databinding.ActivityColoringBinding
import com.mkarshnas6.karenstudio.kidscanvas.databinding.ActivityPaintBinding

class ColoringActivity : AppCompatActivity() {

    private lateinit var binding: ActivityColoringBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityColoringBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_coloring)



    }
}