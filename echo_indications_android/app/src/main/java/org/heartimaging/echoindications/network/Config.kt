package org.heartimaging.echoindications.network

import org.heartimaging.echoindications.BuildConfig

/**
 * API base URL is configured via BuildConfig — see app/build.gradle.kts where
 * each build type defines `API_BASE_URL`. Change the debug URL there when
 * iterating against a local server.
 */
object Config {
    val baseUrl: String = BuildConfig.API_BASE_URL
}
