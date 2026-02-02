package ies.sequeros.dam.pmdm.agp.di

import ies.sequeros.dam.pmdm.agp.aplicacion.AddCategoriaUseCase
import ies.sequeros.dam.pmdm.agp.aplicacion.AddProductoUseCase
import ies.sequeros.dam.pmdm.agp.aplicacion.DeleteCategoriaUseCase
import ies.sequeros.dam.pmdm.agp.aplicacion.DeleteProductoUseCase
import ies.sequeros.dam.pmdm.agp.aplicacion.GetAllCategoriasUseCase
import ies.sequeros.dam.pmdm.agp.aplicacion.GetAllProductosUseCase
import ies.sequeros.dam.pmdm.agp.aplicacion.UpdateProductoUseCase
import ies.sequeros.dam.pmdm.agp.vista.ProductoViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val moduloAplicacion = module {
    // casos de uso
    factory { GetAllProductosUseCase(get()) }
    factory { GetAllCategoriasUseCase(get()) }
    factory { AddCategoriaUseCase(get()) }
    factory { AddProductoUseCase(get()) }
    factory { UpdateProductoUseCase(get()) }
    factory { DeleteProductoUseCase(get()) }
    factory { UpdateProductoUseCase(get()) }
    factory { DeleteCategoriaUseCase(get()) }

    viewModel {
        ProductoViewModel(
            getAllProductosUseCase = get(),
            categoriaRepository = get(),
            deleteProductoUseCase = get(),
            productoRepository = get()
        )
    }
}