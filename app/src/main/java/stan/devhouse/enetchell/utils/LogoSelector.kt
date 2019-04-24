package stan.devhouse.enetchell.utils

class LogoSelector {
    fun selectLogo(teams: String) : String{
        return when(teams){
            "Anaheim Ducks" -> "anaheim_ducks"
            "Boston Bruins" -> "boston_bruins"
            "Buffalo Sabres" -> "buffalo_sabres"
            "Calgary Flames" -> "calgary_flames"
            "Carolina Hurricanes" -> "carolina_hurricanes"
            "Chicago Blackhawks" -> "chicago_blackhawks"
            "Colorado Avalanche" -> "colorado_avalanche"
            "Columbus Blue Jackets" -> "columbus_blue_jackets"
            "Dallas Stars" -> "dallas_stars"
            "Edmonton Oilers" -> "edmonton_oilers"
            "Florida Panthers" ->"florida_panthers"
            "Los Angeles Kings" -> "los_angeles_kings"
            "Minnesota Wild" -> "minnesota_wild"
            "Montréal Canadiens" -> "montreal_canadiens"
            "Nashville Predators" -> "nashville_predators"
            "New Jersey Devils" -> "new_jersey_devils"
            "New York Islanders" -> "new_york_islanders"
            "New York Rangers" -> "new_york_rangers"
            "Ottawa Senators" -> "ottawa_senators"
            "Philadelphia Flyers" -> "philadelphia_flyers"
            "Arizona Coyotes"-> "phoenix_coyotes"
            "Detroit Red Wings" -> "detroit_red_wings"
            "Vegas Golden Knights" -> "vegas_gknights"
            "San Jose Sharks" -> "san_jose_sharks"
            "St. Louis Blues"-> "st_louis_blues"
            "Tampa Bay Lightning" -> "tampa_bay_lightning"
            "Pittsburgh Penguins" -> "pittsburgh_penguins"
            "Toronto Maple Leafs" -> "toronto_maple_leafs"
            "Vancouver Canucks"-> "vancouver_canucks"
            "Washington Capitals" -> "washington_capitals"
            "Winnipeg Jets"-> "winnipeg_jets"
            else -> "ic_canada"
        }
    }
}