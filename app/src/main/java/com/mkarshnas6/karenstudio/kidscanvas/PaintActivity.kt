package com.mkarshnas6.karenstudio.kidscanvas

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mkarshnas6.karenstudio.kidscanvas.databinding.ActivityPaintBinding
import yuku.ambilwarna.AmbilWarnaDialog

class PaintActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaintBinding
    private var selectedColor: Int = Color.BLACK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelectColor.setOnClickListener { openColorPicker() }

    }

    //    .......... dialog for select color .......................
    private fun openColorPicker() {
        val colorPicker =
            AmbilWarnaDialog(this, selectedColor, object : AmbilWarnaDialog.OnAmbilWarnaListener {
                override fun onCancel(dialog: AmbilWarnaDialog?) {}

                override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                    if (color == Color.WHITE) {
                        binding.btnSelectColor.setBackgroundColor(getColor(R.color.black))
                        selectedColor = color
                        binding.coloringView.setColor(selectedColor)
                        binding.btnSelectColor.setColorFilter(selectedColor)
                    } else {
                        binding.btnSelectColor.setBackgroundColor(getColor(R.color.None))
                        selectedColor = color
                        binding.coloringView.setColor(selectedColor)
                        binding.btnSelectColor.setColorFilter(selectedColor)
                    }
                }
            })
        colorPicker.show()
    }

}