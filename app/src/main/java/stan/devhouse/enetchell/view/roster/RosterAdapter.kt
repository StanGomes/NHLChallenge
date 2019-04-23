package stan.devhouse.enetchell.view.roster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.roster_layout.view.*
import stan.devhouse.enetchell.R
import stan.devhouse.enetchell.data.remote.response.Roster

class RosterAdapter(private val playerClickListener: RosterClickListener) : ListAdapter<Roster, RosterAdapter.RosterViewHolder>(RosterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RosterViewHolder {
        return RosterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.roster_layout, parent, false))
    }

    override fun onBindViewHolder(holder: RosterViewHolder, position: Int) {
        getItem(position).let { roster ->
            with(holder) {
                playerName.text = roster.person.fullName
                playerNumber.text = roster.jerseyNumber
                playerPosition.text = roster.position.abbreviation
                itemView.setOnClickListener {
                    playerClickListener.onPlayerClick(roster.person.id.toLong(), roster.person.fullName)
                }
            }
        }
    }

    class RosterViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        var playerName: TextView = root.player_name_tv
        var playerNumber: TextView = root.player_number_tv
        var playerPosition: TextView = root.player_position_tv

    }


}
