package stan.devhouse.enetchell.view.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.player_fragment.*
import stan.devhouse.enetchell.R
import stan.devhouse.enetchell.data.remote.RetrofitService
import stan.devhouse.enetchell.data.remote.response.People
import stan.devhouse.enetchell.data.repository.PlayerRepository
import stan.devhouse.enetchell.utils.FlagSelector
import stan.devhouse.enetchell.utils.Nationality
import stan.devhouse.enetchell.view.BaseFragment

class PlayerFragment : BaseFragment() {


    private lateinit var viewModel: PlayerViewModel
    private lateinit var viewModelFactory: PlayerViewModelFactory
    private lateinit var playerDetail: List<People>
    private lateinit var args: PlayerFragmentArgs

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        args = PlayerFragmentArgs.fromBundle(arguments!!)
        return inflater.inflate(R.layout.player_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (checkConnectivity(context)) {
            viewModelFactory = PlayerViewModelFactory(playerRepository = PlayerRepository(RetrofitService.apiService))
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(PlayerViewModel::class.java)
            init()
        } else Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show()
    }

    private fun init() {

        viewModel.getPlayerDetails(args.playerId)
        viewModel.playerLiveData.observe(this, Observer {
            playerDetail = it
            for (i in playerDetail.indices) {
                player_age_tv.text = playerDetail[i].currentAge.toString()
                player_team_detail_tv.text = playerDetail[i].currentTeam.name
                player_birthdate_tv.text = playerDetail[i].birthDate
                player_height_tv.text = playerDetail[i].height
                player_nationality_tv.text = playerDetail[i].nationality
                setFlagImage(playerDetail[i].nationality)
            }
        })
    }

    /**
     * Pretty hacky and bad code I came up with to choose a flag for the player,
     * since the api doesn't provide any resources or country list
     */

    private fun setFlagImage(nationality: String) {
        val imageName: String? = when (nationality) {
            Nationality.CAN.toString() -> FlagSelector().selectFlagImage(Nationality.CAN)
            Nationality.USA.toString() -> FlagSelector().selectFlagImage(Nationality.USA)
            Nationality.AUT.toString() -> FlagSelector().selectFlagImage(Nationality.AUT)
            Nationality.DNK.toString() -> FlagSelector().selectFlagImage(Nationality.DNK)
            Nationality.SWE.toString() -> FlagSelector().selectFlagImage(Nationality.SWE)
            Nationality.SVK.toString() -> FlagSelector().selectFlagImage(Nationality.SVK)
            Nationality.RUS.toString() -> FlagSelector().selectFlagImage(Nationality.RUS)
            Nationality.FIN.toString() -> FlagSelector().selectFlagImage(Nationality.FIN)
            Nationality.CZE.toString() -> FlagSelector().selectFlagImage(Nationality.CZE)
            Nationality.DEU.toString() -> FlagSelector().selectFlagImage(Nationality.DEU)
            else -> "ic_european_union"
        }
        Glide.with(this)
                .load(getImage(imageName!!))
                .into(player_flag_iv)
    }

    private fun getImage(imageName: String): Int {
        return resources.getIdentifier(imageName, "drawable", context?.packageName)
    }

}

