package ies.sequeros.dam.pmdm.agp

import kotlinx.coroutines.CoroutineDispatcher

class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual fun getPlatform(): Platform = WasmPlatform()
actual val DispatcherIO: CoroutineDispatcher
    get() = TODO("Not yet implemented")