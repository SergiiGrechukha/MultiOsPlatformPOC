package com.loki.moepoc.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.loki.moepoc.R
import java.util.*

open class GalleryAdapter(private val withDynamicBackGround: Boolean = false): RecyclerView.Adapter<GalleryViewHolder>() {

    private var imageUrls: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_gallery_image, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder?, position: Int) {
        holder?.setData(imageUrls[position], withDynamicBackGround)
    }

    override fun getItemCount() = imageUrls.size

    fun updateOrClear(newImageUrls: List<String> = Collections.emptyList()) {
        imageUrls.clear()
        imageUrls.addAll(newImageUrls)
        this.notifyDataSetChanged()
    }
}