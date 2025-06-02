package com.example.magalumusic

import com.example.magalumusic.data.remote.deezer.ApiService
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java


class ApiTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var api: ApiService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getTopArtists() = runTest {
        val mockJson = """
        {
          "data": [
            {
              "id": 1,
              "name": "Artist One",
              "picture_medium": "https://example.com/artist1.jpg"
            },
            {
              "id": 2,
              "name": "Artist Two",
              "picture_medium": "https://example.com/artist2.jpg"
            }
          ]
        }
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(mockJson).setResponseCode(200))

        val response = api.getTopArtists()

        assertEquals(2, response.data.size)
        assertEquals("Artist One", response.data[0].name)
        assertEquals("https://example.com/artist1.jpg", response.data[0].picture_medium)
        assertEquals("Artist Two", response.data[1].name)
    }
}
