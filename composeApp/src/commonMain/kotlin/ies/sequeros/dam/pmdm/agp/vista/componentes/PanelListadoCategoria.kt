package ies.sequeros.dam.pmdm.agp.vista.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ies.sequeros.dam.pmdm.agp.modelo.Categoria
import ies.sequeros.dam.pmdm.agp.modelo.ICategoriaRepositorio
import ies.sequeros.dam.pmdm.agp.vista.ProductoViewModel
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.dsl.koinApplication
import androidx.compose.material3.Icon

@Composable
fun PanelListadoCategoria(
    categorias: List<Categoria>,
    categoriaActual: Categoria?,
    onCategoriaClick: (Categoria) -> Unit,
) {
    val vm: ProductoViewModel = koinViewModel()
    var mostrarFormulario by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Text("Categorías", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // BOTÓN
            item {
                IconButton(
                    onClick = { mostrarFormulario = true },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Nueva", tint = MaterialTheme.colorScheme.primary)
                }
            }

            // FICHAS DE CATEGORÍA
            items(categorias) { cat ->
                val seleccionada = cat.id == categoriaActual?.id

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(if (seleccionada) MaterialTheme.colorScheme.primary else Color.LightGray)
                        .clickable { onCategoriaClick(cat) }
                        .padding(start = 16.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Nombre
                    Text(
                        text = cat.nombre,
                        color = if (seleccionada) Color.White else Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                    // Separación pequeña
                    Spacer(modifier = Modifier.width(4.dp))

                    // Botón BORRAR (X) - Pequeñito
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Borrar",
                        tint = if (seleccionada) Color.White.copy(alpha = 0.7f) else Color.Black.copy(alpha = 0.5f),
                        modifier = Modifier
                            .size(16.dp)
                            .clickable { vm.borrarCategoria(cat) } // <--- AQUÍ BORRAMOS
                    )
                }
            }
        }
    }

    if (mostrarFormulario) {
        FormularioCategoria(
            onDismiss = { mostrarFormulario = false },
            onConfirm = { nombre ->
                vm.crearCategoria(
                    Categoria(id = null, nombre = nombre, descripcion = "", activo = true)
                )
                mostrarFormulario = false
            }
        )
    }
}