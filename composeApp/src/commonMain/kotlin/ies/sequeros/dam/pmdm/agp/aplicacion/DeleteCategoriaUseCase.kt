package ies.sequeros.dam.pmdm.agp.aplicacion

import ies.sequeros.dam.pmdm.agp.modelo.Categoria
import ies.sequeros.dam.pmdm.agp.modelo.ICategoriaRepositorio

class DeleteCategoriaUseCase(private val categoriaRepositorio: ICategoriaRepositorio) {
    suspend operator fun invoke(id: String): Boolean = categoriaRepositorio.delete(id)
}