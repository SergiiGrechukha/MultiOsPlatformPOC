package com.loki.core.model.repository

import com.loki.core.model.entity.Gallery
import io.reactivex.Observable

interface ImageProvider{

    fun getImagePreviewsByPage(page: Int = 1, perPage: Int = 20): Observable<Gallery>

}