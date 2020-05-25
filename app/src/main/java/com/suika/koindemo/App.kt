package com.suika.koindemo

import android.app.Application
import android.widget.TextView
import com.suika.astree.AndroidStudioTree
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.named
import org.koin.core.scope.ScopeID
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.getKoin
import timber.log.Timber

lateinit var app: App

// When needed to begin use a UserSession instance, create a scope for it:
val ourSession by lazy { getKoin().createScope("ourSession", named("session")) }

val applicationView: TextView by lazy {
    TextView(app).apply {
        text = "I'm the global text view"
    }
}

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        app = this

        Timber.plant(AndroidStudioTree())

        startKoin {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            properties(mapOf("one" to 1, "to" to "sdfla"))
            androidFileProperties()
            fragmentFactory()
            modules(
                    module {
                        // 长期scope，整个app运行期间留存
                        single { Presenter("suika") }
                        // 短期scope，只留存一个界面的生命周期
                        scope<MainActivity> {
                            scoped { MainPresenter("main presenter") }
                        }
                        // 中长期scope，可以在多个Android Component之间共享，并需要手动close
                        scope(named("session")) {
                            scoped { UserSession("suika jy") }
                        }
                        // 使用scope.getSource()比直接用get()从koin中找这个scope的拥有者更快
                        factory { (scopeId: ScopeID) -> SessionPresenter(getScope(scopeId).getSource()) }

                        // fragment { MyFragment() }
                        // scoped fragment
//                        scope<MainActivity> {
//                            fragment { MyFragment() }
//                        }

                        scope<MainActivity3> {
                            fragment { MyFragment() }
                            scoped { Toy(1,"goose") }
                        }
                    },
                    module {
                        viewModel { (name: String) -> MainViewModel(name) }
                    }
            )
        }
    }
}