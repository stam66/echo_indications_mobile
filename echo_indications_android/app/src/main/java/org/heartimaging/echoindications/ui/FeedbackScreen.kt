package org.heartimaging.echoindications.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import org.heartimaging.echoindications.auth.AuthManager
import org.heartimaging.echoindications.network.ApiClient
import org.heartimaging.echoindications.ui.theme.AUCIndicated

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackScreen(auth: AuthManager, nav: NavHostController) {
    var requestText by remember { mutableStateOf("") }
    var requestor by remember { mutableStateOf("") }
    var isSubmitting by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    var didSubmit by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Submit feedback") },
                navigationIcon = {
                    IconButton(onClick = { nav.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (didSubmit) {
                SuccessState { nav.popBackStack() }
            } else {
                Text(
                    "Suggest a new indication, report a bug, or flag something that needs editing.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall
                )

                OutlinedTextField(
                    value = requestText,
                    onValueChange = { requestText = it },
                    label = { Text("Your feedback") },
                    minLines = 5,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = requestor,
                    onValueChange = { requestor = it },
                    label = { Text("Name or email (optional)") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.None),
                    supportingText = { Text("Leave blank to submit anonymously.") },
                    modifier = Modifier.fillMaxWidth()
                )

                error?.let {
                    Text(it, color = MaterialTheme.colorScheme.error)
                }

                Spacer(Modifier.height(4.dp))
                Button(
                    onClick = {
                        scope.launch {
                            isSubmitting = true
                            error = null
                            try {
                                ApiClient().submitIssue(
                                    request = requestText.trim(),
                                    requestor = requestor.trim()
                                )
                                didSubmit = true
                            } catch (e: Throwable) {
                                error = e.message ?: "Could not send"
                            }
                            isSubmitting = false
                        }
                    },
                    enabled = requestText.isNotBlank() && !isSubmitting,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (isSubmitting) {
                        CircularProgressIndicator(
                            modifier = Modifier.height(20.dp),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    } else {
                        Text("Send feedback")
                    }
                }
            }
        }
    }
}

@Composable
private fun SuccessState(onDone: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                Icons.Filled.CheckCircle,
                contentDescription = null,
                tint = AUCIndicated,
                modifier = Modifier.size(60.dp)
            )
            Spacer(Modifier.height(14.dp))
            Text("Thank you", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(6.dp))
            Text(
                "Your feedback has been received.",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(20.dp))
            Button(onClick = onDone) { Text("Done") }
        }
    }
}
