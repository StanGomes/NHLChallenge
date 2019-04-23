package stan.devhouse.enetchell.view.roster

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import stan.devhouse.enetchell.data.repository.RosterRepository


class RosterViewModelFactory(private val rosterRepository: RosterRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RosterViewModel::class.java)) {
            return RosterViewModel(rosterRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}