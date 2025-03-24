package com.mkarshnas6.karenstudio.kidscanvas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ColoringAdapter(private val imageList: List<ImageItem>, private val onClick: (ImageItem) -> Unit) :
    RecyclerView.Adapter<ColoringAdapter.ImageViewHolder>() {

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.img_paint)
        val textView: TextView = view.findViewById(R.id.txt_name_paint)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_coloring, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = imageList[position]

        holder.textView.text = item.persianName  // نمایش نام فارسی

        // اگر می‌خواهید تصاویر را با توجه به نام انگلیسی بارگذاری کنید:
        val context = holder.itemView.context
        // برای مثال این کد می‌تواند تصویر مناسب را بر اساس نام انگلیسی بارگذاری کند
         val imageResId = context.resources.getIdentifier("img_${item.englishName}", "drawable", context.packageName)
         holder.imageView.setImageResource(imageResId)

        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount(): Int = imageList.size
}
