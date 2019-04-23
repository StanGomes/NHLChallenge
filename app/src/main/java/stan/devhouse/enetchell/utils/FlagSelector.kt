package stan.devhouse.enetchell.utils

class FlagSelector {
    fun selectFlagImage(nationality: Nationality) : String {
        return when (nationality) {
            Nationality.CAN -> "ic_canada"
            Nationality.USA -> "ic_usa"
            Nationality.CZE -> "ic_czech"
            Nationality.SWE -> "ic_sweden"
            Nationality.RUS -> "ic_russia"
            Nationality.AUT -> "ic_austria"
            Nationality.DNK -> "ic_denmark"
            Nationality.SVK -> "ic_slovakia"
            Nationality.FIN -> "ic_finland"
            Nationality.DEU -> "ic_germany"
        }
    }
}

enum class Nationality{
    CAN,
    USA,
    CZE,
    AUT,
    SWE,
    RUS,
    DNK,
    SVK,
    FIN,
    DEU
}