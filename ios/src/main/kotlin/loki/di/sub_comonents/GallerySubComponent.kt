package loki.di.sub_comonents

import com.loki.core.di.sub.GallerySubModule
import dagger.Subcomponent
import loki.ui.TableViewControllerDelegate

@Subcomponent(modules = [(GallerySubModule::class)])
interface GallerySubComponent{

    fun inject(view: TableViewControllerDelegate)
}