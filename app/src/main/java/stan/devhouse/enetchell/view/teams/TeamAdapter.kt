package stan.devhouse.enetchell.view.teams

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.team_layout.view.*
import stan.devhouse.enetchell.R
import stan.devhouse.enetchell.data.remote.response.Teams
import stan.devhouse.enetchell.utils.LogoSelector

class TeamAdapter(private val context: Context ,private val teams: MutableList<Teams>, private val teamClickListener: TeamClickListener) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.team_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = teams.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTeams(teams[position])
        holder.setImage(teams[position].teamName, holder.itemView.findViewById(R.id.team_logo_iv), context)
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

//        private var teams: Teams? = null

        init {
            itemView.setOnClickListener(this)
        }


        fun bindTeams(teams: Teams) {
//            this.teams = teams
            itemView.team_name_tv.text = teams.teamName
            //            itemView.team_logo_iv.setImageDrawable()

        }
        fun setImage(teamName: String, imageView: CircleImageView, context: Context) {
            val imageName: String? = LogoSelector().selectLogo(teamName)
            val imageIdentifier = context.resources.getIdentifier(imageName, "drawable", context.packageName)
            Glide.with(context)
                    .load(imageIdentifier)
                    .into(imageView)
        }

    }

}