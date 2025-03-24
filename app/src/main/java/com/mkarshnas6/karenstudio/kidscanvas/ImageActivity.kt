package com.mkarshnas6.karenstudio.kidscanvas

import android.app.AlertDialog
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.TextView
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
        binding.btnDelete.setOnClickListener { deleteCanvas() }
        binding.btnEraser.setOnClickListener {
            if (EraserIsEnable == false) {
                selectedColor = Color.WHITE
                coloringView.setColor(Color.WHITE)
                EraserIsEnable = true
                it.setBackgroundColor(getColor(R.color.light_green))
            } else {
                val previousColor =
                    (binding.btnSelectColor.drawable as? ColorDrawable)?.color ?: Color.RED
                selectedColor = previousColor
                coloringView.setColor(previousColor)
                binding.btnSelectColor.setColorFilter(selectedColor)
                EraserIsEnable = false
                it.setBackgroundColor(getColor(R.color.None))
            }
        }
    }

    private fun openColorPicker() {
        if (!::coloringView.isInitialized) return

        if (EraserIsEnable) {
            selectedColor = R.color.red
            coloringView.setColor(R.color.red)
        }

        val colorPicker =
            AmbilWarnaDialog(this, selectedColor, object : AmbilWarnaDialog.OnAmbilWarnaListener {
                override fun onCancel(dialog: AmbilWarnaDialog?) {}

                override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                    if (color != Color.BLACK) {
                        selectedColor = color
                        coloringView.setColor(selectedColor)
                        binding.btnSelectColor.setColorFilter(color)
                    } else {
                        Toast.makeText(
                            this@ImageActivity,
                            "لطفا از رنگ های مشابه استفاده کنید .",
                            Toast.LENGTH_LONG
                        ).show()
                        openColorPicker()
                    }

                    binding.btnEraser.setBackgroundColor(getColor(R.color.None))
                    EraserIsEnable = false

                }

            })
        colorPicker.show()
    }

    fun deleteCanvas() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.custom_dialog_layout, null)
        builder.setView(dialogView)
            .setCancelable(true)

        val alertDialog = builder.create()
        val btnYes: TextView = dialogView.findViewById(R.id.btn_yes)
        val btnNo: TextView = dialogView.findViewById(R.id.btn_no)

        btnYes.setOnClickListener {
            binding.coloringView.deleteCanvas()
            alertDialog.dismiss()
        }
        btnNo.setOnClickListener {
            alertDialog.dismiss()
        }
        alertDialog.show()
    }

}
