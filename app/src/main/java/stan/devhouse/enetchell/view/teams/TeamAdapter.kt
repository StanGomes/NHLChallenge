package stan.devhouse.enetchell.view.teams

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.team_layout.view.*
import stan.devhouse.enetchell.R
import stan.devhouse.enetchell.data.remote.response.Teams

class TeamAdapter(private val teams: MutableList<Teams>, private val teamClickListener: TeamClickListener) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.team_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = teams.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTeams(teams[position])

        holder.itemView.setOnClickListener {
            val teamName = teams[position].teamName
            val teamId = teams[position].teamId
            Log.d("TeamAdapter", "Clicked on $teamName")
            teamClickListener.onTeamClicked(teamId, teamName)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        override fun onClick(v: View?) {

        }

        private var teams: Teams? = null

        init {
            itemView.setOnClickListener(this)
        }

        fun bindTeams(teams: Teams) {
            this.teams = teams
            itemView.team_name_tv.text = teams.teamName
        }


    }
}