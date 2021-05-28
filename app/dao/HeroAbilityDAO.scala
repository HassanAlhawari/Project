package dao

import model.Superhero
import model.HeroBody
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class HeroAbilityDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
                           (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private val HeroBody = TableQuery[HeroTable]

  def getHero(): Future[Seq[Superhero]] = db.run(HeroBody.result)

  def insert(superhero: Superhero): Future[Unit] = db.run(HeroBody += superhero).map { _ => () }

  private class HeroTable(tag: Tag) extends Table[Superhero](tag, "SUPERHERO") {

    def name = column[String]("NAME", O.PrimaryKey)
    def element = column[String]("ELEMENT")


    def * = (name, element) <> (Superhero.tupled, Superhero.unapply)
  }
}