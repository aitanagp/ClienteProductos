package ies.sequeros.dam.pmdm.agp.aplicacion

import ies.sequeros.dam.pmdm.agp.modelo.Categoria
import ies.sequeros.dam.pmdm.agp.modelo.ICategoriaRepositorio

class UpdateCategoriaUseCase(private val categoriaRepositorio: ICategoriaRepositorio) {
    suspend operator fun invoke(categoria: Categoria): Boolean = categoriaRepositorio.update(categoria)
}