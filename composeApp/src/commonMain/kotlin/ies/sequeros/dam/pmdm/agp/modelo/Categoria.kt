package ies.sequeros.dam.pmdm.agp.modelo

import kotlinx.serialization.Serializable

@Serializable
data class Categoria(
    val id: String? = null,
    val nombre: String,
    val descripcion: String,
    val activo: Boolean
)
