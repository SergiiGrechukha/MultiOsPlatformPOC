package com.loki.moepoc.adapter

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.graphics.drawable.Drawable
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.loki.moepoc.R
import com.loki.moepoc.databinding.ItemGalleryImageBinding


open class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: ItemGalleryImageBinding by lazy {
        DataBindingUtil.bind<ItemGalleryImageBinding>(itemView)!!
    }

    fun setData(data: String, withDynamicBackGround: Boolean) {
        binding.imageUrl = data
        binding.dynamicBackGround = withDynamicBackGround
    }
}

@BindingAdapter(value = ["imageUrl"])
fun fetchImageByUrl(view: ImageView, imageUrl: String) {
    val pb = (view.parent as ViewGroup).findViewById<ProgressBar>(R.id.pb_loading)
    pb.visibility = View.VISIBLE
    val requestListener: RequestListener<Drawable?> = object : RequestListener<Drawable?> {
        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable?>?, isFirstResource: Boolean): Boolean {
            pb.visibility = View.GONE
            return false
        }

        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable?>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
            pb.visibility = View.GONE
            return false
        }
    }
    Glide
            .with(view)
            .load(Uri.parse(imageUrl))
            .thumbnail(0.5f)
            .listener(requestListener)
            .into(view)
}


