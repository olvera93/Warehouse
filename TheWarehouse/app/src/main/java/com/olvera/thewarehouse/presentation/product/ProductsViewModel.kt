package com.olvera.thewarehouse.presentation.product

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olvera.thewarehouse.model.ProductList
import com.olvera.thewarehouse.model.ProductModel
import com.olvera.thewarehouse.repository.StoreRepository
import com.olvera.thewarehouse.state.ProductsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val storeRepository: StoreRepository
) : ViewModel() {

    private val _products = MutableStateFlow<List<ProductList>>(emptyList())

    val products = _products

    var state by mutableStateOf(ProductsState())
        private set

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = storeRepository.getAllProducts()
                _products.value = response ?: emptyList()
            }
        }
    }


}