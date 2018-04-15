package loki.ui.connectors


import apple.NSObject
import apple.foundation.*
import apple.uikit.UICollectionView
import apple.uikit.UICollectionViewCell
import apple.uikit.UILabel
import apple.uikit.UIViewController
import apple.uikit.protocol.UICollectionViewDataSource
import apple.uikit.protocol.UICollectionViewDelegate
import loki.ui.TableViewControllerDelegate
import org.moe.natj.c.ann.FunctionPtr
import org.moe.natj.general.NatJ
import org.moe.natj.general.Pointer
import org.moe.natj.general.ann.*
import org.moe.natj.general.ptr.VoidPtr
import org.moe.natj.objc.Class
import org.moe.natj.objc.ObjCRuntime
import org.moe.natj.objc.SEL
import org.moe.natj.objc.ann.IBOutlet
import org.moe.natj.objc.ann.ObjCClassName
import org.moe.natj.objc.ann.Property
import org.moe.natj.objc.ann.Selector
import org.moe.natj.objc.map.ObjCObjectMapper


@Runtime(ObjCRuntime::class)
@ObjCClassName("TableViewController")
@RegisterOnStartup
class TableViewController @Generated constructor(peer: Pointer) : UIViewController(peer),
        UICollectionViewDataSource, UICollectionViewDelegate {


    private val delegate = TableViewControllerDelegate(this)


    override fun viewDidLoad() {
        super.viewDidLoad()
        delegate.viewDidLoad()
    }


    override fun collectionViewNumberOfItemsInSection(collectionView: UICollectionView?, section: Long): Long {
        return delegate.collectionViewNumberOfItemsInSection(collectionView, section)
    }


    override fun collectionViewCellForItemAtIndexPath(collectionView: UICollectionView?, indexPath: NSIndexPath?):
            UICollectionViewCell {
        return delegate.collectionViewCellForItemAtIndexPath(collectionView, indexPath)
    }


    @Generated
    @Selector("collectionView")
    external fun collectionView(): UICollectionView?

    @Generated
    @Selector("init")
    external override fun init(): TableViewController

    @Generated
    @Selector("initWithCoder:")
    external override fun initWithCoder(aDecoder: NSCoder): TableViewController

    @Generated
    @Selector("initWithNibName:bundle:")
    external override fun initWithNibNameBundle(
            nibNameOrNil: String, nibBundleOrNil: NSBundle): TableViewController

    @Generated
    @Selector("myLabel")
    external fun myLabel(): UILabel?

    @Generated
    @Selector("setCollectionView:")
    external fun setCollectionView_unsafe(value: UICollectionView?)

    @Generated
    fun setCollectionView(value: UICollectionView?) {
        val __old = collectionView()
        if (value != null) {
            org.moe.natj.objc.ObjCRuntime.associateObjCObject(this, value)
        }
        setCollectionView_unsafe(value)
        if (__old != null) {
            org.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this, __old)
        }
    }

    @Selector("myLabel")
    @Property
    @IBOutlet
    external fun getMyLabell(): UILabel

    @Generated
    @Selector("setMyLabel:")
    external fun setMyLabel_unsafe(value: UILabel?)

    @Generated
    fun setMyLabel(value: UILabel?) {
        val __old = myLabel()
        if (value != null) {
            org.moe.natj.objc.ObjCRuntime.associateObjCObject(this, value)
        }
        setMyLabel_unsafe(value)
        if (__old != null) {
            org.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this, __old)
        }
    }

    companion object {
        init {
            NatJ.register()
        }

        @Generated
        @Selector("accessInstanceVariablesDirectly")
        external fun accessInstanceVariablesDirectly(): Boolean

        @Generated
        @Owned
        @Selector("alloc")
        external fun alloc(): TableViewController

        @Generated
        @Selector("allocWithZone:")
        @MappedReturn(ObjCObjectMapper::class)
        external fun allocWithZone(zone: VoidPtr): Any

        @Generated
        @Selector("attemptRotationToDeviceOrientation")
        external fun attemptRotationToDeviceOrientation()

        @Generated
        @Selector("automaticallyNotifiesObserversForKey:")
        external fun automaticallyNotifiesObserversForKey(key: String): Boolean

        @Generated
        @Selector("cancelPreviousPerformRequestsWithTarget:")
        external fun cancelPreviousPerformRequestsWithTarget(
                @Mapped(ObjCObjectMapper::class) aTarget: Any)

        @Generated
        @Selector("cancelPreviousPerformRequestsWithTarget:selector:object:")
        external fun cancelPreviousPerformRequestsWithTargetSelectorObject(
                @Mapped(ObjCObjectMapper::class) aTarget: Any, aSelector: SEL,
                @Mapped(ObjCObjectMapper::class) anArgument: Any)

        @Generated
        @Selector("classFallbacksForKeyedArchiver")
        external fun classFallbacksForKeyedArchiver(): NSArray<String>

        @Generated
        @Selector("classForKeyedUnarchiver")
        external fun classForKeyedUnarchiver(): Class

        @Generated
        @Selector("clearTextInputContextIdentifier:")
        external fun clearTextInputContextIdentifier(identifier: String)

        @Generated
        @Selector("debugDescription")
        external fun debugDescription_static(): String

        @Generated
        @Selector("description")
        external fun description_static(): String

        @Generated
        @Selector("hash")
        @NUInt
        external fun hash_static(): Long

        @Generated
        @Selector("instanceMethodForSelector:")
        @FunctionPtr(name = "call_instanceMethodForSelector_ret")
        external fun instanceMethodForSelector(
                aSelector: SEL): NSObject.Function_instanceMethodForSelector_ret

        @Generated
        @Selector("instanceMethodSignatureForSelector:")
        external fun instanceMethodSignatureForSelector(
                aSelector: SEL): NSMethodSignature

        @Generated
        @Selector("instancesRespondToSelector:")
        external fun instancesRespondToSelector(aSelector: SEL): Boolean

        @Generated
        @Selector("isSubclassOfClass:")
        external fun isSubclassOfClass(aClass: Class): Boolean

        @Generated
        @Selector("keyPathsForValuesAffectingValueForKey:")
        external fun keyPathsForValuesAffectingValueForKey(
                key: String): NSSet<String>

        @Generated
        @Owned
        @Selector("new")
        @MappedReturn(ObjCObjectMapper::class)
        external fun new_objc(): Any

        @Generated
        @Selector("resolveClassMethod:")
        external fun resolveClassMethod(sel: SEL): Boolean

        @Generated
        @Selector("resolveInstanceMethod:")
        external fun resolveInstanceMethod(sel: SEL): Boolean

        @Generated
        @Selector("setVersion:")
        external fun setVersion(@NInt aVersion: Long)

        @Generated
        @Selector("superclass")
        external fun superclass_static(): Class

        @Generated
        @Selector("version")
        @NInt
        external fun version_static(): Long
    }
}