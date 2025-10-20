package com.bytemap.a24172012068_assignment.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title : String,
    canPop : Boolean,
    onNavIconClick : () -> Unit
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = onNavIconClick) {
                if (canPop){
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                else{
                    Icon(
                        Icons.Default.Menu,
                        contentDescription = "Menu"
                    )
                }
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun AppBarPreview() {
    AppBar(
        title = "App Bar Title",
        canPop = true,
        onNavIconClick = {}
    )
}