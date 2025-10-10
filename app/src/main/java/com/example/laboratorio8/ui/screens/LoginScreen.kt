package com.example.laboratorio8.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.laboratorio8.R
import com.example.laboratorio8.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.small_ip_page_rick_and_morty_icon_894e334f64),
                        contentDescription = "Logo",
                        modifier = Modifier.size(220.dp)
                    )

                    Spacer(Modifier.height(32.dp))

                    OutlinedTextField(
                        value = uiState.userName,
                        onValueChange = { viewModel.onNameChange(it) },
                        label = { Text("Nombre") },
                        isError = !uiState.isNameValid,
                        supportingText = {
                            if (!uiState.isNameValid) {
                                Text("Por favor ingresa tu nombre")
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(Modifier.height(24.dp))

                    Button(
                        onClick = { viewModel.onLoginClick(onLoginClick) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Entrar")
                    }

                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = "Antony Saz 24710",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}