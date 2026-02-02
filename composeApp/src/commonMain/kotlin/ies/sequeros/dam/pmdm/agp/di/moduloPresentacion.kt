package ies.sequeros.dam.pmdm.agp.di

import ies.sequeros.dam.pmdm.agp.vista.ProductoViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val moduloPresentacion = module {
    viewModel {
        ProductoViewModel(
            getAllProductosUseCase = get(),
            categoriaRepository = get(),
            deleteProductoUseCase = get(),
            productoRepository = get()
        )
    }
}
