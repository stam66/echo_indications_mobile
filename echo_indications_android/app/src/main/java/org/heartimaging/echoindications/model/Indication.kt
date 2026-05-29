package org.heartimaging.echoindications.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Mirrors the server's Indication JSON shape exactly.
 * Nullable enum columns (primaryCare etc.) decode JSON null as Kotlin null.
 */
@Serializable
data class Indication(
    val id: Int,
    val title: String,
    val keywords: String,
    val comments: String,

    @SerialName("primary_care")
    val primaryCare: String? = null,

    @SerialName("secondary_inpatient")
    val secondaryInpatient: String? = null,

    @SerialName("secondary_outpatient")
    val secondaryOutpatient: String? = null,

    val urgency: String? = null,

    @SerialName("source_ase")
    val sourceASE: Boolean,

    @SerialName("source_eacvi")
    val sourceEACVI: Boolean,

    @SerialName("source_bse")
    val sourceBSE: Boolean,

    @SerialName("source_bhvs")
    val sourceBHVS: Boolean,

    @SerialName("source_consensus")
    val sourceConsensus: Boolean,

    val contexts: List<Context> = emptyList()
) {
    /** Human-readable comma-separated list of source attributions. */
    val sourceList: String
        get() = buildList {
            if (sourceASE) add("ASE")
            if (sourceEACVI) add("EACVI")
            if (sourceBSE) add("BSE")
            if (sourceBHVS) add("BHVS")
            if (sourceConsensus) add("Consensus")
        }.joinToString(", ")
}

/**
 * Payload for POST /api/v1/indications and PUT /api/v1/indications/:id.
 * Empty string for nullable enum fields means "clear" — server's bind logic stores SQL NULL.
 */
@Serializable
data class IndicationUpdate(
    val title: String,
    val keywords: String,
    val comments: String,

    @SerialName("primary_care")
    val primaryCare: String,

    @SerialName("secondary_inpatient")
    val secondaryInpatient: String,

    @SerialName("secondary_outpatient")
    val secondaryOutpatient: String,

    val urgency: String,

    @SerialName("source_ase")
    val sourceASE: Boolean,

    @SerialName("source_eacvi")
    val sourceEACVI: Boolean,

    @SerialName("source_bse")
    val sourceBSE: Boolean,

    @SerialName("source_bhvs")
    val sourceBHVS: Boolean,

    @SerialName("source_consensus")
    val sourceConsensus: Boolean,

    val contexts: List<ContextRef>
) {
    @Serializable
    data class ContextRef(val id: Int)

    companion object {
        fun from(ind: Indication): IndicationUpdate = IndicationUpdate(
            title = ind.title,
            keywords = ind.keywords,
            comments = ind.comments,
            primaryCare = ind.primaryCare ?: "",
            secondaryInpatient = ind.secondaryInpatient ?: "",
            secondaryOutpatient = ind.secondaryOutpatient ?: "",
            urgency = ind.urgency ?: "",
            sourceASE = ind.sourceASE,
            sourceEACVI = ind.sourceEACVI,
            sourceBSE = ind.sourceBSE,
            sourceBHVS = ind.sourceBHVS,
            sourceConsensus = ind.sourceConsensus,
            contexts = ind.contexts.map { ContextRef(it.id) }
        )
    }
}
