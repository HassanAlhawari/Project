package controllers

import dao.SuperheroDAO
import model.Superhero

import javax.inject._
import play.api._
import play.api.data.Form
import play.api.data.Forms.{mapping, text}
import play.api.mvc._

import scala.concurrent.ExecutionContext

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() (superheroDao: SuperheroDAO, controllerComponents: ControllerComponents)
                               (implicit executionContext: ExecutionContext) extends AbstractController(controllerComponents){

/**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
def index() = Action.async {
  superheroDao.all().map { case (heros) => Ok(views.html.index(heros)) }
}

  def env() = Action { implicit request: Request[AnyContent] =>
    //Ok("Nothing to see here")
    Ok(System.getenv("JDBC_DATABASE_URL"))
  }

  val superhero = Form(
    mapping(
      "NAME" -> text(),
      "ELEMENT" -> text())(Superhero.apply)(Superhero.unapply))

  def insertHero = Action.async { implicit request =>
    val hero: Superhero = superhero.bindFromRequest.get
    superheroDao.insert(hero).map(_=> Redirect(routes.HomeController.index))
  }

  def site2() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.site2())
  }



}
