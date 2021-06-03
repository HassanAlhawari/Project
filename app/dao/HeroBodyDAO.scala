package dao

import model.HeroBody
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class HeroBodyDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
                           (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  private val HeroBodies = TableQuery[HeroTable]

  def getHero(herobody: String): Future[Seq[HeroBody]] = db.run(HeroBodies.filter(_.name === herobody).result)

  def insert(herobody: HeroBody): Future[Unit] = db.run(HeroBodies += herobody).map { _ => () }

  def refresh(herobody: HeroBody): Future[Unit] = db.run(HeroBodies.insertOrUpdate(herobody)).map { _ => () }

  private class HeroTable(tag: Tag) extends Table[HeroBody](tag, "HEROBODY") {

    def name = column[String]("NAME", O.PrimaryKey)
    def healthMax = column[Int]("HEALTHMAX")
    def healthCurrent = column[Int]("HEALTHCURRENT")
    def defense = column[Int]("DEFENSE")
    def attack = column[Int]("ATTACK")
    def manaMax = column[Int]("MANAMAX")
    def manaCurrent = column[Int]("MANACURRENT")
    def statpoints = column[Int]("STATPOINTS")
    def firstAbility = column[String]("FIRSTABILITY")
    def firstDescription = column[String]("FIRSTDESCRIPTION")
    def secondAbility = column[String]("SECONDABILITY")
    def secondDescription = column[String]("SECONDDESCRIPTION")
    def thirdAbility = column[String]("THIRDABILITY")
    def thirdDescription = column[String]("THIRDDESCRIPTION")

    def * = (name, healthMax, healthCurrent, defense, attack, manaMax, manaCurrent, statpoints,
      firstAbility, firstDescription, secondAbility, secondDescription, thirdAbility, thirdDescription) <> (HeroBody.tupled, HeroBody.unapply)
  }
}