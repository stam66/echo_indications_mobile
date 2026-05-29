package org.heartimaging.echoindications.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.FilterAltOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.heartimaging.echoindications.auth.AuthManager
import org.heartimaging.echoindications.model.Context
import org.heartimaging.echoindications.model.Indication
import org.heartimaging.echoindications.nav.Routes
import org.heartimaging.echoindications.network.ApiClient
import org.heartimaging.echoindications.ui.theme.NHSBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IndicationsListScreen(auth: AuthManager, nav: NavHostController) {
    val token by auth.token.collectAsState()

    var indications by remember { mutableStateOf<List<Indication>>(emptyList()) }
    var contexts by remember { mutableStateOf<List<Context>>(emptyList()) }
    var selectedContext by remember { mutableStateOf<Context?>(null) }
    var searchText by remember { mutableStateOf("") }
    var loadError by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var showFilterMenu by remember { mutableStateOf(false) }

    LaunchedEffect(token) {
        isLoading = true
        loadError = null
        try {
            val api = ApiClient(token)
            indications = api.getIndications()
            contexts = api.getContexts(activeOnly = true).sortedBy { it.sortOrder ?: 0 }
        } catch (e: Throwable) {
            loadError = e.message ?: "Could not load indications"
        }
        isLoading = false
    }

    val filtered by remember(indications, searchText, selectedContext) {
        derivedStateOf {
            val byContext = selectedContext?.let { ctx ->
                indications.filter { ind -> ind.contexts.any { c -> c.id == ctx.id } }
            } ?: indications
            val q = searchText.trim().lowercase()
            if (q.isEmpty()) byContext
            else byContext.filter {
                it.title.lowercase().contains(q) || it.keywords.lowercase().contains(q)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(selectedContext?.name ?: "Indications") },
                navigationIcon = {
                    IconButton(onClick = { nav.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    if (contexts.isNotEmpty()) {
                        Box {
                            IconButton(onClick = { showFilterMenu = true }) {
                                Icon(
                                    imageVector = if (selectedContext == null) Icons.Filled.FilterAltOff else Icons.Filled.FilterAlt,
                                    contentDescription = "Filter by context",
                                    tint = if (selectedContext != null) NHSBlue else MaterialTheme.colorScheme.onSurface
                                )
                            }
                            DropdownMenu(
                                expanded = showFilterMenu,
                                onDismissRequest = { showFilterMenu = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("All contexts") },
                                    onClick = {
                                        selectedContext = null
                                        showFilterMenu = false
                                    }
                                )
                                HorizontalDivider()
                                contexts.forEach { ctx ->
                                    DropdownMenuItem(
                                        text = { Text(ctx.name) },
                                        onClick = {
                                            selectedContext = ctx
                                            showFilterMenu = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text("Search title or keywords") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            when {
                isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator(color = NHSBlue)
                            Spacer(Modifier.height(12.dp))
                            Text("Loading…")
                        }
                    }
                }
                loadError != null -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(32.dp)
                        ) {
                            Text(
                                "Could not load",
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.error
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                loadError ?: "",
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
                filtered.isEmpty() -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            "No indications match",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 4.dp)
                    ) {
                        items(filtered, key = { it.id }) { ind ->
                            IndicationRow(ind) {
                                nav.navigate(Routes.indicationDetail(ind.id))
                            }
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun IndicationRow(ind: Indication, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(ind.title, color = MaterialTheme.colorScheme.onSurface)
        if (ind.sourceList.isNotEmpty()) {
            Spacer(Modifier.height(2.dp))
            Text(
                ind.sourceList,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
