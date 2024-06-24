package com.olvera.thewarehouse

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.olvera.thewarehouse.navigation.NavManager
import com.olvera.thewarehouse.presentation.product.ProductsViewModel
import com.olvera.thewarehouse.presentation.signup.SignUpViewModel
import com.olvera.thewarehouse.ui.theme.TheWarehouseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private val productViewModel: ProductsViewModel by viewModels()
    private val signUpViewModel: SignUpViewModel by viewModels()
    private var onboardingCompleted by mutableStateOf(false)

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
                    color = MaterialTheme.colorScheme.primary
                ) {
                    NavManager(
                        context = this,
                        onboardingCompleted = onboardingCompleted,
                        productsViewModel = productViewModel,
                        signUpViewModel = signUpViewModel
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

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun MainActivityPreview() {
    TheWarehouseTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary
        ) {

        }
    }
}
