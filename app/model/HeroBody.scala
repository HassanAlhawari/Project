package model

case class HeroBody(name: String, healthmax: Int =50, healthcurrent: Int = 50, defense: Int = 5,
                    attack: Int = 5, manamax: Int =20, manacurrent: Int = 20, statpoints: Int = 0,
                    firstAbility: String, firstDescription: String, secondAbility: String, secondDescription: String,
                    thirdAbility: String, thirdDescription: String) {
}
