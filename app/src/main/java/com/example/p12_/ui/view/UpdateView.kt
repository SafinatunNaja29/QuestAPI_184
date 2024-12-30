package com.example.p12_.ui.view

import com.example.p12_.ui.navigation.DestinasiNavigasi

object DestinasiUpdate : DestinasiNavigasi {
    override val route = "update"
    const val NIM = "nim"
    override val titleRes = "Edit Mahasiswa"
    val routeWithArg = "$route/{$NIM}"
}