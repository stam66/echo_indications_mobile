package org.heartimaging.echoindications.auth

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted

/**
 * Single source of truth for the admin session. Token + user info persist in
 * EncryptedSharedPreferences (AES-256 GCM, key in the Android Keystore).
 *
 * UI observes [token] / [username] / [isAuthenticated] via StateFlow.
 */
class AuthManager(context: Context) {

    private val prefs = run {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        EncryptedSharedPreferences.create(
            context,
            "echoindications_auth",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private val scope = CoroutineScope(SupervisorJob())

    private val _token = MutableStateFlow(prefs.getString(KEY_TOKEN, null))
    private val _userID = MutableStateFlow(prefs.getInt(KEY_USER_ID, 0).takeIf { it > 0 })
    private val _username = MutableStateFlow(prefs.getString(KEY_USERNAME, null))

    val token: StateFlow<String?> = _token.asStateFlow()
    val userID: StateFlow<Int?> = _userID.asStateFlow()
    val username: StateFlow<String?> = _username.asStateFlow()

    /** True when a non-empty token is stored. Derived from [token]. */
    val isAuthenticated: StateFlow<Boolean> = _token
        .map { !it.isNullOrEmpty() }
        .stateIn(scope, SharingStarted.Eagerly, !_token.value.isNullOrEmpty())

    fun save(token: String, userID: Int, username: String) {
        prefs.edit()
            .putString(KEY_TOKEN, token)
            .putInt(KEY_USER_ID, userID)
            .putString(KEY_USERNAME, username)
            .apply()
        _token.value = token
        _userID.value = userID
        _username.value = username
    }

    fun clear() {
        prefs.edit().clear().apply()
        _token.value = null
        _userID.value = null
        _username.value = null
    }

    companion object {
        private const val KEY_TOKEN = "token"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USERNAME = "username"
    }
}
