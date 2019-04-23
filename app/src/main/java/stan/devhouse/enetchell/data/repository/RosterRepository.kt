package stan.devhouse.enetchell.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import stan.devhouse.enetchell.data.remote.ApiService
import stan.devhouse.enetchell.data.remote.response.Roster
import stan.devhouse.enetchell.utils.SafeNetworkCall

class RosterRepository(private val retrofitService: ApiService?) : SafeNetworkCall() {

    suspend fun loadRoster(teamId: Int?): MutableList<Roster>? {
        return withContext(Dispatchers.IO) {
            val response = safeApiCall(
                    call = { retrofitService?.getRosterAsync(teamId)!!.await() },
                    errorMsg = "Error fetching roster"
            )
            response?.roster?.toMutableList()
        }

    }
}