package com.mkarshnas6.karenstudio.kidscanvas

import android.app.AlertDialog
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mkarshnas6.karenstudio.kidscanvas.databinding.ActivityLearningBinding
import yuku.ambilwarna.AmbilWarnaDialog

class LearningActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLearningBinding
    private var selectedColor: Int = Color.BLACK
    private var EraserIsEnable:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageName = intent.getStringExtra("image_name")
        if (imageName != null) {
            val resId = resources.getIdentifier("img_$imageName", "drawable", packageName)
            if (resId != 0) {
                binding.imgLearn.setImageResource(resId)
            }
        }

        //        ......... change color ..............................
        window.statusBarColor = ContextCompat.getColor(this, R.color.dark_green)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.dark_green)

//        ....... set seek bar to widh ..................
        binding.seekBarBrushSize.progress = binding.coloringView.getBrushSize().toInt()

        binding.btnSelectColor.setOnClickListener { openColorPicker() }
        binding.btnDelete.setOnClickListener { deletePainter() }
        binding.btnAddSize.setOnClickListener { binding.seekBarBrushSize.progress += 2 }
        binding.btnMainesSize.setOnClickListener { binding.seekBarBrushSize.progress -= 2 }

        binding.btnDownload.setOnClickListener {
            val bitmap = binding.coloringView.getBitmapFromView(binding.coloringView)
            saveImageToGallery(bitmap)
        }

        binding.seekBarBrushSize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.coloringView.setBrushSize(progress.toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })

        binding.btnEraser.setOnClickListener {

            if (EraserIsEnable){
                binding.btnEraser.setBackgroundColor(getColor(R.color.None))
                binding.coloringView.setEraserMode(false)
                EraserIsEnable = false
            }else{
                binding.btnEraser.setBackgroundColor(getColor(R.color.light_green))
                binding.coloringView.setEraserMode(true)
                EraserIsEnable = true
            }

        }
    }

    private fun saveImageToGallery(bitmap: Bitmap) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.custom_dialog_saving, null)
        builder.setView(dialogView)
            .setCancelable(true)

        val alertDialog = builder.create()

        val btnYes: TextView = dialogView.findViewById(R.id.btn_yes)
        val btnNo: TextView = dialogView.findViewById(R.id.btn_no)

        btnYes.setOnClickListener {
            val contentResolver = applicationContext.contentResolver

            val imageCollection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
            val contentValues = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, "drawing_${System.currentTimeMillis()}.png")
                put(MediaStore.Images.Media.MIME_TYPE, "image/png")
                put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            }

            val imageUri = contentResolver.insert(imageCollection, contentValues)

            imageUri?.let { uri ->
                try {
                    contentResolver.openOutputStream(uri)?.use { outputStream ->
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                        Toast.makeText(this, "تصویر با موفقیت ذخیره شد.", Toast.LENGTH_SHORT).show()
                    } ?: run {
                        Toast.makeText(this, "خطا در باز کردن خروجی.", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "خطا در ذخیره تصویر: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            } ?: run {
                Toast.makeText(this, "خطا در ایجاد تصویر URI.", Toast.LENGTH_SHORT).show()
            }

            alertDialog.dismiss()
        }

        btnNo.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

    private fun deletePainter() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.custom_dialog_layout, null)
        builder.setView(dialogView)
            .setCancelable(true)

        val alertDialog = builder.create()
        val btnYes: TextView = dialogView.findViewById(R.id.btn_yes)
        val btnNo: TextView = dialogView.findViewById(R.id.btn_no)

        btnYes.setOnClickListener {
            binding.coloringView.clearCanvas()
            alertDialog.dismiss()
        }
        btnNo.setOnClickListener {
            alertDialog.dismiss()
        }
        alertDialog.show()
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
                    binding.btnEraser.setBackgroundColor(getColor(R.color.None))
                    EraserIsEnable = false
                }
            })
        colorPicker.show()

    }
}