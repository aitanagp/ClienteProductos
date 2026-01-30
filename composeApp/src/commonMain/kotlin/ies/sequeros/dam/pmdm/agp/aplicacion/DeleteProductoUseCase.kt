package ies.sequeros.dam.pmdm.agp.aplicacion

import ies.sequeros.dam.pmdm.agp.modelo.IProductoRepositorio

class DeleteProductoUseCase(private val productoRepositorio: IProductoRepositorio) {
    suspend operator fun invoke(id: String): Boolean = productoRepositorio.delete(id)
}