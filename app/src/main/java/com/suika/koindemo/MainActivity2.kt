package com.suika.koindemo

import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.getKoin

class MainActivity2 : AppCompatActivity() {
    // Then use it anywhere you need it:
    val userSession: UserSession by ourSession.inject()

    override fun onDestroy() {
        super.onDestroy()
        // When you have to finish with your scope, just close it:
        val session = getKoin().getScope("ourSession")
        session.close()
    }
}