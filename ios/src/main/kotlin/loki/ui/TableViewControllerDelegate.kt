package loki.ui

import apple.c.Globals
import apple.foundation.NSData
import apple.foundation.NSIndexPath
import apple.uikit.*
import apple.uikit.protocol.UIApplicationDelegate
import com.loki.core.contract.GalleryContract
import com.loki.core.di.sub.getSubModule
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import loki.Main
import loki.ui.connectors.ImageCellCollectionViewCell
import loki.ui.connectors.TableViewController
import org.moe.natj.general.ptr.impl.PtrFactory
import java.io.ByteArrayOutputStream
import java.net.URL
import javax.inject.Inject

class TableViewControllerDelegate(private val tableViewController: TableViewController) : GalleryContract.View {


    companion object {
        private const val CELL_IDENTIFIER = "itemCell"
    }

    @Inject
    lateinit var presenter: GalleryContract.Presenter
    private var myLabelTest: UILabel? = null
    private var images: List<String> = mutableListOf()

    private val component by lazy {
        val appComponent = (UIApplication.sharedApplication().delegate() as UIApplicationDelegate as Main).appComponent
        appComponent.plus(appComponent.getSubModuleFactory().getSubModule(this))
    }

    fun viewDidLoad() {
        myLabelTest = tableViewController.myLabel()
        component.inject(this)

        presenter.onResume()
        myLabelTest?.setText("loading...")

        fetchImages()

    }

    private fun fetchImages() {
        presenter.subscribeForNewImages()
                //todo introduce ios ui scheduler
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { onNewImages(it) }
        presenter.getImages()
    }

    private fun onNewImages(imageUrls: List<String>) {
        Globals.dispatch_sync(Globals.dispatch_get_main_queue(), {
            myLabelTest?.setText("size = ${imageUrls.size}")
            images = imageUrls
            tableViewController.collectionView()?.reloadData()
        })
    }

    fun collectionViewNumberOfItemsInSection(collectionView: UICollectionView?, section: Long): Long {
        return images.size.toLong()
    }


    fun collectionViewCellForItemAtIndexPath(collectionView: UICollectionView?, indexPath: NSIndexPath?):
            UICollectionViewCell {
        val cell = collectionView?.dequeueReusableCellWithReuseIdentifierForIndexPath(Companion.CELL_IDENTIFIER, indexPath) as ImageCellCollectionViewCell

        if (images.isNotEmpty()) {
            Single.fromCallable {
                getUIImageByUrl(images[indexPath?.item()?.toInt() ?: 0])
            }
                    .subscribeOn(Schedulers.io())
                    .subscribe { uiImage, _ ->
                        Globals.dispatch_sync(Globals.dispatch_get_main_queue(), {
                            cell.image().setImage(uiImage)
                        })
                    }
        }



        return cell
    }

    private fun getUIImageByUrl(url: String): UIImage? {

        val stream = URL(url).openConnection().getInputStream()

        // convert InputStream to byte[] to use with NSData
        val arrayStream = ByteArrayOutputStream()
        var nRead: Int
        val buf = ByteArray(16384)

        nRead = stream.read(buf, 0, buf.size)
        while (nRead != -1) {
            arrayStream.write(buf, 0, nRead)
            nRead = stream.read(buf, 0, buf.size)
        }
        arrayStream.flush()

        val imageData = arrayStream.toByteArray()

        val ptr = PtrFactory.newByteArray(imageData)

        val data = NSData.dataWithBytesLength(ptr, imageData.size.toLong())

        arrayStream.close()


        return UIImage.imageWithData(data)
    }

    override fun onError(error: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}