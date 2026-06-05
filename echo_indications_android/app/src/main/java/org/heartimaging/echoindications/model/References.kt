package org.heartimaging.echoindications.model

/**
 * Static bibliography of source documents underpinning the indication
 * dataset. Surfaced in ReferencesScreen and from IndicationDetailScreen so
 * users can verify the source of each recommendation.
 *
 * Ported from the iOS References.swift to keep the two apps at parity.
 */
data class ReferenceBody(
    val key: String,      // matches Indication.source* flags ("ase", "eacvi", "bse", "bhvs")
    val acronym: String,  // "ASE"
    val name: String      // "American Society of Echocardiography"
)

data class Reference(
    val id: String,       // stable per-citation key
    val bodyKey: String,  // links back to ReferenceBody.key
    val citation: String, // full human-readable citation
    val url: String
)

object References {
    /** Source bodies in display order. */
    val bodies: List<ReferenceBody> = listOf(
        ReferenceBody("ase", "ASE", "American Society of Echocardiography"),
        ReferenceBody("eacvi", "EACVI", "European Association of Cardiovascular Imaging"),
        ReferenceBody("bse", "BSE", "British Society of Echocardiography"),
        ReferenceBody("bhvs", "BHVS", "British Heart Valve Society")
    )

    /** Full bibliography. Order within a body is roughly chronological. */
    val all: List<Reference> = listOf(
        // ASE
        Reference(
            id = "ase-2011",
            bodyKey = "ase",
            citation = "Douglas PS et al. ACCF/ASE/AHA/ASNC/HFSA/HRS/SCAI/SCCM/SCCT/SCMR 2011 Appropriate Use Criteria for Echocardiography. J Am Soc Echocardiogr. 2011;24(3):229–267.",
            url = "https://doi.org/10.1016/j.echo.2010.12.008"
        ),
        Reference(
            id = "ase-2017-vhd",
            bodyKey = "ase",
            citation = "Doherty JU et al. ACC/AATS/AHA/ASE/ASNC/HRS/SCAI/SCCT/SCMR/STS 2017 Appropriate Use Criteria for Multimodality Imaging in Valvular Heart Disease. J Am Soc Echocardiogr. 2017;30(11):1029–1056.",
            url = "https://doi.org/10.1016/j.echo.2017.08.012"
        ),
        Reference(
            id = "ase-2019-nvhd",
            bodyKey = "ase",
            citation = "Doherty JU et al. ACC/AATS/AHA/ASE/ASNC/HRS/SCAI/SCCT/SCMR/STS 2019 Appropriate Use Criteria for Multimodality Imaging in the Assessment of Cardiac Structure and Function in Nonvalvular Heart Disease. J Am Soc Echocardiogr. 2019;32(5):553–579.",
            url = "https://doi.org/10.1016/j.echo.2019.01.008"
        ),

        // EACVI
        Reference(
            id = "eacvi-2017",
            bodyKey = "eacvi",
            citation = "Steeds RP, Garbi M, et al. EACVI appropriateness criteria for the use of transthoracic echocardiography in adults: a report of literature and current practice review. Eur Heart J Cardiovasc Imaging. 2017;18(11):1191–1204.",
            url = "https://doi.org/10.1093/ehjci/jew333"
        ),

        // BSE (PUE triage documents)
        Reference(
            id = "bse-pue014",
            bodyKey = "bse",
            citation = "British Society of Echocardiography. PUE014 — Clinical indications and triage of echocardiography in primary care (2025).",
            url = "https://www.bsecho.org/Public/News/Articles/2025/2025-03/202503-PUE014.aspx?WebsiteKey=cbc9ffd7-4ee6-4741-9280-d435d6a887f4"
        ),
        Reference(
            id = "bse-pue005",
            bodyKey = "bse",
            citation = "British Society of Echocardiography. PUE005 — Clinical indications and triage of echocardiography: Outpatients (2024).",
            url = "https://www.bsecho.org/Public/News/Articles/2024/2024-07/202407-PUE005.aspx"
        ),
        Reference(
            id = "bse-pue006",
            bodyKey = "bse",
            citation = "British Society of Echocardiography. PUE006 — Clinical indications and triage of echocardiography: Emergency inpatient and critical care (2024).",
            url = "https://www.bsecho.org/Public/News/Articles/2024/2024-07/202407-PUE006.aspx"
        ),
        Reference(
            id = "bse-pue004",
            bodyKey = "bse",
            citation = "British Society of Echocardiography & British Heart Valve Society. PUE004 — Clinical indications and triage of echocardiography: Heart valve disease (2024).",
            url = "https://www.bsecho.org/Public/News/Articles/2024/2024-07/202407-PUE004.aspx"
        ),

        // BHVS
        Reference(
            id = "bhvs-triage",
            bodyKey = "bhvs",
            citation = "British Heart Valve Society. Updated triage guidance for echocardiography in valvular heart disease.",
            url = "https://bhvs.org/newsitem/updated-triage-guidance/"
        )
    )

    /** Citations for a given source body, in display order. */
    fun references(forBodyKey: String): List<Reference> = all.filter { it.bodyKey == forBodyKey }

    /** Look up a body by its key (matches the source_* flags on Indication). */
    fun body(forKey: String): ReferenceBody? = bodies.firstOrNull { it.key == forKey }
}
