package ies.sequeros.dam.pmdm.agp.di

import ies.sequeros.dam.pmdm.agp.aplicacion.GetAllProductosUseCase
import org.koin.dsl.module

val moduloAplicacion = module {
    // casos de uso
    factory { GetAllProductosUseCase(get()) }
}