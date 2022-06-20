package com.aspire.sawacompose.screens

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.viewModel
import com.aspire.sawacompose.navigation.SawaScreens
import com.aspire.sawacompose.screens.home.HomeScreen
import com.aspire.sawacompose.ui.theme.SawaComposeTheme
import com.aspire.sawacompose.viewmodel.SettingViewModel
import java.util.*

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    val viewModel: SettingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getTheme()
        viewModel.getLanguage()

        setContent {
            MyApp(viewModel)
        }

    }

    @Composable
    fun MyApp(viewModel: SettingViewModel) {
        val result by viewModel.collectAsState()

        setLocale(result.language)
        SawaComposeTheme(theme = result.theme) {

            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = SawaScreens.SplashScreen.name
            ) {

                composable(SawaScreens.SplashScreen.name) {
                    SplashScreen(navController)
                }

                composable(SawaScreens.HomeScreen.name) {
                    HomeScreen(viewModel)
                }
            }
        }
    }

    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources: Resources = resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}



