package org.heartimaging.echoindications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.heartimaging.echoindications.auth.AuthManager
import org.heartimaging.echoindications.nav.AppNavigation
import org.heartimaging.echoindications.ui.theme.ECHOindicationsTheme

class MainActivity : ComponentActivity() {

    private val authManager: AuthManager by lazy { AuthManager(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ECHOindicationsTheme {
                AppNavigation(auth = authManager)
            }
        }
    }
}
