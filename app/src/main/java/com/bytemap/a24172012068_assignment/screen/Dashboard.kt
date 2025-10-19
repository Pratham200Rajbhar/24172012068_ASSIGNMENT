package com.bytemap.a24172012068_assignment.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Dashboard() {
    Column (modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Card (modifier = Modifier.fillMaxWidth())
        {
            Row {
                Text(text = "79/100", modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                    fontSize = 40.sp)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DashboardPreview() {
    Dashboard()
}