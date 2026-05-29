package org.heartimaging.echoindications.ui.theme

import androidx.compose.ui.graphics.Color

// NHS-blue palette — matches the iOS BrandColors and the web app.
val NHSBlue = Color(0xFF006AB5)
val NHSBlueDark = Color(0xFF004C82)
val NHSBlueAccent = Color(0xFF5C99CE)

// Tinted background / stroke (with alpha applied at use-site via Color.copy(alpha = …))
val NHSBlueTint = Color(0x14006AB5)     // 8% opacity equivalent
val NHSBlueStroke = Color(0x33006AB5)   // 20% opacity equivalent

// AUC semantic colors — kept consistent across iOS and Android
val AUCIndicated = Color(0xFF34C759)        // green
val AUCNotIndicated = Color(0xFFFF3B30)     // red
val AUCCanBeConsidered = Color(0xFFFF9500)  // orange
