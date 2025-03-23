package com.mkarshnas6.karenstudio.kidscanvas

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mkarshnas6.karenstudio.kidscanvas.databinding.ActivityPaintBinding

class PaintActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPaintBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaintBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}