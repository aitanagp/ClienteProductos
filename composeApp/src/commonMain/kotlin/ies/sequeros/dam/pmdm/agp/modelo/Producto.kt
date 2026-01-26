package ies.sequeros.dam.pmdm.agp.modelo

import kotlinx.serialization.Serializable

@Serializable
data class Producto(
    val id: String? = null,
    val nombre: String,
    val descripcion: String,
    val categoria: Double,
    val precio: Double,
    val activo: Boolean,
    val imagen: String? = null
)
