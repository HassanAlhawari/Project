package dao

import model.Superhero
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class LoginDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
                        (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  private val Superheros = TableQuery[HeroTable]

  def getHero(superhero: String): Future[Seq[Superhero]] = db.run(Superheros.filter(_.name === superhero).result)

  def getPassword(superhero: String): Future[Seq[Superhero]] = db.run(Superheros.filter(_.name === superhero).result)

  def insert(superhero: Superhero): Future[Unit] = db.run(Superheros += superhero).map { _ => () }

  private class HeroTable(tag: Tag) extends Table[Superhero](tag, "SUPERHERO") {

    def name = column[String]("NAME", O.PrimaryKey)
    def passwort = column[String]("PASSWORT")


    def * = (name, passwort) <> (Superhero.tupled, Superhero.unapply)
  }
}