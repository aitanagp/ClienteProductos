package ies.sequeros.dam.pmdm.agp.aplicacion

import ies.sequeros.dam.pmdm.agp.modelo.Categoria
import ies.sequeros.dam.pmdm.agp.modelo.IProductoRepositorio
import ies.sequeros.dam.pmdm.agp.modelo.Producto

class GetAllCategoriasUseCase(private val categoriaRepositorio: IProductoRepositorio) {
    suspend fun invoke(): List<Producto> {
        return categoriaRepositorio.getAll()
    }
}