package com.thebrownfoxx.budgetrequests.ui

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.budgetrequests.data.getRandomPassword
import com.thebrownfoxx.budgetrequests.data.hash.hash
import com.thebrownfoxx.budgetrequests.ui.models.user.User
import com.thebrownfoxx.budgetrequests.ui.models.user.admin.Admin
import com.thebrownfoxx.budgetrequests.ui.models.user.officer.Officer
import com.thebrownfoxx.budgetrequests.ui.shared.ChoiceChip
import com.thebrownfoxx.budgetrequests.ui.theme.BudgetRequestsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUserScreen(
    onAddUser: (User) -> Unit,
    onClose: () -> Unit,
) {
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    var isAdmin by remember { mutableStateOf(false) }
    var firstName by remember { mutableStateOf("") }
    var middleName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var isSuperAdmin by remember { mutableStateOf(false) }

    var firstNameError by remember { mutableStateOf<String?>(null) }
    var middleNameError by remember { mutableStateOf<String?>(null) }
    var lastNameError by remember { mutableStateOf<String?>(null) }
    var usernameError by remember { mutableStateOf<String?>(null) }

    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Card(modifier = Modifier.align(Alignment.Center)) {
                Column(
                    modifier = Modifier
                        .padding(32.dp)
                        .widthIn(max = 384.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = onClose) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Add User",
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        ChoiceChip(
                            text = "Officer",
                            active = !isAdmin,
                            onClick = { isAdmin = false },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        ChoiceChip(
                            text = "Admin",
                            active = isAdmin,
                            onClick = { isAdmin = true },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = firstName,
                        onValueChange = {
                            firstName = it
                            firstNameError = null
                        },
                        label = { Text(text = "First Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        singleLine = true,
                        isError = usernameError != null,
                        supportingText = usernameError?.let { usernameError ->
                            { Text(text = usernameError) }
                        },
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AnimatedVisibility(visible = isAdmin) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "Super Admin")
                            Spacer(modifier = Modifier.weight(1f))
                            Checkbox(checked = isSuperAdmin, onCheckedChange = { isSuperAdmin = it })
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
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

                            val password = getRandomPassword(16)

                            if (!hasError) {
                                val user = if (isAdmin) Admin(
                                    firstName = firstName,
                                    middleName = if (middleName == "") null else middleName,
                                    lastName = lastName,
                                    username = username,
                                    passwordHash = hash(password),
                                    isSuperAdmin = isSuperAdmin,
                                ) else Officer(
                                    firstName = firstName,
                                    middleName = if (middleName == "") null else middleName,
                                    lastName = lastName,
                                    username = username,
                                    passwordHash = hash(password),
                                )
                                clipboardManager.setText(AnnotatedString(password))
                                Toast.makeText(context, "Password copied to clipboard", Toast.LENGTH_LONG).show()
                                onAddUser(user)
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
fun AddUserScreenPreview() {
    BudgetRequestsTheme {
        AddUserScreen(onAddUser = {}, onClose = {})
    }
}