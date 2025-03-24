package com.mkarshnas6.karenstudio.kidscanvas

import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.mkarshnas6.karenstudio.kidscanvas.databinding.ActivityImageBinding
import com.mkarshnas6.karenstudio.kidscanvas.models.ColoringView
import yuku.ambilwarna.AmbilWarnaDialog

class ImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageBinding
    private var selectedColor: Int = Color.RED
    private lateinit var coloringView: ColoringView
    private var EraserIsEnable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        coloringView = findViewById(R.id.coloring_view)
        binding.btnSelectColor.setColorFilter(Color.RED)

//        ......... change color status bar and navigation mob ..............................
        window.statusBarColor = ContextCompat.getColor(this, R.color.dark_green)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.dark_green)

        val imageName = intent.getStringExtra("image_name")
        if (imageName != null) {
            val resId = resources.getIdentifier("img_$imageName", "drawable", packageName)
            if (resId != 0) {
                val bitmap = BitmapFactory.decodeResource(resources, resId)
                binding.coloringView.setBitmap(bitmap)
            }
        }

        binding.btnSelectColor.setOnClickListener { openColorPicker() }
    }

    private fun openColorPicker() {
        if (!::coloringView.isInitialized) return

        val colorPicker =
            AmbilWarnaDialog(this, selectedColor, object : AmbilWarnaDialog.OnAmbilWarnaListener {
                override fun onCancel(dialog: AmbilWarnaDialog?) {}

                override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                    if (color != Color.BLACK) {
                        selectedColor = color
                        coloringView.setColor(selectedColor)
                        binding.btnSelectColor.setColorFilter(color)
                    } else {
                        Toast.makeText(this@ImageActivity, "لطفا از رنگ های مشابه استفاده کنید .", Toast.LENGTH_LONG).show()
                        openColorPicker()
                    }
                }

            })
        colorPicker.show()
    }

}
