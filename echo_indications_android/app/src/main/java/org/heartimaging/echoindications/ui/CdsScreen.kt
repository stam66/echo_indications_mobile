package org.heartimaging.echoindications.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.heartimaging.echoindications.auth.AuthManager
import org.heartimaging.echoindications.model.DecisionNode
import org.heartimaging.echoindications.nav.Routes
import org.heartimaging.echoindications.network.ApiClient
import org.heartimaging.echoindications.ui.theme.AUCIndicated
import org.heartimaging.echoindications.ui.theme.NHSBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CdsScreen(parentId: Int?, auth: AuthManager, nav: NavHostController) {
    val token by auth.token.collectAsState()

    var nodes by remember { mutableStateOf<List<DecisionNode>>(emptyList()) }
    var parentNode by remember { mutableStateOf<DecisionNode?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var loadError by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(parentId, token) {
        isLoading = true
        loadError = null
        try {
            val api = ApiClient(token)
            nodes = if (parentId == null) {
                api.getRootDecisionNodes()
            } else {
                // Fetch the parent itself so we can show its prompt at the top.
                // Simplest path: refetch children; parent prompt comes via nav arg
                // expansion when we get there. For now, just children.
                api.getChildDecisionNodes(parentId)
            }
        } catch (e: Throwable) {
            loadError = e.message ?: "Could not load"
        }
        isLoading = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (parentId == null) "Decision Support" else "Choose option") },
                navigationIcon = {
                    IconButton(onClick = { nav.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            when {
                isLoading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = NHSBlue)
                }
                loadError != null -> Text(
                    loadError ?: "",
                    modifier = Modifier.padding(32.dp),
                    color = MaterialTheme.colorScheme.error
                )
                nodes.isEmpty() -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Dead end — no further options.")
                }
                else -> LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 4.dp)
                ) {
                    if (parentId == null) {
                        item {
                            Text(
                                "What is the presenting complaint?",
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                            HorizontalDivider()
                        }
                    }
                    items(nodes, key = { it.id }) { node ->
                        NodeRow(node) {
                            if (node.isTerminal && node.indicationID != null) {
                                nav.navigate(Routes.indicationDetail(node.indicationID))
                            } else {
                                nav.navigate(Routes.cdsChild(node.id))
                            }
                        }
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
private fun NodeRow(node: DecisionNode, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = if (node.isTerminal) Icons.Filled.Description else Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            tint = if (node.isTerminal) AUCIndicated else NHSBlue,
            modifier = Modifier.size(20.dp).padding(top = 2.dp)
        )
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(node.optionLabel)
            if (!node.rationale.isNullOrEmpty()) {
                Spacer(Modifier.height(2.dp))
                Text(
                    node.rationale,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
