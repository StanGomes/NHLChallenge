package stan.devhouse.enetchell.view.teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.team_fragment.*
import stan.devhouse.enetchell.R
import stan.devhouse.enetchell.data.remote.RetrofitService
import stan.devhouse.enetchell.data.repository.TeamRepository
import stan.devhouse.enetchell.view.BaseFragment
import stan.devhouse.enetchell.view.HomeFragmentDirections

class TeamFragment : BaseFragment(), TeamClickListener {


    private lateinit var viewModel: TeamViewModel
    private lateinit var adapter: TeamAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.team_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager = LinearLayoutManager(context)
        teams_rv.layoutManager = linearLayoutManager
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (checkConnectivity(context)) {
            val viewModelFactory = TeamViewModelFactory(teamRepository = TeamRepository(RetrofitService.apiService))
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(TeamViewModel::class.java)
            observeTeam()
        }
    }

    private fun observeTeam() {
        viewModel.teamsLiveData.observe(this, Observer {
            if (it == null || it.isEmpty()) {
                return@Observer
            } else {
                adapter = TeamAdapter(it, this)
                teams_rv.adapter = adapter
            }
        })
    }

    override fun onTeamClicked(teamId: Int, teamName: String) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRosterFragment(teamName, teamId))
        activity?.drawer_layout?.closeDrawers()
    }

}
