package ie.setu.propertyauctionapp.data.api

import ie.setu.propertyauctionapp.data.model.AuctionModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AuctionService {

    @GET(ServiceEndPoints.AUCTIONS_ENDPOINT + "/{email}")
    suspend fun getall(
        @Path("email") email: String)
            : Response<List<AuctionModel>>

    @GET(ServiceEndPoints.AUCTIONS_ENDPOINT + "/{email}" + "/{id}")
    suspend fun get(@Path("email") email: String,
                    @Path("id") id: String): Response<List<AuctionModel>>

    @DELETE(ServiceEndPoints.AUCTIONS_ENDPOINT + "/{email}" + "/{id}")
    suspend fun delete(@Path("email") email: String,
                       @Path("id") id: String): AuctionWrapper

    @POST(ServiceEndPoints.AUCTIONS_ENDPOINT + "/{email}")
    suspend fun post(@Path("email") email: String,
                     @Body auction: AuctionModel): AuctionWrapper

    @PUT(ServiceEndPoints.AUCTIONS_ENDPOINT + "/{email}" + "/{id}")
    suspend fun put(@Path("email") email: String,
                    @Path("id") id: String,
                    @Body auction: AuctionModel
    ): AuctionWrapper
}

