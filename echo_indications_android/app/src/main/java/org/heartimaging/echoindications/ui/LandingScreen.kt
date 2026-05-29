package org.heartimaging.echoindications.ui

import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.ListAlt
import androidx.compose.material.icons.outlined.MedicalServices
import androidx.compose.material.icons.outlined.Sms
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import org.heartimaging.echoindications.auth.AuthManager
import org.heartimaging.echoindications.nav.Routes
import org.heartimaging.echoindications.ui.theme.NHSBlue
import org.heartimaging.echoindications.ui.theme.NHSBlueStroke
import org.heartimaging.echoindications.ui.theme.NHSBlueTint

@Composable
fun LandingScreen(auth: AuthManager, nav: NavHostController) {
    var showAbout by remember { mutableStateOf(false) }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
            Spacer(Modifier.height(60.dp))

            // Header
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "ECHOindications",
                    color = NHSBlue,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Appropriate Use Criteria for Echocardiography",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 14.sp
                )
            }

            Spacer(Modifier.height(28.dp))

            // Tiles
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                LandingTile(
                    icon = Icons.Outlined.ListAlt,
                    title = "Indications",
                    subtitle = "Search or filter by context"
                ) { nav.navigate(Routes.INDICATIONS) }

                LandingTile(
                    icon = Icons.Outlined.MedicalServices,
                    title = "Decision Support",
                    subtitle = "Guided indication lookup"
                ) { nav.navigate(Routes.CDS_ROOT) }

                LandingTile(
                    icon = Icons.Outlined.Sms,
                    title = "Submit Feedback",
                    subtitle = "Suggest a change or new indication"
                ) { nav.navigate(Routes.FEEDBACK) }
            }

            Spacer(Modifier.weight(1f))

            // Footer
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = { showAbout = true }) {
                    Icon(Icons.Filled.Info, contentDescription = null)
                    Spacer(Modifier.width(6.dp))
                    Text("About")
                }
                Spacer(Modifier.weight(1f))
            }
        }
    }

    if (showAbout) {
        AboutSheet(
            auth = auth,
            onDismiss = { showAbout = false },
            onLoginClick = {
                showAbout = false
                nav.navigate(Routes.LOGIN)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AboutSheet(
    auth: AuthManager,
    onDismiss: () -> Unit,
    onLoginClick: () -> Unit
) {
    val isAuthenticated by auth.isAuthenticated.collectAsState()
    val username by auth.username.collectAsState()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    fun dismiss() {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) onDismiss()
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Scrollable body
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = false)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "About",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 13.sp
                )
                Text(
                    text = "ECHOindications",
                    color = NHSBlue,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Text("A free public reference for Appropriate Use Criteria (AUC) for echocardiography.")

                Text("Designed for clinicians, trainees, sonographers, and anyone requesting or interpreting echocardiograms. No account is required to use any feature — browsing, search, decision support, and feedback are open to everyone.")

                SectionHeader("Sources")
                Text("Indications are derived from publicly available guidance from the British Society of Echocardiography (BSE), the British Heart Valve Society (BHVS), the European Association of Cardiovascular Imaging (EACVI), and the American Society of Echocardiography (ASE), supplemented by expert consensus.")

                SectionHeader("Web")
                Text("echoindications.org")

                SectionHeader("Maintainers")
                Text("Content is curated by a small volunteer editorial team. The login below is for those maintainers only — end users do not need to sign in.")
            }

            // Sticky maintainer section
            HorizontalDivider()
            if (isAuthenticated) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Signed in as ${username ?: "maintainer"}",
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Maintainer mode",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    TextButton(onClick = {
                        auth.clear()
                        dismiss()
                    }) {
                        Text("Sign out", color = MaterialTheme.colorScheme.error)
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp)
                ) {
                    OutlinedButton(
                        onClick = onLoginClick,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Filled.Key, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Content maintainer login")
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        modifier = Modifier.padding(top = 4.dp)
    )
}

@Composable
private fun LandingTile(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable(onClick = onClick),
        color = NHSBlueTint
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(0.5.dp, NHSBlueStroke, RoundedCornerShape(14.dp))
                .padding(14.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = NHSBlue,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(Modifier.width(14.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = subtitle,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = null,
                    tint = NHSBlue.copy(alpha = 0.5f)
                )
            }
        }
    }
}
