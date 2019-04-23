package stan.devhouse.enetchell.view.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import stan.devhouse.enetchell.data.remote.response.Teams
import stan.devhouse.enetchell.data.repository.TeamRepository

class TeamViewModel(teamRepository: TeamRepository) : ViewModel() {

    private val repo = teamRepository
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    init {
        getTeams()
    }

    //Encapsulation used to make value immutable from outside
    private var _teamsLiveData = MutableLiveData<MutableList<Teams>>()
    val teamsLiveData: LiveData<MutableList<Teams>> = _teamsLiveData

    private fun getTeams() {
        scope.launch {
            val teams = repo.loadTeams()
            _teamsLiveData.postValue(teams)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
