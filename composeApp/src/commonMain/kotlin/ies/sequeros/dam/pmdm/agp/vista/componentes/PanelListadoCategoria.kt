package ies.sequeros.dam.pmdm.agp.vista.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ies.sequeros.dam.pmdm.agp.modelo.Categoria
import ies.sequeros.dam.pmdm.agp.modelo.ICategoriaRepositorio
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun PanelListadoCategorias(
    categoriaActual: Categoria?,
    onCategoriaClick: (Categoria) -> Unit
) {
    val repositorio = koinInject<ICategoriaRepositorio>()
    val scope = rememberCoroutineScope()
    var categorias by remember { mutableStateOf<List<Categoria>>(emptyList()) }

    LaunchedEffect(true) {
        scope.launch { categorias = repositorio.getAll() }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Categorías", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(top = 8.dp)
        ) {
            items(categorias) { cat ->
                val esSeleccionada = cat.id == categoriaActual?.id

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(if (esSeleccionada) MaterialTheme.colorScheme.primary else Color.LightGray)
                        .clickable { onCategoriaClick(cat) }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = cat.nombre,
                        color = if (esSeleccionada) Color.White else Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}