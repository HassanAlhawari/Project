package dao

import model.HeroAbility


import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class HeroAbilityDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
                           (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private val HeroAbilities = TableQuery[AbilityTable]

  def getAbility(): Future[Seq[HeroAbility]] = db.run(HeroAbilities.result)

  def insert(heroability: HeroAbility): Future[Unit] = db.run(HeroAbilities += heroability).map { _ => () }

  private class AbilityTable(tag: Tag) extends Table[HeroAbility](tag, "HEROABILITY") {

    def name = column[String]("NAME", O.PrimaryKey)
    def firstAbility = column[String]("FIRSTABILITY")
    def secondAbility = column[String]("SECONDABILITY")
    def thirdAbility = column[String]("THIRDABILITY")


    def * = (name, firstAbility,secondAbility,thirdAbility ) <> (HeroAbility.tupled, HeroAbility.unapply)
  }
}