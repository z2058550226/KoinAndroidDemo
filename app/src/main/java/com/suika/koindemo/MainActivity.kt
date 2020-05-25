package com.suika.koindemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.KoinContextHandler
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private val presenter: Presenter by inject()
    private val mainPresenter: MainPresenter by lifecycleScope.inject()
    private val mainViewModel: MainViewModel by viewModel { parametersOf("<main view model name>") }

    // Then use it anywhere you need it:
    private val userSession: UserSession by ourSession.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        // setupKoinFragmentFactory()
        // 如果fragment绑定Activity的scope，那么需要传入lifecycleScope
        setupKoinFragmentFactory(lifecycleScope)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val koin = KoinContextHandler.get()
        Timber.e(koin.getProperty<String>("SERVER_URL"))
        val application: Context = koin.get()
        Timber.e("application: $application")

        Timber.e("presenter.name: ${presenter.name}")
        val p: Presenter = get()
        Timber.e("p.name: ${p.name}")

        Timber.e("mainPresenter.name: ${mainPresenter.name}")
        Timber.e("mainViewModel.name: ${mainViewModel.name}")
        mainViewModel.someName.observe(this, Observer { Timber.e("mainViewModel.someName: $it") })

        supportFragmentManager.beginTransaction()
                .replace(R.id.content, MyFragment::class.java, null, null)
                .commit()
        root_layout.addView(applicationView)
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.e("mainPresenter.name: ${mainPresenter.name}")
    }

    fun startSecondActivity(view: View) {
        startActivity(Intent(this, SecondActivity::class.java))
    }
}