package ies.sequeros.dam.pmdm.agp

import kotlinx.coroutines.CoroutineDispatcher

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
expect val DispatcherIO: CoroutineDispatcher