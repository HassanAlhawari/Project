package model

case class HeroBody(name: String, healthmax: Int, healthcurrent: Int, defense: Int,
                    attack: Int, manamax: Int, manacurrent: Int, statpoints: Int,
                    firstAbility: String, firstDescription: String, secondAbility: String, secondDescription: String,
                    thirdAbility: String, thirdDescription: String) {
}
