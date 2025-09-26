package com.example.laboratorio8.ui.components



import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingLayout(onClickAnywhere: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClickAnywhere() },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
            CircularProgressIndicator()
            Text("Cargando", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun ErrorLayout(message: String, onRetry: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(message, style = MaterialTheme.typography.bodyMedium)
            Button(onClick = onRetry) { Text("Reintentar") }
        }
    }
}
