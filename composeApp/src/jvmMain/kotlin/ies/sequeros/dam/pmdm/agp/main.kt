package ies.sequeros.dam.pmdm.agp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "ClienteProductos",
    ) {
        App()
    }
}