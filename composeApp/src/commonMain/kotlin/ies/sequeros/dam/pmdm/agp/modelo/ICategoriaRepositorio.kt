package ies.sequeros.dam.pmdm.agp.modelo

interface ICategoriaRepositorio {
    suspend fun getAll():List<Categoria>
    suspend fun insert(categoria: Categoria): Boolean
    suspend fun update(categoria: Categoria): Boolean
    suspend fun delete(id: String): Boolean
}