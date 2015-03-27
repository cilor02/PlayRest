package controllers

import model.Account
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json

import play.api.mvc.{Action, Controller}

object Application extends Controller {
  def index = Action {
    Ok(views.html.index("Hello Play Framework"))
  }

//  def accountByEmail = Action.async {
//    scala.concurrent.Future{("email")}.map (Ok(_))
//  }
import model.jsonTransformers.AccountWrites.accountWrites
 import model.jsonTransformers.AccountReads.accountReads
  def testJson = Action {
    val account = Account("id1","mike","ilori","suffolk road","UK","essex","ig11","ilford","1986")

    val jsonString = """{"id":"id1","foreName":"michael","familyName":"whyte-ilori","address":"73 suffolk road","country":"GB","province":"essex","postalCode":"ig11","city":"ilford","graduationYear":"1986"}"""
    val jsValue = Json.parse(jsonString)
    val account2 = jsValue.as[Account]
   // val jsonAccount = Json.toJson(account)(accountWrites)
    Ok(Json.toJson[Account](account2))
  }
}