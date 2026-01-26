package ies.sequeros.dam.pmdm.agp.vista

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.dam.pmdm.agp.DispatcherIO
import ies.sequeros.dam.pmdm.agp.aplicacion.GetAllProductosUseCase
import ies.sequeros.dam.pmdm.agp.modelo.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProductoViewModel(getAllProductosUseCase: GetAllProductosUseCase) : ViewModel() {
    private val _items = MutableStateFlow<List<Producto>>(emptyList())

    val items = _items.asStateFlow()

    init {
        viewModelScope.launch {
            withContext(DispatcherIO) {
                _items.value = getAllProductosUseCase.invoke()
            }
        }

    }
}