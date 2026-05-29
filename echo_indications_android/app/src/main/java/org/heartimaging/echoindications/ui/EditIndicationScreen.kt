package org.heartimaging.echoindications.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import org.heartimaging.echoindications.auth.AuthManager
import org.heartimaging.echoindications.model.Context
import org.heartimaging.echoindications.model.Indication
import org.heartimaging.echoindications.model.IndicationUpdate
import org.heartimaging.echoindications.network.ApiClient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditIndicationScreen(
    indicationId: Int,
    auth: AuthManager,
    nav: NavHostController
) {
    val token by auth.token.collectAsState()
    val scope = rememberCoroutineScope()

    var indication by remember { mutableStateOf<Indication?>(null) }
    var availableContexts by remember { mutableStateOf<List<Context>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var loadError by remember { mutableStateOf<String?>(null) }
    var isSubmitting by remember { mutableStateOf(false) }
    var submitError by remember { mutableStateOf<String?>(null) }

    // Form state — initialised once the indication loads
    var title by remember { mutableStateOf("") }
    var keywords by remember { mutableStateOf("") }
    var comments by remember { mutableStateOf("") }
    var primaryCare by remember { mutableStateOf<String?>(null) }
    var secondaryInpatient by remember { mutableStateOf<String?>(null) }
    var secondaryOutpatient by remember { mutableStateOf<String?>(null) }
    var urgency by remember { mutableStateOf<String?>(null) }
    var sourceASE by remember { mutableStateOf(false) }
    var sourceEACVI by remember { mutableStateOf(false) }
    var sourceBSE by remember { mutableStateOf(false) }
    var sourceBHVS by remember { mutableStateOf(false) }
    var sourceConsensus by remember { mutableStateOf(false) }
    val selectedContextIds = remember { mutableStateMapOf<Int, Boolean>() }

    LaunchedEffect(indicationId, token) {
        isLoading = true
        loadError = null
        try {
            val api = ApiClient(token)
            val ind = api.getIndication(indicationId)
            indication = ind
            title = ind.title
            keywords = ind.keywords
            comments = ind.comments
            primaryCare = ind.primaryCare
            secondaryInpatient = ind.secondaryInpatient
            secondaryOutpatient = ind.secondaryOutpatient
            urgency = ind.urgency
            sourceASE = ind.sourceASE
            sourceEACVI = ind.sourceEACVI
            sourceBSE = ind.sourceBSE
            sourceBHVS = ind.sourceBHVS
            sourceConsensus = ind.sourceConsensus
            selectedContextIds.clear()
            ind.contexts.forEach { selectedContextIds[it.id] = true }

            availableContexts = api.getContexts(activeOnly = false)
                .sortedBy { it.sortOrder ?: 0 }
        } catch (e: Throwable) {
            loadError = e.message ?: "Could not load"
        }
        isLoading = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit indication") },
                navigationIcon = {
                    IconButton(
                        onClick = { nav.popBackStack() },
                        enabled = !isSubmitting
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Cancel")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            val ind = indication ?: return@IconButton
                            scope.launch {
                                isSubmitting = true
                                submitError = null
                                val update = IndicationUpdate(
                                    title = title.trim(),
                                    keywords = keywords,
                                    comments = comments,
                                    primaryCare = primaryCare ?: "",
                                    secondaryInpatient = secondaryInpatient ?: "",
                                    secondaryOutpatient = secondaryOutpatient ?: "",
                                    urgency = urgency ?: "",
                                    sourceASE = sourceASE,
                                    sourceEACVI = sourceEACVI,
                                    sourceBSE = sourceBSE,
                                    sourceBHVS = sourceBHVS,
                                    sourceConsensus = sourceConsensus,
                                    contexts = selectedContextIds.filter { it.value }
                                        .keys
                                        .map { IndicationUpdate.ContextRef(it) }
                                )
                                try {
                                    ApiClient(token).updateIndication(ind.id, update)
                                    nav.previousBackStackEntry
                                        ?.savedStateHandle
                                        ?.set("indicationUpdated", true)
                                    nav.popBackStack()
                                } catch (e: Throwable) {
                                    submitError = e.message ?: "Could not save"
                                }
                                isSubmitting = false
                            }
                        },
                        enabled = title.isNotBlank() && !isSubmitting && indication != null
                    ) {
                        if (isSubmitting) {
                            CircularProgressIndicator(modifier = Modifier.padding(4.dp))
                        } else {
                            Icon(Icons.Filled.Check, contentDescription = "Save")
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        when {
            isLoading -> {
                Box(
                    modifier = Modifier.padding(innerPadding).fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) { CircularProgressIndicator() }
            }
            loadError != null -> {
                Text(
                    loadError ?: "",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(innerPadding).padding(32.dp)
                )
            }
            else -> {
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Title") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = keywords,
                        onValueChange = { keywords = it },
                        label = { Text("Keywords (comma-separated)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = comments,
                        onValueChange = { comments = it },
                        label = { Text("Comments") },
                        minLines = 3,
                        modifier = Modifier.fillMaxWidth()
                    )

                    SectionLabel("Appropriate Use Criteria")
                    EnumPicker("Primary care", primaryCare, AUC_OPTIONS) { primaryCare = it }
                    EnumPicker("Secondary inpatient", secondaryInpatient, AUC_OPTIONS) { secondaryInpatient = it }
                    EnumPicker("Secondary outpatient", secondaryOutpatient, AUC_OPTIONS) { secondaryOutpatient = it }

                    SectionLabel("Urgency")
                    EnumPicker("Urgency", urgency, URGENCY_OPTIONS) { urgency = it }

                    SectionLabel("Sources")
                    SourceToggle("ASE", sourceASE) { sourceASE = it }
                    SourceToggle("EACVI", sourceEACVI) { sourceEACVI = it }
                    SourceToggle("BSE", sourceBSE) { sourceBSE = it }
                    SourceToggle("BHVS", sourceBHVS) { sourceBHVS = it }
                    SourceToggle("Consensus", sourceConsensus) { sourceConsensus = it }

                    if (availableContexts.isNotEmpty()) {
                        SectionLabel("Contexts")
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                availableContexts.forEach { ctx ->
                                    SourceToggle(
                                        label = ctx.name,
                                        checked = selectedContextIds[ctx.id] == true
                                    ) { selectedContextIds[ctx.id] = it }
                                }
                            }
                        }
                    }

                    submitError?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                    Spacer(Modifier.height(24.dp))
                }
            }
        }
    }
}

private val AUC_OPTIONS: List<Pair<String?, String>> = listOf(
    null to "Not set",
    "indicated" to "Indicated",
    "not_indicated" to "Not indicated",
    "can_be_considered" to "Can be considered"
)

private val URGENCY_OPTIONS: List<Pair<String?, String>> = listOf(
    null to "Not set",
    "urgent" to "Urgent",
    "soon" to "Soon",
    "routine" to "Routine",
    "can_be_considered" to "Can be considered",
    "not_indicated" to "Not indicated"
)

@Composable
private fun SectionLabel(text: String) {
    Spacer(Modifier.height(8.dp))
    Text(
        text.uppercase(),
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

/**
 * Field-styled dropdown picker. Tapping the row opens a DropdownMenu;
 * picking an option calls [onChange] with the raw value (or null for "Not set").
 */
@Composable
private fun EnumPicker(
    label: String,
    value: String?,
    options: List<Pair<String?, String>>,
    onChange: (String?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val displayValue = options.firstOrNull { it.first == value }?.second ?: "Not set"

    Box(modifier = Modifier.fillMaxWidth()) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            color = MaterialTheme.colorScheme.surface
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        label,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(displayValue)
                }
                Icon(
                    Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { (raw, optionLabel) ->
                DropdownMenuItem(
                    text = { Text(optionLabel) },
                    onClick = {
                        onChange(raw)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
private fun SourceToggle(label: String, checked: Boolean, onChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, modifier = Modifier.weight(1f))
        Switch(checked = checked, onCheckedChange = onChange)
    }
}
