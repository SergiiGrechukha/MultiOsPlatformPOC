package com.loki.core.model.repository.pixabay

import com.loki.core.model.entity.Gallery
import com.loki.core.model.entity.GalleryImage
import com.loki.core.model.repository.ImageProvider
import com.loki.core.model.repository.pixabay.PixaBayRestApi.Companion.API_KEY
import com.loki.core.model.utils.RestClient
import io.reactivex.Observable

class PixaBayImageProvider: ImageProvider {

    private val restClient = RestClient(PixaBayRestApi.BASE_URL, PixaBayRestApi::class.java).getServerApi()

    override fun getImagePreviewsByPage(page: Int, perPage: Int): Observable<Gallery> {
        return restClient.getImagesPagedList(API_KEY, "city", page)
                .map { Gallery(it.amount,
                        it.images.map { GalleryImage(it.previewUrl, it.fullImageUrl) }) }
                .doOnError({ PixaBayFetchException(it.message) })
    }
}