package stan.devhouse.enetchell.utils

/**
 * Class to handle network response. It either can be Success with the required data
 * or Error with an exception
 */
sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}