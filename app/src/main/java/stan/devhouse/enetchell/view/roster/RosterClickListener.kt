package stan.devhouse.enetchell.view.roster

interface RosterClickListener {
    fun onPlayerClick(playerId: Long, playerName: String)
}