package ies.sequeros.dam.pmdm.agp.vista

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual object IODispatchers {
    actual val io: CoroutineDispatcher= Dispatchers.IO

}