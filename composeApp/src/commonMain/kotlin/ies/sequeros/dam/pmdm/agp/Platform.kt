package ies.sequeros.dam.pmdm.agp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform