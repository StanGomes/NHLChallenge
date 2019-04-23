package stan.devhouse.enetchell.data.remote

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import stan.devhouse.enetchell.data.remote.response.PlayerResponse
import stan.devhouse.enetchell.data.remote.response.RosterResponse
import stan.devhouse.enetchell.data.remote.response.TeamResponse

interface ApiService {

    //Deferred api calls are thread safe. can be safely invoked from coroutines functions

    @GET("teams")
    fun getAllTeamsAsync(): Deferred<Response<TeamResponse>>

    @GET("teams/{id}/roster")
    fun getRosterAsync(@Path("id") id: Int?): Deferred<Response<RosterResponse>>

    @GET("people/{playerId}")
    fun getPlayerDetailAsync(@Path("playerId") playerId: Long): Deferred<Response<PlayerResponse>>
}