package com.mkarshnas6.karenstudio.kidscanvas

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mkarshnas6.karenstudio.kidscanvas.databinding.ActivityFullScreenBinding

class FullScreen : AppCompatActivity() {

    private lateinit var binding: ActivityFullScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = (
                android.view.View.SYSTEM_UI_FLAG_FULLSCREEN or
                        android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )

        binding = ActivityFullScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgIc.animate().alpha(1f).setDuration(1500).start()
        binding.txtTitle.animate().alpha(1f).setDuration(2500).start()
        binding.txtSlogan.animate().alpha(1f).setDuration(2500).start()

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2500)

    }
}