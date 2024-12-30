package com.example.p12_.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.example.p12_.ui.customwidget.CostumeTopAppBar
import com.example.p12_.ui.navigation.DestinasiNavigasi

object DestinasiDetail: DestinasiNavigasi {
    override val route = "detail"
    const val NIM = "nim"
    override val titleRes = "Detail Mahasiswa"
    val routeWithArg = "$route/{$NIM}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailView(
    navigateBack: () -> Unit,
    navigateToEdit: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetail.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToEdit,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Delete Mahasiswa"
                )
            }
        }
    ) { innerPadding ->
        BodyDetailMhs(
            detailUiState = viewModel.detailUiState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyDetailMhs(
    detailUiState: DetailUiState,
    modifier: Modifier = Modifier
) {
    when {
        detailUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        detailUiState.isError -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = detailUiState.errorMessage,
                    color = Color.Red
                )
            }
        }
        detailUiState.isUiEventNotEmpty -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailMhs(
                    mahasiswa = detailUiState.detailUiEvent.toMhs(),
                    modifier = modifier
                )
            }
        }
    }
}