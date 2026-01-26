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
import ies.sequeros.dam.pmdm.agp.modelo.Producto
import ies.sequeros.dam.pmdm.agp.vista.ProductoViewModel
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListadoProducto() {
    //injeccion de dependencias
    val vm: ProductoViewModel= koinViewModel ()
    val navigator = rememberListDetailPaneScaffoldNavigator<Nothing>()
    // estado de selección
    var selectedItem by remember { mutableStateOf<Producto?>(null) }
    val items=vm.items.collectAsState()
    //corrutina
    val scope = rememberCoroutineScope()

    // al seleccionar un elemento: cargar borrador desde el contenido
    fun onSelect(item: Producto) {
        selectedItem = item
        scope.launch {
            navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
        }
    }
    //tamaño de la ventana, para mostrar o no el botón de back
    val windowInfo=currentWindowAdaptiveInfo()
    val directive = calculatePaneScaffoldDirective(windowInfo)
    val mostrarBotonAtras= directive.maxHorizontalPartitions == 1
    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            PanelListadoProducto(
                items = items.value,
                selected = selectedItem,
                onSelect = ::onSelect
            )
        },
        detailPane = {
            DetalleProducto(
                item = selectedItem,
                onBack = {
                    scope.launch {
                        navigator.navigateBack()
                    }
                },
                mostrarBotonAtras
            )
        },
    )
}