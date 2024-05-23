package com.olvera.thewarehouse

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.olvera.thewarehouse.navigation.NavManager
import com.olvera.thewarehouse.presentation.product.ProductsViewModel
import com.olvera.thewarehouse.ui.theme.TheWarehouseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private val productViewModel: ProductsViewModel by viewModels()
    var onboardingCompleted by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onboardingCompleted = onBoardingIsFinished(this)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !viewModel.isReady.value
            }
        }
        setContent {
            TheWarehouseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavManager(
                        context = this,
                        onboardingCompleted = onboardingCompleted,
                        productsViewModel = productViewModel
                    )
                }
            }
        }
    }

    private fun onBoardingIsFinished(context: MainActivity): Boolean {
        val sharedPreferences = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isFinished", false)
    }
}
