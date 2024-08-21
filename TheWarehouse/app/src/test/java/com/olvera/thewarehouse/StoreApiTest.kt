package com.olvera.thewarehouse

import com.google.gson.Gson
import com.olvera.thewarehouse.data.remote.StoreApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StoreApiTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var storeApi: StoreApi

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

        storeApi = retrofit.create(StoreApi::class.java)

    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getAllProducts returns a list of products`() = runBlocking {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("""{"products": [{"id": "1", "title": "Essence Mascara Lash Princess"}]}""")
        mockWebServer.enqueue(mockResponse)

        val response = storeApi.getAllProducts()

        assertEquals(true, response.isSuccessful)
        assertEquals(30, response.body()?.products?.size)
        assertEquals("Essence Mascara Lash Princess", response.body()?.products?.get(0)?.title)
    }

    @Test
    fun `getAllCategories returns a list of categories`() = runBlocking {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("""[{"id": "1", "name": "Beauty"}]""")
        mockWebServer.enqueue(mockResponse)

        val response = storeApi.getAllCategories()

        assertEquals(true, response.isSuccessful)
        assertEquals(24, response.body()?.size)
        assertEquals("Beauty", response.body()?.get(0)?.name)

    }

    @Test
    fun `getProductsByCategory returns products for a specific category`() = runBlocking {

        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("""{"products": [{"id": "1", "title": "Essence Mascara Lash Princess"}]}""")

        mockWebServer.enqueue(mockResponse)

        val response = storeApi.getProductsByCategory("beauty")

        assertEquals(true, response.isSuccessful)
        assertEquals(1, response.body()?.products?.size)
        assertEquals("Essence Mascara Lash Princess", response.body()?.products?.get(0)?.title)
    }

    @Test
    fun `getProductsByCategory returns null for an invalid category`() = runBlocking {
        val mockResponse = MockResponse()
            .setResponseCode(404)
            .setBody("Category not found")

        mockWebServer.enqueue(mockResponse)

        val response = storeApi.getProductsByCategory("invalid")

        assertEquals(null, response.body())
    }







}