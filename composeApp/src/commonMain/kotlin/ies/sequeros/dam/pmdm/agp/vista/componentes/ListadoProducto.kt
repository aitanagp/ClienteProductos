package ies.sequeros.dam.pmdm.agp.vista.componentes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirective
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import ies.sequeros.dam.pmdm.agp.modelo.Categoria
import ies.sequeros.dam.pmdm.agp.modelo.Producto
import ies.sequeros.dam.pmdm.agp.vista.ProductoViewModel
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListadoProducto() {
    val vm: ProductoViewModel = koinViewModel()
    val items by vm.items.collectAsState()
    val categoriaSeleccionada by vm.categoriaSeleccionada.collectAsState()

    val navigator = rememberListDetailPaneScaffoldNavigator<Nothing>()
    var selectedItem by remember { mutableStateOf<Producto?>(null) }
    var mostrarFormularioProducto by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    fun onSelect(item: Producto) {
        selectedItem = item
        scope.launch { navigator.navigateTo(ListDetailPaneScaffoldRole.Detail) }
    }

    fun onDelete(item: Producto) {
        vm.borrarProducto(item)
        if (selectedItem == item) {
            selectedItem = null
            scope.launch { navigator.navigateBack() }
        }
    }

    val windowInfo = currentWindowAdaptiveInfo()
    val directive = calculatePaneScaffoldDirective(windowInfo)
    val mostrarBotonAtras = directive.maxHorizontalPartitions == 1

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            Scaffold(
                floatingActionButton = {
                    if (categoriaSeleccionada != null) {
                        FloatingActionButton(onClick = { mostrarFormularioProducto = true }) {
                            Icon(Icons.Default.Add, contentDescription = "Añadir Producto")
                        }
                    }
                }
            ) { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    PanelListadoProducto(
                        items = items,
                        selected = selectedItem,
                        onSelect = ::onSelect,
                        onDelete = ::onDelete
                    )
                }
            }
        },
        detailPane = {
            DetalleProducto(
                item = selectedItem,
                onBack = { scope.launch { navigator.navigateBack() } },
                mostrarBotonAtras = mostrarBotonAtras
            )
        },
    )

    if (mostrarFormularioProducto) {
        val catId = categoriaSeleccionada?.id ?: ""
        FormularioProducto(
            categoriaId = catId,
            onDismiss = { mostrarFormularioProducto = false },
            onConfirm = { nuevoProd ->
                vm.crearProducto(nuevoProd)
                mostrarFormularioProducto = false
            }
        )
    }
}