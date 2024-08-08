package com.olvera.thewarehouse.presentation.product

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olvera.thewarehouse.model.CategoryModel
import com.olvera.thewarehouse.model.ProductList
import com.olvera.thewarehouse.repository.StoreRepository
import com.olvera.thewarehouse.state.ProductsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val storeRepository: StoreRepository
) : ViewModel() {

    private val _products = MutableStateFlow<List<ProductList>>(emptyList())
    val products = _products

    private val _categories = MutableStateFlow<List<CategoryModel>>(emptyList())
    val categories = _categories


    var state by mutableStateOf(ProductsState())
        private set

    init {
        fetchCategoriesAndProducts()
    }

    private fun fetchCategoriesAndProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val categoriesDeferred = async { storeRepository.getAllCategories() }
                val productsDeferred = async { storeRepository.getAllProducts() }

                val categories = categoriesDeferred.await()
                val products = productsDeferred.await()

                categories?.forEach { category ->
                    val productsByCategoryDeferred = async { storeRepository.getProductsByCategory(category.slug) }
                    val productsByCategory = productsByCategoryDeferred.await()
                    _products.value += (productsByCategory ?: emptyList())
                }

                _categories.value = categories ?: emptyList()
                _products.value = products ?: emptyList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}