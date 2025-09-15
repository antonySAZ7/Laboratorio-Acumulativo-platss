package com.example.laboratorio8.ui.screens.locations



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.laboratorio8.data.LocationDb
import com.example.laboratorio8.data.Location

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDetailScreen(
    id: Int,
    onBack: () -> Unit
) {
    val db = remember { LocationDb() }
    val loc: Location? = remember(id) { db.getLocationById(id) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Location details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        if (loc == null) {
            Box(Modifier.padding(padding).padding(16.dp)) {
                Text("Location not found")
            }
        } else {
            Column(
                Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(text = loc.name, style = androidx.compose.material3.MaterialTheme.typography.headlineSmall)
                InfoRow(label = "ID:", value = "${loc.id}")
                InfoRow(label = "Type:", value = loc.type)
                InfoRow(label = "Dimensions:", value = loc.dimension)
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    androidx.compose.foundation.layout.Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(label)
        Text(value)
    }
}
