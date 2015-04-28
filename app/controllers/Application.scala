package controllers

import com.bmj.bsi.restapi.ics.net.BSIRestIcsApi
import com.milo.scala.adaptor.RestServiceAdaptor
import model.Account
import model.jsonTransformers.AccountWrites.accountWrites
import model.jsonTransformers.AccountReads.accountReads
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json

import play.api.mvc.{Action, Controller,Result}
//import play.api.mvc.Results.Error

import scala.util.Failure
import scala.util.Success

object Application extends Controller {

  val ics = new BSIRestIcsApi("ics.internal.bmjgroup.com", 6700)
  val adaptor = new RestServiceAdaptor(ics)

  def index = Action {
    Ok(views.html.index("Hello Play Framework"))
  }


  def accountByEmail(email:String) = Action {

    val accounts = adaptor.accountByEmail(email)
    Ok(Json.toJson(accounts))
  }

  def accountById(id:String) = Action {
    adaptor.accountById(id) match {
      case Success (success) => success match
      {
        case Some(acc) => Ok(Json.toJson(acc))
        case None => Ok(Json.toJson("{\"error\":\" Account" + id + " not found \"}"))
      }

      case Failure (exp) =>  Ok(Json.toJson("{\"error\":\"" + exp.getMessage + "\"}"))
    }
   // Ok(Json.toJson(account match {case Some(acc) => acc case None => "{\"error\":\" Account" + id + " not found \"}"}))
  }

  def updateAccountById(accountId:String) = Action(parse.json) {

    request =>
      val jsValue = request.body

      val accUpdateDetails =  jsValue.as[Account]

      adaptor.updateAccount (accUpdateDetails,accountId) match {
        case Success (success) => Ok(Json.toJson(success))
        case Failure (exp) =>  NotFound(Json.toJson("{\"error\":\"" + exp.getMessage + "\"}"))
      }  }

  def deleteAccountById(id:String) = Action {
    adaptor.deleteById(id) match {
      case Success (success) => success match
      {
        case Some(acc) => Ok(Json.toJson(acc))
        case None => NotFound(Json.toJson("{\"error\":\" Account" + id + " not found \"}"))
      }

      case Failure (exp) =>  InternalServerError(Json.toJson("{\"error\":\"" + exp.getMessage + "\"}"))
    }
  }

  def createAccount = Action(parse.json) {
    request =>
      val jsValue = request.body

      val acc =  jsValue.as[Account]

      adaptor.createNewAccount (acc) match {
        case Success (success) => Ok(Json.toJson(acc))
        case Failure (exp) =>  NotFound(Json.toJson("{\"error\":\"" + exp.getMessage + "\"}"))
      }
  }


  //  def accountByEmail = Action.async {
//    scala.concurrent.Future{("email")}.map (Ok(_))
//  }

  def receiveJson = Action (parse.json)
  { request =>

    val jsValue = request.body

    val acc =  jsValue.as[Account]


  Ok(Json.toJson[Account](acc))

}

import model.jsonTransformers.AccountWrites.accountWrites
 import model.jsonTransformers.AccountReads.accountReads
  def testJson = Action {
    val account = Account(Some("id1"),
      Some("milori@bmj.com"),
      Some("mr"),
      Some("mike"),
      Some("ilori"),
      Some("suffolk road"),
      Some("UK"),
      Some("bma001"),
      Some("essex"),
      None,
      Some("ig11"),
      Some("ilford"),
      Some("99990-009"),
      Some("1986"), Some(List("1","2")), Some(List("4","5"))
    )


    val jsonString =
      """{"id":"id1",
        |"foreName":"michael","familyName":"whyte-ilori","address":"73 suffolk road","country":"GB","province":"essex","postalCode":"ig11","city":"ilford","graduationYear":"1986"}""".stripMargin

    val jsValue = Json.parse(jsonString)
   // val account2 = jsValue.as[Account]
   // val jsonAccount = Json.toJson(account)(accountWrites)
    Ok(Json.toJson[Account](account))
  }
}