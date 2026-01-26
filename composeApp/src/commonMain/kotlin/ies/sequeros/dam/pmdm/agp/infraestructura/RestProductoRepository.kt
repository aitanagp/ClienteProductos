package ies.sequeros.dam.pmdm.agp.infraestructura

import ies.sequeros.dam.pmdm.agp.modelo.IProductoRepositorio
import ies.sequeros.dam.pmdm.agp.modelo.Producto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

class RestProductoRepository(
    private val url: String,
    private val _client: HttpClient
) : IProductoRepositorio {

    override suspend fun getAll(): List<Producto> {
        val response = _client.get("$url/productos")

        println("URL QUE ESTOY LLAMANDO: $response")
        return try {

            val respuestaEnTexto = response.bodyAsText()

            println("RESPUESTA DEL SERVIDOR: $respuestaEnTexto")

            Json.decodeFromString<List<Producto>>(respuestaEnTexto)

        } catch (e: Exception) {
            println("Error al obtener productos: ${e.message}")
            emptyList()
        }
    }
}