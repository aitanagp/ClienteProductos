package ies.sequeros.dam.pmdm.agp.aplicacion

import ies.sequeros.dam.pmdm.agp.modelo.Categoria
import ies.sequeros.dam.pmdm.agp.modelo.ICategoriaRepositorio
import ies.sequeros.dam.pmdm.agp.modelo.IProductoRepositorio

class AddCategoriaUseCase(private val categoriaRepositorio: ICategoriaRepositorio) {
    suspend operator fun invoke(categoria: Categoria): Boolean {
        return categoriaRepositorio.insert(categoria)
    }
}