package ies.sequeros.dam.pmdm.agp.vista

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.dam.pmdm.agp.DispatcherIO
import ies.sequeros.dam.pmdm.agp.aplicacion.DeleteProductoUseCase
import ies.sequeros.dam.pmdm.agp.aplicacion.GetAllProductosUseCase
import ies.sequeros.dam.pmdm.agp.modelo.Categoria
import ies.sequeros.dam.pmdm.agp.modelo.ICategoriaRepositorio
import ies.sequeros.dam.pmdm.agp.modelo.IProductoRepositorio
import ies.sequeros.dam.pmdm.agp.modelo.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProductoViewModel(
    private val getAllProductosUseCase: GetAllProductosUseCase,
    private val categoriaRepository: ICategoriaRepositorio,
    private val deleteProductoUseCase: DeleteProductoUseCase,
    private val productoRepository: IProductoRepositorio
) : ViewModel() {

    private val _items = MutableStateFlow<List<Producto>>(emptyList())
    val items = _items.asStateFlow()

    private val _categorias = MutableStateFlow<List<Categoria>>(emptyList())
    val categorias = _categorias.asStateFlow()

    private val _categoriaSeleccionada = MutableStateFlow<Categoria?>(null)
    val categoriaSeleccionada = _categoriaSeleccionada.asStateFlow()

    private var todosLosProductos: List<Producto> = emptyList()

    init {
        cargarDatos()
    }

    private fun cargarDatos() {
        viewModelScope.launch {
            withContext(DispatcherIO) {
                try {
                    _categorias.value = categoriaRepository.getAll()
                    todosLosProductos = getAllProductosUseCase.invoke()
                    filtrarPorCategoria(_categoriaSeleccionada.value)
                } catch (e: Exception) {
                    println("Error cargando datos: ${e.message}")
                }
            }
        }
    }

    fun filtrarPorCategoria(categoria: Categoria?) {
        _categoriaSeleccionada.value = categoria

        if (categoria == null) {
            _items.value = todosLosProductos
        } else {
            _items.value = todosLosProductos.filter { it.categoria == categoria.id }
        }
    }
    fun crearCategoria(categoria: Categoria) {
        viewModelScope.launch {
            withContext(DispatcherIO) {
                // addCategoriaUseCase.invoke(categoria)
                categoriaRepository.insert(categoria)
                cargarDatos()
            }
        }
    }
    fun borrarCategoria(categoria: Categoria) {
        viewModelScope.launch {
            withContext(DispatcherIO) {
                try {
                    if (categoria.id != null) {
                        categoriaRepository.delete(categoria.id)

                        if (_categoriaSeleccionada.value?.id == categoria.id) {
                            filtrarPorCategoria(null)
                        }
                        cargarDatos()
                    }
                } catch (e: Exception) {
                    println("ERROR BORRANDO CATEGORÍA: ${e.message}")
                }
            }
        }
    }

    fun crearProducto(producto: Producto) {
        viewModelScope.launch {
            withContext(DispatcherIO) {
                try {
                    println("Intentando crear producto: $producto")
                    productoRepository.insert(producto)
                    println("Producto creado OK...")
                    cargarDatos()
                } catch (e: Exception) {
                    println("ERROR CREANDO PRODUCTO: ${e.message}")
                    e.printStackTrace()
                }
            }
        }
    }

    fun borrarProducto(producto: Producto) {
        viewModelScope.launch {
            withContext(DispatcherIO) {
                if (producto.id != null) {
                    // Llamamos al caso de uso para borrar en el servidor
                    val borradoCorrecto = deleteProductoUseCase.invoke(producto.id)

                    if (borradoCorrecto) {
                        //Si se borra bien se recarga la lista del servidor
                        cargarDatos()
                    }
                }
            }
        }
    }
}