package org.heartimaging.echoindications.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import org.heartimaging.echoindications.auth.AuthManager
import org.heartimaging.echoindications.model.Indication
import org.heartimaging.echoindications.nav.Routes
import org.heartimaging.echoindications.network.ApiClient
import org.heartimaging.echoindications.ui.theme.AUCCanBeConsidered
import org.heartimaging.echoindications.ui.theme.AUCIndicated
import org.heartimaging.echoindications.ui.theme.AUCNotIndicated
import org.heartimaging.echoindications.ui.theme.NHSBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IndicationDetailScreen(
    indicationId: Int,
    auth: AuthManager,
    nav: NavHostController
) {
    val token by auth.token.collectAsState()
    val isAuthenticated by auth.isAuthenticated.collectAsState()
    val scope = rememberCoroutineScope()

    var indication by remember { mutableStateOf<Indication?>(null) }
    var loadError by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var showMenu by remember { mutableStateOf(false) }
    var showDeleteConfirm by remember { mutableStateOf(false) }
    var isDeleting by remember { mutableStateOf(false) }
    var deleteError by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(indicationId, token) {
        isLoading = true
        loadError = null
        try {
            indication = ApiClient(token).getIndication(indicationId)
        } catch (e: Throwable) {
            loadError = e.message ?: "Could not load"
        }
        isLoading = false
    }

    // Refetch when the edit screen pops back with a result flag.
    val savedHandle = nav.currentBackStackEntry?.savedStateHandle
    val updatedFlow = savedHandle?.getStateFlow("indicationUpdated", false)
    val updated by (updatedFlow?.collectAsState() ?: remember { mutableStateOf(false) })

    LaunchedEffect(updated) {
        if (updated) {
            savedHandle?.set("indicationUpdated", false)
            try {
                indication = ApiClient(token).getIndication(indicationId)
            } catch (e: Throwable) {
                loadError = e.message ?: "Could not refresh"
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(indication?.title?.take(30) ?: "Indication") },
                navigationIcon = {
                    IconButton(onClick = { nav.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    if (isAuthenticated && indication != null) {
                        Box {
                            IconButton(onClick = { showMenu = true }) {
                                if (isDeleting) CircularProgressIndicator(modifier = Modifier.padding(4.dp))
                                else Icon(Icons.Filled.MoreVert, contentDescription = "Admin actions")
                            }
                            DropdownMenu(
                                expanded = showMenu,
                                onDismissRequest = { showMenu = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Edit") },
                                    leadingIcon = { Icon(Icons.Filled.Edit, contentDescription = null) },
                                    onClick = {
                                        showMenu = false
                                        nav.navigate(Routes.indicationEdit(indicationId))
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Delete", color = MaterialTheme.colorScheme.error) },
                                    leadingIcon = {
                                        Icon(
                                            Icons.Filled.Delete,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.error
                                        )
                                    },
                                    onClick = {
                                        showMenu = false
                                        showDeleteConfirm = true
                                    }
                                )
                            }
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when {
                isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = NHSBlue)
                    }
                }
                loadError != null -> {
                    Text(
                        loadError ?: "",
                        modifier = Modifier.padding(32.dp),
                        color = MaterialTheme.colorScheme.error
                    )
                }
                indication != null -> {
                    DetailForm(indication!!)
                }
            }
        }
    }

    if (showDeleteConfirm) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirm = false },
            title = { Text("Delete this indication?") },
            text = { Text("This is permanent. The indication will be removed from the database.") },
            confirmButton = {
                TextButton(onClick = {
                    showDeleteConfirm = false
                    val ind = indication ?: return@TextButton
                    scope.launch {
                        isDeleting = true
                        try {
                            ApiClient(token).deleteIndication(ind.id)
                            nav.popBackStack()
                        } catch (e: Throwable) {
                            deleteError = e.message ?: "Could not delete"
                        }
                        isDeleting = false
                    }
                }) {
                    Text("Delete", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirm = false }) { Text("Cancel") }
            }
        )
    }

    deleteError?.let { msg ->
        AlertDialog(
            onDismissRequest = { deleteError = null },
            title = { Text("Could not delete") },
            text = { Text(msg) },
            confirmButton = {
                TextButton(onClick = { deleteError = null }) { Text("OK") }
            }
        )
    }
}

@Composable
private fun DetailForm(ind: Indication) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Title
        Text(
            ind.title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(20.dp))

        // AUCs
        Section("Appropriate Use Criteria") {
            AUCRow("Primary care", ind.primaryCare)
            AUCRow("Secondary inpatient", ind.secondaryInpatient)
            AUCRow("Secondary outpatient", ind.secondaryOutpatient)
        }

        // Urgency
        Section("Urgency") {
            Text(
                ind.urgency?.replace('_', ' ')?.replaceFirstChar { it.uppercaseChar() } ?: "—"
            )
        }

        // Contexts
        if (ind.contexts.isNotEmpty()) {
            Section("Contexts") {
                ind.contexts.forEach { ctx -> Text(ctx.name) }
            }
        }

        // Sources
        Section("Sources") {
            Text(if (ind.sourceList.isEmpty()) "—" else ind.sourceList)
        }

        // Comments
        if (ind.comments.isNotEmpty()) {
            Section("Comments") { Text(ind.comments) }
        }

        // Keywords
        if (ind.keywords.isNotEmpty()) {
            Section("Keywords") {
                Text(
                    ind.keywords,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun Section(title: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            title.uppercase(),
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(4.dp))
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                content()
            }
        }
    }
}

@Composable
private fun AUCRow(label: String, value: String?) {
    val pretty = if (value.isNullOrEmpty()) "—"
    else value.replace('_', ' ').replaceFirstChar { it.uppercaseChar() }
    val colour = when (value) {
        "indicated" -> AUCIndicated
        "not_indicated" -> AUCNotIndicated
        "can_be_considered" -> AUCCanBeConsidered
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(label, modifier = Modifier.weight(1f))
        Text(pretty, color = colour, fontWeight = FontWeight.Medium)
    }
}
