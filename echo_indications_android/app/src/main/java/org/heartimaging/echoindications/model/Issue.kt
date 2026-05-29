package org.heartimaging.echoindications.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Issue(
    val id: Int,
    val request: String,
    val requestor: String,
    val status: String,

    @SerialName("existing_contexts")
    val existingContexts: String? = null,

    @SerialName("new_contexts")
    val newContexts: String? = null,

    @SerialName("existing_indication")
    val existingIndication: String? = null,

    @SerialName("new_indication")
    val newIndication: String? = null,

    @SerialName("indication_id")
    val indicationID: Int? = null,

    @SerialName("reason_for_close")
    val reasonForClose: String? = null,

    @SerialName("resolution_notes")
    val resolutionNotes: String? = null,

    @SerialName("created_at")
    val createdAt: String? = null
)

/** Payload for POST /api/v1/issues (anonymous submission). */
@Serializable
data class IssueSubmission(
    val request: String,
    val requestor: String
)
