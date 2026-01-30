package ies.sequeros.dam.pmdm.agp.vista.componentes

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
import ies.sequeros.dam.pmdm.agp.modelo.Categoria
import ies.sequeros.dam.pmdm.agp.modelo.Producto
import ies.sequeros.dam.pmdm.agp.vista.ProductoViewModel
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListadoProducto(
    categoriaFiltro: Categoria?
) {
    val vm: ProductoViewModel = koinViewModel()
    val navigator = rememberListDetailPaneScaffoldNavigator<Nothing>()
    var selectedItem by remember { mutableStateOf<Producto?>(null) }
    val items by vm.items.collectAsState()
    val scope = rememberCoroutineScope()

    val productosFiltrados = remember(items, categoriaFiltro) {
        if (categoriaFiltro == null) {
            items
        } else {
            items.filter { it.categoria == categoriaFiltro.id }
        }
    }

    fun onSelect(item: Producto) {
        selectedItem = item
        scope.launch {
            navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
        }
    }

    val windowInfo = currentWindowAdaptiveInfo()
    val directive = calculatePaneScaffoldDirective(windowInfo)
    val mostrarBotonAtras = directive.maxHorizontalPartitions == 1

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            PanelListadoProducto(
                items = productosFiltrados,
                selected = selectedItem,
                onSelect = ::onSelect
            )
        },
        detailPane = {
            DetalleProducto(
                item = selectedItem,
                onBack = { scope.launch { navigator.navigateBack() } },
                mostrarBotonAtras
            )
        },
    )
}