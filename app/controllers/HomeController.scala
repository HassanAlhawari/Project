package controllers

import dao.HeroBodyDAO
import dao.LoginDAO
import model.Superhero
import model.HeroBody

import javax.inject._
import play.api._
import play.api.data.Form
import play.api.data.Forms.{mapping, number, text}
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(heroBodyDao: HeroBodyDAO, loginDao: LoginDAO, controllerComponents: ControllerComponents)
                              (implicit executionContext: ExecutionContext) extends AbstractController(controllerComponents) {

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
      flatMap(userhero => if (userhero.passwort == hero.passwort) Some("ok") else None))
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
    loginDao.insert(hero).map(_ => Redirect(routes.HomeController.createHero()).withSession("heroname" -> hero.name))
  }

  def createHero() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.create(request.session.get("heroname").getOrElse("nichts")))}

  val heroBodyForm = Form(
    mapping(
      "name" -> text(),
      "healthmax" -> number(),
      "healthcurrent" -> number(),
      "defense" -> number(),
      "attack" -> number(),
      "manamax" -> number(),
      "manacurrent" -> number(),
      "statpoints" -> number(),
      "firstability" -> text(),
      "firstdescription" -> text(),
      "secondability" -> text(),
      "seconddescription" -> text(),
      "thirdability" -> text(),
      "thirddescription" -> text())(HeroBody.apply)(HeroBody.unapply))

  def insertHeroBody = Action.async { implicit request =>
    val hero: HeroBody = heroBodyForm.bindFromRequest().get
    heroBodyDao.insert(hero).map(_ => Redirect(routes.HomeController.site2).withSession("heroname" -> hero.name))
  }


  def site2() = Action.async { implicit request: Request[AnyContent] =>
    heroBodyDao.getHero(request.session.get("heroname").getOrElse("nichts")).map { case (heros) => Ok(views.html.site2(heros)) }
  }

}
