package com.thomas.data.repository

import com.thomas.data.RepositoriesStubs
import com.thomas.data.UserDetailsStubs
import com.thomas.data.UsersStubs
import com.thomas.data.model.ErrorResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.net.HttpURLConnection
import kotlin.test.assertFailsWith
import kotlin.time.ExperimentalTime

@ExperimentalTime
internal class GithubAPIDataSourceTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var githubServiceAPI: GithubServiceAPI
    private lateinit var githubAPIDataSource: GithubAPIDataSource

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        githubServiceAPI = mockk()
        githubAPIDataSource = GithubAPIDataSource(githubServiceAPI)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `When getUsers is called Should returns correct UserResponse`() = runTest {
        // Given
        mockWebServer.enqueue(getMockedResponse(USER_LIST_SUCCESS_RESPONSE))
        val expectedResponse = UsersStubs.listUsersResponse
        coEvery { githubServiceAPI.getUsers() } returns expectedResponse

        // Then
        val result = githubAPIDataSource.getUsers()

        // When
        assertEquals(expectedResponse, result.first())
        assertEquals(2, result.first().size)
    }

    @Test
    fun `When getUsers is called Should return a HttpException`() = runBlocking {
        // Given
        mockWebServer.enqueue(getMockedErrorResponse())
        coEvery { githubServiceAPI.getUsers() } throws getHttpException()

        // When
        val exception = assertFailsWith<ErrorResponse> {
            githubAPIDataSource.getUsers().first()
        }

        // Then
        assertEquals("Not Found", exception.message)
        assertEquals("https://developer.github.com/v3", exception.documentationUrl)
    }

    @Test
    fun `When getUserDetails is called Should returns correct UserDetailsResponse`() = runTest {
        // Given
        val username = "username"
        mockWebServer.enqueue(getMockedResponse(USER_DETAILS_SUCCESS_RESPONSE))
        val expectedResponse = UserDetailsStubs.response
        coEvery { githubServiceAPI.getUserDetails(username) } returns expectedResponse

        // When
        val result = githubAPIDataSource.getUserDetails(username)

        // Then
        assertEquals(expectedResponse, result.first())
    }

    @Test
    fun `When getUserDetails is called Should return a HttpException`() = runBlocking {
        // Given
        val username = "username"
        mockWebServer.enqueue(getMockedErrorResponse())
        coEvery { githubServiceAPI.getUserDetails(username) } throws getHttpException()

        // When
        val exception = assertFailsWith<ErrorResponse> {
            githubAPIDataSource.getUserDetails(username).first()
        }

        // Then
        assertEquals("Not Found", exception.message)
        assertEquals("https://developer.github.com/v3", exception.documentationUrl)
    }

    @Test
    fun `When getRepositories is called Should returns correct UserDetailsResponse`() = runTest {
        // Given
        val username = "username"
        mockWebServer.enqueue(getMockedResponse(REPOSITORIES_SUCCESS_RESPONSE))
        val expectedResponse = RepositoriesStubs.listResponse
        coEvery { githubServiceAPI.getRepositories(username) } returns expectedResponse

        // When
        val result = githubAPIDataSource.getRepositories(username)

        // Then
        assertEquals(expectedResponse, result.first())
        assertEquals(2, result.first().size)
    }

    @Test
    fun `When getRepositories is called Should return HttpException`() = runBlocking {
        // Given
        val username = "username"
        mockWebServer.enqueue(getMockedErrorResponse())
        coEvery { githubServiceAPI.getRepositories(username) } throws getHttpException()

        // When
        val exception = assertFailsWith<ErrorResponse> {
            githubAPIDataSource.getRepositories(username).first()
        }

        // Then
        assertEquals("Not Found", exception.message)
        assertEquals("https://developer.github.com/v3", exception.documentationUrl)
    }

    private fun getMockedResponse(responseFilePath: String): MockResponse {
        val inputStream = javaClass.classLoader?.getResourceAsStream(responseFilePath)
        val jsonResponse = inputStream?.bufferedReader()?.use { it.readText() }.orEmpty()
        return MockResponse()
            .setResponseCode(200)
            .setBody(jsonResponse)
    }

    private fun getMockedErrorResponse(): MockResponse {
        return MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
            .setBody(ERROR_MESSAGE)
    }

    private fun getHttpException(): HttpException {
        val errorResponseBody = ResponseBody.create("application/json".toMediaTypeOrNull(), ERROR_MESSAGE)
        return HttpException(Response.error<Any>(
            HttpURLConnection.HTTP_NOT_FOUND,
            errorResponseBody
        ))
    }

    companion object {
        private const val USER_LIST_SUCCESS_RESPONSE = "com/thomas/data/repository/user_list_success_response.json"
        private const val USER_DETAILS_SUCCESS_RESPONSE = "com/thomas/data/repository/user_list_success_response.json"
        private const val REPOSITORIES_SUCCESS_RESPONSE = "com/thomas/data/repository/repositories_success_response.json"
        private const val ERROR_MESSAGE = "{\"message\": \"Not Found\", \"documentation_url\": \"https://developer.github.com/v3\"}"
    }
}