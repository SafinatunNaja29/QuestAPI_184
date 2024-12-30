package com.example.p12_.ui.view

import com.example.p12_.ui.navigation.DestinasiNavigasi

object DestinasiDetail: DestinasiNavigasi {
    override val route = "detail"
    const val NIM = "nim"
    override val titleRes = "Detail Mahasiswa"
    val routeWithArg = "$route/{$NIM}"
}