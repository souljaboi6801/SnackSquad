package com.example.snackordering

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.snackordering.ui.theme.SnackOrderingTheme

class LoginActivity : ComponentActivity() {
    private lateinit var databaseHelper: UserDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHelper = UserDatabaseHelper(this)
        setContent {
            SnackOrderingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF121212) // Changed background color to dark
                ) {
                    LoginScreen(this, databaseHelper)
                }
            }
        }
    }
}

@Composable
fun LoginScreen(context: Context, databaseHelper: UserDatabaseHelper) {

    Image(
        painterResource(id = R.drawable.order),
        contentDescription = "",
        alpha = 0.3F,
        contentScale = ContentScale.FillHeight
    )

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Welcome to Snack Squad",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            color = Color(0xFFF8B400) // Changed text color to a vibrant yellow-orange
        )
        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username", color = Color(0xFFF8B400)) }, // Modified label color
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFF1E1E1E), // Darker background for text fields
                focusedIndicatorColor = Color(0xFFF8B400), // Yellow-orange indicator
                cursorColor = Color(0xFFF8B400)
            ),
            modifier = Modifier
                .padding(10.dp)
                .width(280.dp)
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password", color = Color(0xFFF8B400)) }, // Modified label color
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFF1E1E1E),
                focusedIndicatorColor = Color(0xFFF8B400),
                cursorColor = Color(0xFFF8B400)
            ),
            modifier = Modifier
                .padding(10.dp)
                .width(280.dp)
        )

        if (error.isNotEmpty()) {
            Text(
                text = error,
                color = Color.Red, // Keep error text red for emphasis
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        Button(
            onClick = {
                if (username.isNotEmpty() && password.isNotEmpty()) {
                    val user = databaseHelper.getUserByUsername(username)
                    if (user != null && user.password == password) {
                        error = "Successfully logged in"
                        context.startActivity(
                            Intent(
                                context,
                                MainPage::class.java
                            )
                        )
                    } else if (user != null && user.password == "admin") {
                        error = "Successfully logged in as Admin"
                        context.startActivity(
                            Intent(
                                context,
                                AdminActivity::class.java
                            )
                        )
                    } else {
                        error = "Invalid username or password"
                    }
                } else {
                    error = "Please fill all fields"
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50)), // Green button color
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Login", color = Color.White)
        }

        Row {
            TextButton(onClick = {
                context.startActivity(
                    Intent(
                        context,
                        MainActivity::class.java
                    )
                )
            }) {
                Text(color = Color(0xFFF8B400), text = "Sign up") // Updated button text color
            }
            Spacer(modifier = Modifier.width(60.dp))
            TextButton(onClick = {}) {
                Text(color = Color(0xFFF8B400), text = "Forgot password?") // Updated button text color
            }
        }
    }
}
