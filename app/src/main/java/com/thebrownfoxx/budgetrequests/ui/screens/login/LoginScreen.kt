package com.thebrownfoxx.budgetrequests.ui.screens.login

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.data.datasource.EmptyDataSource
import com.thebrownfoxx.budgetrequests.data.hash.hash
import com.thebrownfoxx.budgetrequests.ui.models.user.User
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLogin: (User) -> Unit,
) {
    val dataSource = EmptyDataSource

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var usernameError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Card(modifier = Modifier.align(Alignment.Center)) {
                Column(modifier = Modifier
                    .padding(32.dp)
                    .widthIn(max = 384.dp)) {
                    Text(
                        text = "Budget Requests",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = username,
                        onValueChange = {
                            username = it
                            usernameError = null
                        },
                        label = { Text(text = "Username") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        singleLine = true,
                        isError = usernameError != null,
                        supportingText = usernameError?.let { usernameError ->
                            { Text(text = usernameError) }
                        },
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            passwordError = null
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        label = { Text(text = "Password") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        singleLine = true,
                        isError = passwordError != null,
                        supportingText = passwordError?.let { passwordError ->
                            { Text(text = passwordError) }
                        },
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            var hasError = false

                            if (username == "") {
                                hasError = true
                                usernameError = "Username required"
                            }

                            if (password == "") {
                                hasError = true
                                passwordError = "Password required"
                            }

                            val user = dataSource.getUser(username)

                            if (!hasError) {
                                if (user == null) {
                                    usernameError = "Username not found"
                                } else {
                                    val passwordHash = hash(password, user.passwordHash.salt)

                                    if (passwordHash != user.passwordHash) {
                                        hasError = true
                                        passwordError = "Incorrect password"
                                    }

                                    if (!hasError) {
                                        onLogin(user)
                                    }
                                }
                            }

                        },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(text = "Login")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    BudgetRequestsTheme {
        LoginScreen(onLogin = {})
    }
}