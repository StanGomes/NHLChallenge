package stan.devhouse.enetchell.view.roster

import androidx.recyclerview.widget.DiffUtil
import stan.devhouse.enetchell.data.remote.response.Roster

/**
 * DiffUtil Callback Class for Roster List. Used to compare and animate items in recyclerView in a worker thread
 */

class RosterDiffCallback : DiffUtil.ItemCallback<Roster>() {
    override fun areItemsTheSame(oldItem: Roster, newItem: Roster): Boolean {
        return oldItem.person.id == newItem.person.id
    }

    override fun areContentsTheSame(oldItem: Roster, newItem: Roster): Boolean {
        return oldItem.person.id == newItem.person.id
    }
}