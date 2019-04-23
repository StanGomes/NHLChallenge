package stan.devhouse.enetchell.utils

import android.util.Log
import retrofit2.Response
import java.io.IOException

/**
 * A class to handle api calls and errors. Can only be called from a Coroutine function
 */

open class SafeNetworkCall {

    suspend fun <T: Any> safeApiCall(call: suspend () -> Response<T>, errorMsg : String) : T? {

        val result : Result<T> = safeApiResult(call,errorMsg)
        var data : T? = null

        when(result) {
            is Result.Success -> data = result.data
            is Result.Error -> {
                Log.e("SafeNetworkCall", "$errorMsg , exception - ${result.exception}")
            }
        }
        return data
    }

    private suspend fun <T: Any> safeApiResult(call: suspend () -> Response<T>, errorMsg: String): Result<T> {
        val response = call.invoke()
        if (response.isSuccessful) return Result.Success(response.body()!!)

        return Result.Error(IOException("Error occurred during getting safeApi $errorMsg"))
    }

}