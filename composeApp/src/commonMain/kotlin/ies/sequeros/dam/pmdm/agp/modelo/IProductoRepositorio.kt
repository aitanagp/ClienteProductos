package ies.sequeros.dam.pmdm.agp.modelo

interface IProductoRepositorio {
    suspend fun getAll():List<Producto>
}