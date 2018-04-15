package com.loki.moepoc

import android.databinding.DataBindingUtil
import android.graphics.Point
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.TextView
import com.loki.core.contract.GalleryContract
import com.loki.core.di.sub.getSubModule
import com.loki.moepoc.adapter.GalleryAdapter
import com.loki.moepoc.databinding.ActivityMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity(), GalleryContract.View {

    @Inject
    lateinit var presenter: GalleryContract.Presenter
    private val component by lazy {
        val appComponent = (applicationContext as PocApp).appComponent
        appComponent.plus(appComponent.getSubModuleFactory().getSubModule(this)) }
    private lateinit var binding: ActivityMainBinding
    private val adapter = GalleryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        component.inject(this)

        setUpView()
    }

    private fun setUpView() {
        binding.rvGallery.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvGallery.adapter = adapter
        binding.rvGallery.setItemViewCacheSize(6)
    }

    private fun fetchImages() {
        presenter.subscribeForNewImages()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { onNewImages(it) }
        presenter.getImages()
    }

    private fun onNewImages(imageUrls: List<String>) {
        this.adapter.updateOrClear(imageUrls)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
        fetchImages()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onError(error: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
