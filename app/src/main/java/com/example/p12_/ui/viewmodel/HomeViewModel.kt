package com.example.p12_.ui.viewmodel

import com.example.p12_.model.Mahasiswa

sealed class HomeUiState{
    data class Success(val mhs: List<Mahasiswa>): HomeUiState()
    object Loading: HomeUiState()
    object Error: HomeUiState()
}