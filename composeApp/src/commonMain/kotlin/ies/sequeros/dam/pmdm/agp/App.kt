package ies.sequeros.dam.pmdm.agp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.painterResource

import clienteproductos.composeapp.generated.resources.Res
import clienteproductos.composeapp.generated.resources.compose_multiplatform
import ies.sequeros.dam.pmdm.agp.modelo.Categoria
import ies.sequeros.dam.pmdm.agp.vista.componentes.ListadoProducto
import ies.sequeros.dam.pmdm.agp.vista.componentes.PanelListadoCategorias
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF006D3C),
            onPrimary = Color.White,
            secondary = Color(0xFF4CAF50),
            background = Color(0xFFF1FDF4),
        )
    ) {
        var categoriaSeleccionada by remember { mutableStateOf<Categoria?>(null) }

        Column(modifier = Modifier.fillMaxSize()) {

            PanelListadoCategorias(
                categoriaActual = categoriaSeleccionada,
                onCategoriaClick = { nuevaCat ->
                    if (categoriaSeleccionada?.id == nuevaCat.id) {
                        categoriaSeleccionada = null
                    } else {
                        categoriaSeleccionada = nuevaCat
                    }
                }
            )

            ListadoProducto(
                categoriaFiltro = categoriaSeleccionada
            )
        }
    }
}