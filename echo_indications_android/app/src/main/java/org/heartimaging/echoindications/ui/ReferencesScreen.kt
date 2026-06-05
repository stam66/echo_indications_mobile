package org.heartimaging.echoindications.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.heartimaging.echoindications.model.Reference
import org.heartimaging.echoindications.model.References
import org.heartimaging.echoindications.ui.theme.NHSBlue

/**
 * Bibliography of source documents used to construct the indication dataset.
 * Each citation is a tappable link to the source (DOI or organisational page).
 * Ported from the iOS ReferencesView to keep the two apps at parity.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReferencesScreen(nav: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("References") },
                navigationIcon = {
                    IconButton(onClick = { nav.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Text(
                    "ECHOindications is built on Appropriate Use Criteria (AUC) and triage guidance " +
                        "published by international professional bodies. The references below are the " +
                        "primary sources used to construct the indication dataset. Tap a citation to " +
                        "open the source.",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            References.bodies.forEach { body ->
                item {
                    SectionLabel(body.name)
                }
                items(References.references(forBodyKey = body.key), key = { it.id }) { ref ->
                    ReferenceRow(ref)
                }
            }

            item { SectionLabel("Expert consensus") }
            item {
                Text(
                    "Where published AUC do not yet cover a clinical scenario, recommendations are " +
                        "derived from expert clinical consensus. These indications are tagged " +
                        "\"Consensus\" in the source list.",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            item {
                Text(
                    "This app is a reference resource for clinicians, trainees, and other users " +
                        "requesting or interpreting echocardiograms. It is not a diagnostic device and " +
                        "is not a substitute for individual clinical judgement.",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(
        text,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        color = NHSBlue,
        modifier = Modifier.padding(top = 12.dp)
    )
}

@Composable
private fun ReferenceRow(reference: Reference) {
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .clickable { uriHandler.openUri(reference.url) }
            .padding(vertical = 6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            reference.citation,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Filled.Link,
                contentDescription = null,
                tint = NHSBlue,
                modifier = Modifier.size(14.dp)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                reference.url,
                fontSize = 12.sp,
                color = NHSBlue,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
