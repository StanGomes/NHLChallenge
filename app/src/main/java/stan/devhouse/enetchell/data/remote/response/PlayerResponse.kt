package stan.devhouse.enetchell.data.remote.response


data class PlayerResponse(
    val copyright: String,
    val people: List<People>
)

data class People(
        val active: Boolean,
        val alternateCaptain: Boolean,
        val birthCity: String,
        val birthCountry: String,
        val birthDate: String,
        val birthStateProvince: String,
        val captain: Boolean,
        val currentAge: Int,
        val currentTeam: CurrentTeam,
        val firstName: String,
        val fullName: String,
        val height: String,
        val id: Int,
        val lastName: String,
        val link: String,
        val nationality: String,
        val primaryNumber: String,
        val primaryPosition: PrimaryPosition,
        val rookie: Boolean,
        val rosterStatus: String,
        val shootsCatches: String,
        val weight: Int
)

data class PrimaryPosition(
    val abbreviation: String,
    val code: String,
    val name: String,
    val type: String
)

data class CurrentTeam(
    val id: Int,
    val link: String,
    val name: String
)