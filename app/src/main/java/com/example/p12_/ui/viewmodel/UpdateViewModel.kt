package com.example.p12_.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p12_.model.Mahasiswa
import com.example.p12_.repository.MahasiswaRepository
import kotlinx.coroutines.launch

class UpdateViewModel(
    savedStateHandle: SavedStateHandle,
    private val mahasiswaRepository: MahasiswaRepository
) : ViewModel() {
    var updateUIState by mutableStateOf(InsertUiState())
        private set
    private val _nim: String = checkNotNull(savedStateHandle[DestinasiUpdate.NIM])

    init{
        viewModelScope.launch {
            updateUIState = mahasiswaRepository.getMahasiswaByNim(_nim)
                .filterNotNull()
                .first()
                .toUIStateMhs()
        }
    }
    fun updateState(insertUiEvent: InsertUiEvent) {
        updateUIState = updateUIState.copy(insertUiEvent = insertUiEvent)
    }

    suspend fun updateMahasiswa() {
        try {
            mahasiswaRepository.updateMahasiswa(
                nim = _nim,
                mahasiswa = updateUIState.insertUiEvent.toMhs()
            )
        } catch (e: Exception) {
            updateUIState = updateUIState.copy(error = e.message)
        }
    }
}

fun Mahasiswa.toUIStateMhs(): InsertUiState = InsertUiState(
    insertUiEvent = this.toDetailUiEvent(),
)