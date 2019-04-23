package stan.devhouse.enetchell.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import stan.devhouse.enetchell.data.remote.ApiService
import stan.devhouse.enetchell.data.remote.response.People
import stan.devhouse.enetchell.utils.SafeNetworkCall

class PlayerRepository(private val retrofitService: ApiService?) : SafeNetworkCall() {

    suspend fun loadPlayerDetails(playerId: Long): MutableList<People>? {
        return withContext(Dispatchers.IO) {
            val response = safeApiCall(
                    call = { retrofitService?.getPlayerDetailAsync(playerId)!!.await() },
                    errorMsg = "Error fetching player details"
            )
            response?.people?.toMutableList()
        }
    }
}