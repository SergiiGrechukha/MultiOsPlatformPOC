package loki

import apple.NSObject
import apple.foundation.NSDictionary
import apple.uikit.UIApplication
import apple.uikit.UIWindow
import apple.uikit.c.UIKit
import apple.uikit.protocol.UIApplicationDelegate
import com.loki.core.di.AppModule
import loki.di.DaggerIosAppComponent
import loki.di.IosAppComponent
import org.moe.natj.general.Pointer
import org.moe.natj.general.ann.RegisterOnStartup
import org.moe.natj.objc.ann.Selector
import kotlin.properties.Delegates

@RegisterOnStartup
class Main constructor(peer: Pointer) : NSObject(peer), UIApplicationDelegate {

    private var window: UIWindow? = null
    var appComponent: IosAppComponent by Delegates.notNull()

    override fun applicationDidFinishLaunchingWithOptions(application: UIApplication?, launchOptions: NSDictionary<*, *>?): Boolean {
        appComponent = DaggerIosAppComponent.builder().appModule(AppModule()).build()
        return true
    }

    override fun setWindow(value: UIWindow?) {
        window = value
    }

    override fun window(): UIWindow? {
        return window
    }

    companion object {

        @JvmStatic fun main(args: Array<String>) {
            UIKit.UIApplicationMain(0, null, null, Main::class.java.name)
        }

        @Selector("alloc")
        @JvmStatic external fun alloc(): Main
    }
}
