package com.bytemap.a24172012068_assignment.screen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.navigation.NavController

data class Incident(
    val name: String,
    val icon: ImageVector
)

@Composable
fun EFirScreen(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
    ) {
        EFirUI()
    }
}

@Composable
fun EFirUI() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        IncidentType()
        IncidentDescription()
        DateTime()
        LocationInput()
        WitnessesInput()
        AdditionalDetails()
        SubmitButton()
    }
}

@Composable
fun IncidentType() {
    val incidentTypes = listOf(
        Incident("Harassment", Icons.Default.Block),
        Incident("Theft", Icons.Default.Lock),
        Incident("Assault", Icons.Default.Dangerous),
        Incident("Fraud", Icons.Default.CreditCard),
        Incident("Emergency", Icons.Default.Emergency),
        Incident("Other", Icons.Default.Notes)
    )

    Text(
        text = "Incident Type",
        modifier = Modifier.padding(bottom = 8.dp),
        fontSize = 18.sp,
        color = Color.DarkGray,
        fontWeight = FontWeight.Medium
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        for (i in incidentTypes.chunked(2)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                i.forEach { incident ->
                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = incident.icon,
                            contentDescription = "${incident.name} Icon"
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = incident.name)
                    }
                }
                if (i.size < 2) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
    
    Text(
        text = "Other incidents", 
        fontStyle = FontStyle.Italic, 
        color = Color.Gray,
        modifier = Modifier.padding(top = 8.dp)
    )
}

@Composable
fun IncidentDescription() {
    var description by remember { mutableStateOf("") }
    Text(
        text = "Incident Description *",
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
        fontSize = 18.sp,
        color = Color.DarkGray,
        fontWeight = FontWeight.Medium
    )
    OutlinedTextField(
        value = description,
        onValueChange = {description = it},
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        placeholder = { Text(text = "Describe what happened in detail...", color = Color.Gray, fontSize = 14.sp) },
        maxLines = 6
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Be as specific as possible",
            color = Color.Gray,
            fontSize = 12.sp
        )
        Text(
            text = "0/5000",
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}

@Composable
fun DateTime() {
    val context = LocalContext.current
    var selectedDate by remember { mutableStateOf("") }
    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val timePickerDialog = TimePickerDialog(
                context,
                { _, hourOfDay, minute ->
                    selectedDate = "$dayOfMonth/${month + 1}/$year  -  ${"%02d".format(hourOfDay)}:${"%02d".format(minute)}"
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false
            )
            timePickerDialog.show()
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    OutlinedTextField(
        value = selectedDate,
        onValueChange = { },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { datePickerDialog.show() },
        label = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Date Icon",
                    tint = Color.Gray,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Select Date & Time",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        },
        placeholder = {
            Text(
                text = "Tap to pick date and time",
                color = Color.Gray,
                fontSize = 13.sp
            )
        },
        readOnly = true, // prevents typing
        enabled = true   // ensures clickable works
    )
}

@Composable
fun LocationInput(){
    Text(
        text = "Location",
        modifier = Modifier.padding(bottom = 8.dp),
        fontSize = 18.sp,
        color = Color.DarkGray,
        fontWeight = FontWeight.Medium
    )
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox( checked = false, onCheckedChange = { } )
        Text(text = "Use current location", color = Color.Gray)
    }
    OutlinedTextField(
        value = "",
        onValueChange = { },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location Icon",
                tint = Color.Gray
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        placeholder = { Text(text = "Enter location details...", color = Color.Gray, fontSize = 14.sp) }
    )
}


@Composable
fun WitnessesInput(){
    var witnesses by remember { mutableStateOf("") }
    Text(
        text = "Witnesses (Optional)",
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
        fontSize = 18.sp,
        color = Color.DarkGray,
        fontWeight = FontWeight.Medium
    )
    Row {
        OutlinedTextField(
            value = witnesses,
            onValueChange = { witnesses = it },
            modifier = Modifier
                .weight(1f)
                .heightIn(min = 56.dp)
                .align(Alignment.CenterVertically),
            placeholder = { Text(text = "Enter witness details...",
                color = Color.Gray, fontSize = 14.sp) }
        )
        Button(
            onClick = { },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(start = 8.dp)
                .heightIn(min = 56.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = "+ Add", fontSize = 16.sp)
        }
    }
}

@Composable
fun AdditionalDetails() {
    var additional by remember { mutableStateOf("") }
    Text(
        text = "Additional Details (Optional)",
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
        fontSize = 18.sp,
        color = Color.DarkGray,
        fontWeight = FontWeight.Medium
    )
    OutlinedTextField(
        value = additional,
        onValueChange = {additional = it},
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        placeholder = { Text(text = "Any additional information, evidence , or notes...",
            color = Color.Gray, fontSize = 14.sp) },
        maxLines = 6
    )
    

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = "0/2000",
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}

@Composable
fun SubmitButton() {
    Button(
        onClick = {},
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFD32F2F),
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
            .height(50.dp)
    ) {
        Icon(
            imageVector = Icons.Default.InsertDriveFile,
            contentDescription = "Submit Icon",
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Submit Report", fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun EFirPreview() {
    EFirUI()
}
