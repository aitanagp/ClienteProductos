package ies.sequeros.dam.pmdm.agp.di

import ies.sequeros.dam.pmdm.agp.infraestructura.RestProductoRepository
import ies.sequeros.dam.pmdm.agp.modelo.IProductoRepositorio
import org.koin.dsl.module

val moduloDominio = module {
    single<IProductoRepositorio> { RestProductoRepository("http://localhost:8080", get()) }
}