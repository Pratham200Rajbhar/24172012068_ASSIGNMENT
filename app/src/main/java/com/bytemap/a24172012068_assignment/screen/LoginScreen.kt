package com.bytemap.a24172012068_assignment.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import com.bytemap.a24172012068_assignment.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bytemap.a24172012068_assignment.components.FormField

@Composable
fun LoginScreen() {
    var emailState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Image(painter = painterResource(R.drawable.logo),
            contentDescription = "Login Image",
            modifier = Modifier
                .align(
                    alignment = Alignment.CenterHorizontally
                )
                .padding(0.dp, 50.dp, 0.dp, 10.dp))

        Text(text = "Welcome Back", modifier = Modifier
            .align(
                alignment = Alignment.CenterHorizontally
            )
            .padding(0.dp, 20.dp, 0.dp, 10.dp),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Sign in to access your account",
            modifier = Modifier.align(
            alignment = Alignment.CenterHorizontally),
            fontSize = 16.sp,
            )
        Spacer(modifier = Modifier.height(30.dp))
        FormField(
            label = "Email",
            textState = emailState,
            onTextChange = { emailState = it },
            icon = Icons.Default.Person
        )
        Spacer(modifier = Modifier.height(10.dp))
        FormField(
            label = "Password",
            textState = passwordState,
            onTextChange = { passwordState = it },
            icon = Icons.Default.VisibilityOff,
            isPasswordField = true
        )
        Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
            )
            {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { isChecked = it }
                    )
                    Text(
                        text = "Remember Me",
                        fontSize = 15.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Text(
                    text = "Forgot Password?",
                    fontSize = 15.sp
                )
            }

        Button( modifier = Modifier.width(250.dp)
            .height(90.dp)
            .padding(top = 30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF000000),
                contentColor = Color.White
            )
            , onClick = {
        }) {
            Text(text = "Login", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row (
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Don't have an account?",
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Sign up",
                fontSize = 18.sp,
                color = Color.Red
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    LoginScreen()
}