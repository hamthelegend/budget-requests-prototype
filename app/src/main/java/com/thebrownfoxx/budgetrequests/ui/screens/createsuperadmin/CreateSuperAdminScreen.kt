package com.thebrownfoxx.budgetrequests.ui.screens.createsuperadmin

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.data.hash.hash
import com.thebrownfoxx.budgetrequests.ui.models.user.Admin
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateSuperAdminScreen(
    onCreateSuperAdmin: (Admin) -> Unit,
) {
    var firstName by remember { mutableStateOf("") }
    var middleName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }

    var firstNameError by remember { mutableStateOf<String?>(null) }
    var middleNameError by remember { mutableStateOf<String?>(null) }
    var lastNameError by remember { mutableStateOf<String?>(null) }
    var usernameError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var repeatPasswordError by remember { mutableStateOf<String?>(null) }

    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Card(modifier = Modifier.align(Alignment.Center)) {
                Column(
                    modifier = Modifier
                        .padding(32.dp)
                        .widthIn(max = 384.dp)
                ) {
                    Text(
                        text = "Create Super Admin",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = firstName,
                        onValueChange = {
                            firstName = it
                            firstNameError = null
                        },
                        label = { Text(text = "First Name") },
                        modifier = Modifier.fillMaxWidth().animateContentSize(),
                        singleLine = true,
                        isError = firstNameError != null,
                        supportingText = firstNameError?.let { firstNameError ->
                            { Text(text = firstNameError) }
                        },
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = middleName,
                        onValueChange = {
                            middleName = it
                            middleNameError = null
                        },
                        label = { Text(text = "Middle name") },
                        modifier = Modifier.fillMaxWidth().animateContentSize(),
                        singleLine = true,
                        isError = middleNameError != null,
                        supportingText = middleNameError?.let { middleNameError ->
                            { Text(text = middleNameError) }
                        },
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = lastName,
                        onValueChange = {
                            lastName = it
                            lastNameError = null
                        },
                        label = { Text(text = "Last name") },
                        modifier = Modifier.fillMaxWidth().animateContentSize(),
                        singleLine = true,
                        isError = lastNameError != null,
                        supportingText = lastNameError?.let { lastNameError ->
                            { Text(text = lastNameError) }
                        },
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = username,
                        onValueChange = {
                            username = it
                            usernameError = null
                        },
                        label = { Text(text = "Username") },
                        modifier = Modifier.fillMaxWidth().animateContentSize(),
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
                        modifier = Modifier.fillMaxWidth().animateContentSize(),
                        singleLine = true,
                        isError = passwordError != null,
                        supportingText = passwordError?.let { passwordError ->
                            { Text(text = passwordError) }
                        },
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = repeatPassword,
                        onValueChange = {
                            repeatPassword = it
                            repeatPasswordError = null
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        label = { Text(text = "Repeat Password") },
                        modifier = Modifier.fillMaxWidth().animateContentSize(),
                        singleLine = true,
                        isError = repeatPasswordError != null,
                        supportingText = repeatPasswordError?.let { repeatPasswordError ->
                            { Text(text = repeatPasswordError) }
                        },
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            var hasError = false

                            if (firstName == "") {
                                hasError = true
                                firstNameError = "First name required"
                            }

                            if (lastName == "") {
                                hasError = true
                                lastNameError = "Last name required"
                            }

                            if (username == "") {
                                hasError = true
                                usernameError = "Username required"
                            }

                            if (password == "") {
                                hasError = true
                                passwordError = "Password required"
                            }

                            if (password != repeatPassword) {
                                hasError = true
                                repeatPasswordError = "Password does not match"
                            }

                            if (!hasError) {
                                val superAdmin = Admin(
                                    firstName = firstName,
                                    middleName = if (middleName == "") null else middleName,
                                    lastName = lastName,
                                    username = username,
                                    passwordHash = hash(password),
                                    isSuperAdmin = true,
                                )
                                onCreateSuperAdmin(superAdmin)
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
fun CreateSuperAdminScreenPreview() {
    BudgetRequestsTheme {
        CreateSuperAdminScreen(
            onCreateSuperAdmin = {}
        )
    }
}