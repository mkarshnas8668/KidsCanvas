package com.mkarshnas6.karenstudio.kidscanvas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.adivery.sdk.Adivery
import com.adivery.sdk.AdiveryAdListener
import com.mkarshnas6.karenstudio.kidscanvas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var lastPauseTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        ......... for AD .......................................
        Adivery.configure(application, "1d85f173-8ff4-45a2-9bee-650334225883")
        Adivery.prepareAppOpenAd(this, "12109695-2a0f-4f95-ae0c-2e66a33ca60a")
        val bannerAd_top = binding.bannerAdTop
        val bannerAd_bottom = binding.bannerAdBottom

//        banner top
        bannerAd_top.setBannerAdListener(object : AdiveryAdListener() {
            override fun onError(reason: String) {
                Log.e("adivary", "${reason}")
            }

            override fun onAdClicked() {
                Toast.makeText(
                    this@MainActivity,
                    "خیلی ممنون که کلیک کردی ❤ :)",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
//        banner bottom
        bannerAd_bottom.setBannerAdListener(object : AdiveryAdListener() {
            override fun onError(reason: String) {
                Log.e("adivary", "${reason}")
            }

            override fun onAdClicked() {
                Toast.makeText(
                    this@MainActivity,
                    "خیلی ممنون که کلیک کردی ❤ :)",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        bannerAd_top.loadAd()
        bannerAd_bottom.loadAd()

        if (Adivery.isLoaded("12109695-2a0f-4f95-ae0c-2e66a33ca60a")) {
            Adivery.showAppOpenAd(this, "12109695-2a0f-4f95-ae0c-2e66a33ca60a")
        }

//        ............. End AD .......................................

        window.statusBarColor = ContextCompat.getColor(this, R.color.dark_green)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.dark_green)

        binding.btnColoring.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ColoringActivity::class.java
                )
            )
        }
        binding.btnPainting.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    PaintActivity::class.java
                )
            )
        }
        binding.btnLearning.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SelecteLearningActivity::class.java
                )
            )
        }

    }

    override fun onResume() {
        super.onResume()
        val pauseTime = System.currentTimeMillis() - lastPauseTime
        if (pauseTime > 5000) { // بیشتر از ۵ ثانیه گذشته
            if (Adivery.isLoaded("12109695-2a0f-4f95-ae0c-2e66a33ca60a")) {
                Adivery.showAppOpenAd(this, "12109695-2a0f-4f95-ae0c-2e66a33ca60a")
            }
        }
    }

    override fun onPause() {
        super.onPause()
        lastPauseTime = System.currentTimeMillis()
    }

}