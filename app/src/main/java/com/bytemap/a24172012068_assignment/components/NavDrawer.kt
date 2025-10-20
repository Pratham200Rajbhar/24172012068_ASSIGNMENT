package com.bytemap.a24172012068_assignment.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding

@Composable
fun NavDrawer(
    menuItems: List<Pair<String, String>>, // Pair<Label, Route>
    currentRoute: String,
    onItemClick: (String) -> Unit
) {
    ModalDrawerSheet {
        Text(
            text = "My App",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Divider()
        Spacer(modifier = Modifier.padding(8.dp))
        menuItems.forEach { (label, route) ->
            NavigationDrawerItem(
                label = { Text(label) },
                selected = currentRoute == route,
                onClick = { onItemClick(route) },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavDrawerPreview() {
    MaterialTheme {
        NavDrawer(
            menuItems = listOf(
                "Home" to "home",
                "e-FIR" to "efir"
            ),
            currentRoute = "home",
            onItemClick = {}
        )
    }
}
