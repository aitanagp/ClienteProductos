package ies.sequeros.dam.pmdm.agp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        modules(moduloInfraestructuran,moduloDominio, moduloAplicacion, moduloPresentacion)

    }
    ComposeViewport {
        App()
    }
}