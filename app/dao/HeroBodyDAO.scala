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

  def getHero(): Future[Seq[HeroBody]] = db.run(HeroBodies.result)

  def insert(superhero: HeroBody): Future[Unit] = db.run(HeroBodies += superhero).map { _ => () }

  private class HeroTable(tag: Tag) extends Table[HeroBody](tag, "HEROBODY") {

    def name = column[String]("NAME", O.PrimaryKey)
    def healthMax = column[Int]("HEALTHMAX")
    def healthCurrent = column[Int]("HEALTHCURRENT")
    def defense = column[Int]("DEFENSE")
    def attack = column[Int]("ATTACK")
    def manaMax = column[Int]("MANAMAX")
    def manaCurrent = column[Int]("MANACURRENT")
    def statpoints = column[Int]("STATPOINTS")

    def * = (name, healthMax, healthCurrent, defense, attack, manaMax, manaCurrent, statpoints ) <> (HeroBody.tupled , HeroBody.unapply)
  }
}