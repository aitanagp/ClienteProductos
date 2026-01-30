package ies.sequeros.dam.pmdm.agp.infraestructura

import ies.sequeros.dam.pmdm.agp.modelo.Categoria
import ies.sequeros.dam.pmdm.agp.modelo.ICategoriaRepositorio
import ies.sequeros.dam.pmdm.agp.modelo.Producto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import io.ktor.client.request.setBody

class RestCategoriaRepository(
    private val url: String,
    private val client: HttpClient
) : ICategoriaRepositorio {

    override suspend fun getAll(): List<Categoria> {
        val response = client.get("$url/categorias")
        return try {
            val respuestaEnTexto = response.bodyAsText()

            println("RESPUESTA DEL SERVIDOR: $respuestaEnTexto")

            Json.decodeFromString<List<Categoria>>(respuestaEnTexto)
        } catch (e: Exception) {
            println("Error cargando categorías: ${e.message}")
            emptyList()
        }
    }
    override suspend fun insert(categoria: Categoria): Boolean {
        return try {
            val response = client.post("$url/categorias") {
                contentType(ContentType.Application.Json)
                setBody(categoria)
            }

            response.status == HttpStatusCode.Created || response.status == HttpStatusCode.OK
        } catch (e: Exception) {
            println("Error al crear categoría: ${e.message}")
            false
        }
    }

    override suspend fun update(categoria: Categoria): Boolean {
        return try {
            val response = client.put("$url/categorias/${categoria.id}") {
                contentType(ContentType.Application.Json)
                setBody(categoria)
            }
            response.status == HttpStatusCode.Created || response.status == HttpStatusCode.OK
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun delete(id: String): Boolean {
        return try {
            val response = client.delete("$url/categorias/$id")
            response.status == HttpStatusCode.OK
        }catch (e: Exception) {
            false
        }
    }
}