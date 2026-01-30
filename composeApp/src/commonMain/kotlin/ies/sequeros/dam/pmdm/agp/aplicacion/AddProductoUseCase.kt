package ies.sequeros.dam.pmdm.agp.aplicacion

import ies.sequeros.dam.pmdm.agp.modelo.IProductoRepositorio
import ies.sequeros.dam.pmdm.agp.modelo.Producto

class AddProductoUseCase(private val productoRepositorio: IProductoRepositorio) {
    suspend operator fun invoke(producto: Producto): Boolean = productoRepositorio.insert(producto)
}