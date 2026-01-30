package ies.sequeros.dam.pmdm.agp.modelo

interface IProductoRepositorio {
    suspend fun getAll():List<Producto>
    suspend fun insert(producto: Producto): Boolean
    suspend fun update(producto: Producto): Boolean
    suspend fun delete(id: String): Boolean
}