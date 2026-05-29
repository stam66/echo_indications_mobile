package org.heartimaging.echoindications.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Used both for the full /api/v1/contexts response (all fields) and as nested
 * entries on Indication.contexts (only id + name populated). Optional fields
 * allow the same class to decode both shapes.
 */
@Serializable
data class Context(
    val id: Int,
    val name: String,
    val description: String? = null,

    @SerialName("sort_order")
    val sortOrder: Int? = null,

    @SerialName("is_active")
    val isActive: Boolean? = null
)
