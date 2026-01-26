package ies.sequeros.dam.pmdm.agp.vista.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ies.sequeros.dam.pmdm.agp.modelo.Producto
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight

@Composable
fun PanelListadoProducto(
    items: List<Producto>,
    selected: Producto?,
    onSelect: (Producto) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        if (items.isEmpty()) {
            // Mensaje de carga o lista vacía
            Text(
                text = "Cargando Productos...",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                // Usamos el ID como clave si existe, si no el nombre
                items(items, key = { it.id ?: it.nombre }) { item ->
                    ListItem(
                        headlineContent = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.ShoppingBag, // Icono más apropiado
                                    contentDescription = "Producto",
                                    modifier = Modifier.size(24.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = item.nombre,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelect(item) }
                            .padding(horizontal = 8.dp, vertical = 4.dp), // Un poco de margen extra
                        supportingContent = {
                            // Mostramos el precio y la descripción
                            Text(
                                text = "${item.precio} € - ${item.descripcion}",
                                maxLines = 1,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        },
                        // Resaltamos el elemento si coincide con el seleccionado
                        tonalElevation = if (item.nombre == selected?.nombre) 4.dp else 0.dp
                    )
                }
            }
        }
    }
}