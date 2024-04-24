package com.olvera.thewarehouse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olvera.thewarehouse.navigation.NavManager
import com.olvera.thewarehouse.presentation.screens.signup.SignUpViewModel
import com.olvera.thewarehouse.presentation.screens.store.StoreViewModel
import com.olvera.thewarehouse.ui.theme.TheWarehouseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val signUpViewModel: SignUpViewModel by viewModels()
        val storeViewModel: StoreViewModel by viewModels()
        setContent {
            TheWarehouseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavManager(signUpViewModel, storeViewModel)
                }
            }
        }
    }
}