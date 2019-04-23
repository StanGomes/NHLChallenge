package stan.devhouse.enetchell.view.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import stan.devhouse.enetchell.data.repository.PlayerRepository

class PlayerViewModelFactory(private val playerRepository: PlayerRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayerViewModel::class.java)) {
            return PlayerViewModel(playerRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}