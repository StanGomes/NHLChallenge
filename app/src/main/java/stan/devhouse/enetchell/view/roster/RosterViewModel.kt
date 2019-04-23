package stan.devhouse.enetchell.view.roster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import stan.devhouse.enetchell.data.remote.response.Roster
import stan.devhouse.enetchell.data.repository.RosterRepository
import stan.devhouse.enetchell.utils.PlayerPosition

class RosterViewModel(private val rosterRepository: RosterRepository) : ViewModel() {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    //Encapsulation used to make value immutable from outside
    private val _rosterLiveData = MutableLiveData<MutableList<Roster>>()
    val rosterLiveData: LiveData<MutableList<Roster>> = _rosterLiveData

    private val _sortNameEvent = MutableLiveData<Boolean>()
    val sortEvent: LiveData<Boolean>
        get() = _sortNameEvent

    private val _sortNumberEvent = MutableLiveData<Boolean>()
    val sortNumberEvent: LiveData<Boolean>
        get() = _sortNumberEvent

    private val _filterRWLiveData = MutableLiveData<Boolean>()
    val filterRWLiveData: LiveData<Boolean>
        get() = _filterRWLiveData

    private val _filterLWLiveData = MutableLiveData<Boolean>()
    val filterLWLiveData: LiveData<Boolean>
        get() = _filterLWLiveData

    private val _filterCLiveData = MutableLiveData<Boolean>()
    val filterCLiveData: LiveData<Boolean>
        get() = _filterCLiveData

    private val _filterGLiveData = MutableLiveData<Boolean>()
    val filterGLiveData: LiveData<Boolean>
        get() = _filterGLiveData

    private val _filterDLiveData = MutableLiveData<Boolean>()
    val filterDLiveData: LiveData<Boolean>
        get() = _filterDLiveData


    fun getRoster(teamId: Int?) {
        scope.launch {
            val teams = rosterRepository.loadRoster(teamId)
            _rosterLiveData.postValue(teams)
        }
    }

    fun filterByPosition(position: PlayerPosition) {
        when (position) {
            PlayerPosition.RW -> _filterRWLiveData.value = true
            PlayerPosition.LW -> _filterLWLiveData.value = true
            PlayerPosition.C -> _filterCLiveData.value = true
            PlayerPosition.D -> _filterDLiveData.value = true
            PlayerPosition.G -> _filterGLiveData.value = true
        }
    }

    fun onFilteredByC() {
        _filterCLiveData.value = false
    }

    fun onFilteredByD() {
        _filterDLiveData.value = false
    }

    fun onFilteredByG() {
        _filterGLiveData.value = false
    }

    fun onFilteredByRW() {
        _filterRWLiveData.value = false
    }

    fun onFilteredByLW() {
        _filterLWLiveData.value = false
    }

    fun onSortByNameClicked() {
        _sortNameEvent.value = true
    }

    fun onSortedByName() {
        _sortNameEvent.value = false
    }

    fun onSortNumberClicked() {
        _sortNumberEvent.value = true
    }

    fun onSortedByNumber() {
        _sortNumberEvent.value = false
    }

}
