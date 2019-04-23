package stan.devhouse.enetchell.data.remote.response

import com.google.gson.annotations.SerializedName


data class TeamResponse(
        val copyright: String,
        val teams: List<Teams>
)

data class Teams(
        @SerializedName("id")
        val teamId: Int,
        @SerializedName("name")
        val teamName: String)

