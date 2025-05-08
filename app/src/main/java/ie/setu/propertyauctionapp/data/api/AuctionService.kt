package ie.setu.propertyauctionapp.data.api

import ie.setu.propertyauctionapp.data.AuctionModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AuctionService {
    @GET(ServiceEndPoints.AUCTIONS_ENDPOINT)
    suspend fun getall(): Response<List<AuctionModel>>

    @GET(ServiceEndPoints.AUCTIONS_ENDPOINT + "/{id}")
    suspend fun get(@Path("id") id: String): Response<List<AuctionModel>>

    @DELETE(ServiceEndPoints.AUCTIONS_ENDPOINT + "/{id}")
    suspend fun delete(@Path("id") id: String): AuctionWrapper

    @POST(ServiceEndPoints.AUCTIONS_ENDPOINT)
    suspend fun post(@Body auction: AuctionModel): AuctionWrapper

    @PUT(ServiceEndPoints.AUCTIONS_ENDPOINT + "/{id}")
    suspend fun put(@Path("id") id: String,
                    @Body auction: AuctionModel
    ): AuctionWrapper
}
