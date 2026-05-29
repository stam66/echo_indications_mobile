package org.heartimaging.echoindications.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.heartimaging.echoindications.model.ApiErrorEnvelope
import org.heartimaging.echoindications.model.Context
import org.heartimaging.echoindications.model.DecisionNode
import org.heartimaging.echoindications.model.Indication
import org.heartimaging.echoindications.model.IndicationUpdate
import org.heartimaging.echoindications.model.Issue
import org.heartimaging.echoindications.model.IssueSubmission
import org.heartimaging.echoindications.model.LoginRequest
import org.heartimaging.echoindications.model.LoginResponse

/**
 * Wraps Ktor with all the API endpoints we use. Construct with the current
 * bearer token (null for anonymous calls). Suspend functions throw [ApiError]
 * on failure — callers wrap in try/catch.
 */
class ApiClient(private val token: String? = null) {

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(jsonFormat)
        }
        defaultRequest {
            url(Config.baseUrl)
            contentType(ContentType.Application.Json)
            if (!token.isNullOrEmpty()) {
                header(HttpHeaders.Authorization, "Bearer $token")
            }
        }
    }

    // MARK: Auth

    suspend fun login(username: String, password: String): LoginResponse {
        val response = client.post("/api/v1/login") {
            setBody(LoginRequest(username, password))
        }
        return decode(response)
    }

    // MARK: Indications

    suspend fun getIndications(): List<Indication> {
        val response = client.get("/api/v1/indications")
        return decode(response)
    }

    suspend fun getIndication(id: Int): Indication {
        val response = client.get("/api/v1/indications/$id")
        return decode(response)
    }

    suspend fun updateIndication(id: Int, update: IndicationUpdate): Indication {
        val response = client.put("/api/v1/indications/$id") {
            setBody(update)
        }
        return decode(response)
    }

    suspend fun deleteIndication(id: Int) {
        val response = client.delete("/api/v1/indications/$id")
        validate(response)
    }

    // MARK: Contexts

    suspend fun getContexts(activeOnly: Boolean = true): List<Context> {
        val path = if (activeOnly) "/api/v1/contexts?active=1" else "/api/v1/contexts"
        val response = client.get(path)
        return decode(response)
    }

    // MARK: Decision nodes

    suspend fun getRootDecisionNodes(): List<DecisionNode> {
        val response = client.get("/api/v1/decision-nodes?roots=1")
        return decode(response)
    }

    suspend fun getChildDecisionNodes(parentId: Int): List<DecisionNode> {
        val response = client.get("/api/v1/decision-nodes?parent=$parentId")
        return decode(response)
    }

    // MARK: Issues

    suspend fun submitIssue(request: String, requestor: String): Issue {
        val response = client.post("/api/v1/issues") {
            // No auth needed; clear the default Authorization header in case it's set
            headers { remove(HttpHeaders.Authorization) }
            setBody(IssueSubmission(request, requestor))
        }
        return decode(response)
    }

    // MARK: Internals

    private suspend inline fun <reified T> decode(response: HttpResponse): T {
        validate(response)
        return response.body()
    }

    private suspend fun validate(response: HttpResponse) {
        if (response.status.isSuccess()) return

        // Try to extract structured error message from our API
        val message = try {
            val envelope: ApiErrorEnvelope = response.body()
            envelope.error
        } catch (_: Throwable) {
            "HTTP ${response.status.value}"
        }

        if (response.status == HttpStatusCode.Unauthorized) {
            throw ApiError.Unauthorized(message)
        }
        throw ApiError.Server(response.status.value, message)
    }

    companion object {
        val jsonFormat: Json = Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }
    }
}

sealed class ApiError(message: String) : Exception(message) {
    class Unauthorized(message: String) : ApiError(message)
    class Server(val statusCode: Int, message: String) : ApiError(message)
    class Network(message: String) : ApiError(message)
}
