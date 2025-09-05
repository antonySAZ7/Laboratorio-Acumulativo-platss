package com.example.laboratorio8.ui.screens



import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.laboratorio8.data.CharacterDb
import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color


private fun avatarColorFor(id: Int, cs: ColorScheme): Color {

    val palette = listOf(
        cs.primaryContainer,
        cs.secondaryContainer,
        cs.tertiaryContainer,
        cs.surfaceVariant,
        cs.inversePrimary
    )
    return palette[(id - 1).mod(palette.size)]
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(onCharacterClick: (Int) -> Unit) {
    val characters = CharacterDb().getAllCharacters()
    val cs = MaterialTheme.colorScheme

    Scaffold(
        containerColor = cs.background,
        topBar = {
            TopAppBar(
                title = { Text("Characters") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = cs.primary,
                    titleContentColor = cs.onPrimary
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(items = characters, key = { it.id }) { c ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onCharacterClick(c.id) }
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Avatar con color diferente según el ID
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(avatarColorFor(c.id, cs))
                    )

                    Spacer(Modifier.width(12.dp))

                    Column {
                        Text(
                            c.name,
                            style = MaterialTheme.typography.titleMedium,
                            color = cs.onBackground
                        )
                        Text(
                            "${c.species} - ${c.status}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = cs.onSurfaceVariant
                        )
                    }
                }
                Divider(color = cs.outline.copy(alpha = 0.3f))
            }
        }
    }
}