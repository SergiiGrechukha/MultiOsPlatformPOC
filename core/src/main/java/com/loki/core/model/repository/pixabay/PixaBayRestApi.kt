package com.loki.core.model.repository.pixabay

import com.loki.core.model.repository.pixabay.pojo.PixaBayPage
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaBayRestApi{

    companion object {
        const val BASE_URL = "https://pixabay.com/"
        //in real live I'd hide it somewhere in BuildConfig
        const val API_KEY = "7764849-c36ad32583666a621a4d0f7d0"
    }

    @GET("api/")
    fun getImagesPagedList(
            @Query("key") key: String,
            @Query("q") query: String,
            @Query("page") page: Int): Observable<PixaBayPage>
}