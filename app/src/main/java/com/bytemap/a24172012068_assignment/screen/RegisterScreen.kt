package com.bytemap.a24172012068_assignment.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bytemap.a24172012068_assignment.R
import com.bytemap.a24172012068_assignment.components.FormField

@Composable
fun RegisterScreen() {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var emergencyName by remember { mutableStateOf("") }
    var emergencyPhone by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Register Image",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(150.dp)
                .padding(0.dp, 50.dp, 0.dp, 10.dp)
        )

        Text(
            text = "Create Account",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 10.dp),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Fill in your details to register",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(30.dp))

        // Full Name
        FormField(
            label = "Full Name",
            textState = fullName,
            onTextChange = { fullName = it },
            icon = Icons.Default.Person
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Email
        FormField(
            label = "Email",
            textState = email,
            onTextChange = { email = it },
            icon = Icons.Default.Person
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Password
        FormField(
            label = "Password",
            textState = password,
            onTextChange = { password = it },
            icon = Icons.Default.VisibilityOff,
            isPasswordField = true
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Phone (Optional)
        FormField(
            label = "Phone Number (Optional)",
            textState = phone,
            onTextChange = { phone = it },
            icon = Icons.Default.Call
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Emergency Contact Name
        FormField(
            label = "Emergency Contact Name (Optional)",
            textState = emergencyName,
            onTextChange = { emergencyName = it },
            icon = Icons.Default.Person
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Emergency Contact Phone
        FormField(
            label = "Emergency Contact Phone (Optional)",
            textState = emergencyPhone,
            onTextChange = { emergencyPhone = it },
            icon = Icons.Default.Call
        )
        Spacer(modifier = Modifier.height(30.dp))

        // Register Button
        Button(
            modifier = Modifier
                .width(250.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF000000),
                contentColor = Color.White
            ),
            onClick = {
                // TODO: Add Register Logic
            }
        ) {
            Text(text = "Register", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Already have an account?",
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Login",
                fontSize = 18.sp,
                color = Color.Red
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}
