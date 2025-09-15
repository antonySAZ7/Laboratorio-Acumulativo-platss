package com.example.laboratorio8.ui.screens.locations


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.laboratorio8.data.LocationDb

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationsListScreen(
    onLocationClick: (Int) -> Unit
) {
    val db = remember { LocationDb() }
    val locations = remember { db.getAllLocations() } // Nombre y Tipo

    Scaffold(
        topBar = { TopAppBar(title = { Text("Locations") }) }
    ) { padding ->
        LazyColumn(Modifier.padding(padding)) {
            items(locations) { loc ->
                Column(
                    Modifier
                        .fillMaxWidth()
                        .clickable { onLocationClick(loc.id) }
                        .padding(16.dp)
                ) {
                    Text(text = loc.name, style = MaterialTheme.typography.titleMedium)
                    Text(text = loc.type, style = MaterialTheme.typography.bodyMedium)
                }
                Divider()
            }
        }
    }
}
