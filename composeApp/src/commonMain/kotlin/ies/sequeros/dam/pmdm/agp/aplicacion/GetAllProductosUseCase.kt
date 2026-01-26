package ies.sequeros.dam.pmdm.agp.aplicacion

import ies.sequeros.dam.pmdm.agp.modelo.IProductoRepositorio
import ies.sequeros.dam.pmdm.agp.modelo.Producto

class GetAllProductosUseCase(private val productoRepositorio: IProductoRepositorio) {
    suspend fun invoke(): List<Producto> {
        return productoRepositorio.getAll()
    }
}