package org.heartimaging.echoindications.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DecisionNode(
    val id: Int,

    @SerialName("parent_id")
    val parentID: Int? = null,

    @SerialName("option_label")
    val optionLabel: String,

    val prompt: String? = null,

    @SerialName("sort_order")
    val sortOrder: Int? = null,

    val rationale: String? = null,

    @SerialName("indication_id")
    val indicationID: Int? = null,

    @SerialName("is_terminal")
    val isTerminal: Boolean
)
