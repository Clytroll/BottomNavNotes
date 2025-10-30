package com.example.bottomnavnotes

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ExperimentalMaterial3Api  // <— pastikan ini ada

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(title: String, content: String) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Detail Catatan") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(text = title, style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(8.dp))
            Text(text = content, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
