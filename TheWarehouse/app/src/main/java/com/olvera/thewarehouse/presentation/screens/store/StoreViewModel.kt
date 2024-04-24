package com.olvera.thewarehouse.presentation.screens.store

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olvera.thewarehouse.model.ProductList
import com.olvera.thewarehouse.repository.StoreRepository
import com.olvera.thewarehouse.state.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val storeRepository: StoreRepository
): ViewModel() {

    private val _products = MutableStateFlow<List<ProductList>>(emptyList())

    val products = _products.asStateFlow()

    var state by mutableStateOf(ProductState())
        private set

    private fun fetchProducts() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = storeRepository.getAllProducts()
                _products.value = result ?: emptyList()
            }
        }
    }



}