package stan.devhouse.enetchell.view.teams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import stan.devhouse.enetchell.data.repository.TeamRepository

class TeamViewModelFactory(private val teamRepository: TeamRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeamViewModel::class.java)) {
            return TeamViewModel(teamRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}