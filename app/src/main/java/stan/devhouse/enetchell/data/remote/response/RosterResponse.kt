package stan.devhouse.enetchell.data.remote.response


data class RosterResponse(
        val copyright: String,
        val link: String,
        val roster: List<Roster>
)

data class Roster(
        val jerseyNumber: String,
        val person: Person,
        val position: Position
)

data class Person(
        val fullName: String,
        val id: Int,
        val link: String
)

data class Position(
        val abbreviation: String,
        val code: String,
        val name: String,
        val type: String
)