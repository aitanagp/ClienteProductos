package ies.sequeros.dam.pmdm.agp.vista

import kotlinx.coroutines.CoroutineDispatcher

expect object IODispatchers {
    val io: CoroutineDispatcher
}