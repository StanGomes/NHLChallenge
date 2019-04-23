package stan.devhouse.enetchell.view.roster

import android.os.Bundle
import android.view.*
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.roster_fragment.*
import stan.devhouse.enetchell.R
import stan.devhouse.enetchell.data.remote.RetrofitService
import stan.devhouse.enetchell.data.remote.response.Roster
import stan.devhouse.enetchell.data.repository.RosterRepository
import stan.devhouse.enetchell.utils.PlayerPosition
import stan.devhouse.enetchell.view.BaseFragment

class RosterFragment : BaseFragment(), RosterClickListener {

    private lateinit var rosterViewModelFactory: RosterViewModelFactory
    private lateinit var viewModel: RosterViewModel
    private lateinit var rosterList: List<Roster>
    private lateinit var adapter: RosterAdapter
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var args: RosterFragmentArgs
    private lateinit var filterMenu: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args = RosterFragmentArgs.fromBundle(arguments!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity?.drawer_layout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        gridLayoutManager = GridLayoutManager(context, 2)

        return inflater.inflate(R.layout.roster_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        roster_rv.layoutManager = gridLayoutManager
        if (checkConnectivity(context)) {
            rosterViewModelFactory = RosterViewModelFactory(rosterRepository = RosterRepository(RetrofitService.apiService))
            viewModel = ViewModelProviders.of(this, rosterViewModelFactory).get(RosterViewModel::class.java)
            setupAdapter()
            observeViewModel()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }


    private fun observeCLiveData() {
        viewModel.filterCLiveData.observe(this, Observer {
            if (it == true) {
                val filterByC = rosterList.filter { roster ->
                    roster.position.abbreviation == PlayerPosition.C.toString()
                }
                adapter.submitList(filterByC)
                viewModel.onFilteredByC()
            }
        })
    }

    private fun observeGLiveData() {
        viewModel.filterGLiveData.observe(this, Observer {
            if (it == true) {
                val filterByG = rosterList.filter { roster ->
                    roster.position.abbreviation == PlayerPosition.G.toString()
                }
                adapter.submitList(filterByG)
                viewModel.onFilteredByG()
            }
        })
    }

    private fun observeDLiveData() {
        viewModel.filterDLiveData.observe(this, Observer {
            if (it == true) {
                val filterByD = rosterList.filter { roster ->
                    roster.position.abbreviation == PlayerPosition.D.toString()
                }
                adapter.submitList(filterByD)
                viewModel.onFilteredByD()
            }
        })
    }

    private fun observeRWLiveData() {
        viewModel.filterRWLiveData.observe(this, Observer {
            if (it == true) {
                val filterByRw = rosterList.filter { roster ->
                    roster.position.abbreviation == PlayerPosition.RW.toString()
                }
                adapter.submitList(filterByRw)
                viewModel.onFilteredByRW()
            }
        })
    }

    private fun observeLWLiveData() {
        viewModel.filterLWLiveData.observe(this, Observer {
            if (it == true) {
                val filterByLw = rosterList.filter { roster ->
                    roster.position.abbreviation == PlayerPosition.LW.toString()
                }
                adapter.submitList(filterByLw)
                viewModel.onFilteredByLW()
            }
        })
    }

    private fun observeViewModel() {
        viewModel.getRoster(args.teamId)
        viewModel.rosterLiveData.observe(this, Observer {
            rosterList = it
            adapter.submitList(rosterList)
        })
    }

    private fun setupAdapter() {
        adapter = RosterAdapter(this)
        roster_rv.adapter = adapter
    }

    private fun observeSortNameEvent() {
        viewModel.sortEvent.observe(this, Observer {
            if (it == true) {
                val sortedRoster = rosterList.sortedBy { roster ->
                    roster.person.fullName
                }
                adapter.submitList(sortedRoster)
                roster_rv.layoutManager?.scrollToPosition(0)
                Snackbar.make(activity?.findViewById(android.R.id.content)!!, "Sorted by name", Snackbar.LENGTH_SHORT).show()
                viewModel.onSortedByName()
            }
        })
    }

    private fun observeSortNumberEvent() {
        viewModel.sortNumberEvent.observe(this, Observer { it ->
            if (it == true) {
                val sortedRoster = rosterList.sortedWith(nullsFirst(compareBy { roster ->
                    roster.jerseyNumber
                }))
                adapter.submitList(sortedRoster)
                roster_rv.layoutManager?.scrollToPosition(0)
                Snackbar.make(activity?.findViewById(android.R.id.content)!!, "Sorted by jersey number", Snackbar.LENGTH_SHORT).show()
                viewModel.onSortedByNumber()
            }
        })
    }

    override fun onPlayerClick(playerId: Long, playerName: String) {
        findNavController().navigate(RosterFragmentDirections.actionRosterFragmentToPlayerFragment(playerId, playerName))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        filterMenu = menu.findItem(R.id.menu_filter_by)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_sort_name -> {
                viewModel.onSortByNameClicked()
                observeSortNameEvent()
                true
            }
            R.id.menu_sort_number -> {
                viewModel.onSortNumberClicked()
                observeSortNumberEvent()
                true
            }
            R.id.filter_c -> {
                viewModel.filterByPosition(PlayerPosition.C)
                observeCLiveData()
                true
            }
            R.id.filter_d -> {
                viewModel.filterByPosition(PlayerPosition.D)
                observeDLiveData()
                true
            }
            R.id.filter_g -> {
                viewModel.filterByPosition(PlayerPosition.G)
                observeGLiveData()
                true
            }
            R.id.filter_rw -> {
                viewModel.filterByPosition(PlayerPosition.RW)
                observeRWLiveData()
                true
            }
            R.id.filter_lw -> {
                viewModel.filterByPosition(PlayerPosition.LW)
                observeLWLiveData()
                true
            }
            R.id.filter_reset -> {
                resetFilter()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun resetFilter() {
        adapter.submitList(rosterList)
    }

}
