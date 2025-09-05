package com.example.laboratorio8.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.laboratorio8.R

@Composable
fun LoginScreen(onLoginClick: () -> Unit) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(R.drawable.small_ip_page_rick_and_morty_icon_894e334f64),
                contentDescription = "Logo",
                modifier = Modifier.size(220.dp)
            )
            Spacer(Modifier.height(24.dp))
            Button(onClick = onLoginClick) { Text("Entrar") }
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Antony Saz 24710",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
