package stan.devhouse.enetchell.view.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import stan.devhouse.enetchell.data.remote.response.People
import stan.devhouse.enetchell.data.repository.PlayerRepository

class PlayerViewModel(private val playerRepository: PlayerRepository) : ViewModel() {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    //Encapsulation used to make value immutable from outside
    private val _playerLiveData = MutableLiveData<MutableList<People>>()
    val playerLiveData: LiveData<MutableList<People>> = _playerLiveData

    fun getPlayerDetails(playerId: Long) {
        scope.launch {
            val player = playerRepository.loadPlayerDetails(playerId)
            _playerLiveData.postValue(player)
        }
    }

}
