package com.olvera.thewarehouse.presentation.home

import androidx.lifecycle.ViewModel
import com.olvera.thewarehouse.data.remote.WarehouseApi
import com.olvera.thewarehouse.repository.WarehouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val warehouseRepository: WarehouseRepository
) : ViewModel() {



}