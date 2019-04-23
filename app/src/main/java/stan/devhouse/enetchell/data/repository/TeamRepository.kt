package stan.devhouse.enetchell.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import stan.devhouse.enetchell.data.remote.ApiService
import stan.devhouse.enetchell.data.remote.response.Teams
import stan.devhouse.enetchell.utils.SafeNetworkCall

class TeamRepository(private val retrofitService: ApiService) : SafeNetworkCall() {

    suspend fun loadTeams(): MutableList<Teams>? {
        return withContext(Dispatchers.IO) {
            val response = safeApiCall(
                    call = { retrofitService.getAllTeamsAsync().await() },
                    errorMsg = "Error fetching teams"
            )
            response?.teams?.toMutableList()
        }
    }

}
