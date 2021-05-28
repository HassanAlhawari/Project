package controllers

import dao.HeroBodyDAO
import dao.LoginDAO
import model.Superhero

import javax.inject._
import play.api._
import play.api.data.Form
import play.api.data.Forms.{mapping, text}
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() (superheroDao: HeroBodyDAO, loginDao : LoginDAO, controllerComponents: ControllerComponents)
                               (implicit executionContext: ExecutionContext) extends AbstractController(controllerComponents){

/**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
def index() = Action {
   Ok(views.html.index())
}

  def env() = Action { implicit request: Request[AnyContent] =>
    //Ok("Nothing to see here")
    Ok(System.getenv("JDBC_DATABASE_URL"))
  }

  val superherologinform = Form(
    mapping(
      "name" -> text(),
      "passwort" -> text())(Superhero.apply)(Superhero.unapply))

  def loginHero = Action.async { implicit request =>
    val hero: Superhero = superherologinform.bindFromRequest().get
    val hero2 = loginDao.getHero(hero.name)
    val checkname: Future[Option[String]] = hero2.map(heromap => heromap.headOption.
      flatMap(userhero => if(userhero.passwort == hero.passwort) Some("ok") else None))
    print(checkname)
    checkname.map(name => name match {
      case Some("ok") => Redirect(routes.HomeController.site2).withSession("heroname" -> hero.name)
      case None => Redirect(routes.HomeController.index())
    })

  }

  val superheroform = Form(
    mapping(
      "neuerName" -> text(),
      "neuerPasswort" -> text())(Superhero.apply)(Superhero.unapply))

  def insertHero = Action.async { implicit request =>
    val hero: Superhero = superheroform.bindFromRequest().get
    loginDao.insert(hero).map(_=> Redirect(routes.HomeController.site2).withSession("heroname" -> hero.name))
  }

  def site2() = Action.async { implicit request: Request[AnyContent] =>
    loginDao.getHero(request.session.get("heroname").getOrElse("nichts")).map { case (heros) => Ok(views.html.site2(heros)) }
  }

}
