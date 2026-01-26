package ies.sequeros.dam.pmdm.agp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ies.sequeros.dam.pmdm.agp.di.moduloAplicacion
import ies.sequeros.dam.pmdm.agp.di.moduloDominio
import ies.sequeros.dam.pmdm.agp.di.moduloInfraestructuran
import ies.sequeros.dam.pmdm.agp.di.moduloPresentacion
import org.koin.core.context.GlobalContext.startKoin

fun main() = application {
    startKoin {
        modules(moduloInfraestructuran,moduloDominio,moduloAplicacion, moduloPresentacion)
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "ClienteProductos",
    ) {
        App()
    }
}