package dao

import model.Superhero
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class SuperheroDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
                          (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private val Superheros = TableQuery[ProduktTable]

  def getHero(): Future[Seq[Superhero]] = db.run(Superheros.result)

  def insert(superhero: Superhero): Future[Unit] = db.run(Superheros += superhero).map { _ => () }

  private class ProduktTable(tag: Tag) extends Table[Superhero](tag, "SUPERHERO") {

    def name = column[String]("NAME", O.PrimaryKey)
    def element = column[String]("ELEMENT")


    def * = (name, element) <> (Superhero.tupled, Superhero.unapply)
  }
}