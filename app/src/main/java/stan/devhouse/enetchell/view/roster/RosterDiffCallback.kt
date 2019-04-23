package stan.devhouse.enetchell.view.roster

import androidx.recyclerview.widget.DiffUtil
import stan.devhouse.enetchell.data.remote.response.Roster

class RosterDiffCallback : DiffUtil.ItemCallback<Roster>() {
    override fun areItemsTheSame(oldItem: Roster, newItem: Roster): Boolean {
        return oldItem.person.id == newItem.person.id
    }

    override fun areContentsTheSame(oldItem: Roster, newItem: Roster): Boolean {
        return oldItem.person.id == newItem.person.id
    }
}