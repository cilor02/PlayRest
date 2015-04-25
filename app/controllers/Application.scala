package controllers

import com.bmj.bsi.restapi.ics.net.BSIRestIcsApi
import com.milo.scala.adaptor.RestServiceAdaptor
import model.Account
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json

import play.api.mvc.{Action, Controller}

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
    val account = adaptor.accountById(id)
    Ok(Json.toJson(account))
  }

  def updateAccountById(id:String) = Action(parse.json) {

    Ok(views.html.index("Hello Play Framework"))
  }

  def deleteAccountById(id:String) = Action {
    adaptor.deleteById(id)
    Ok(views.html.index("Hello Play Framework"))
  }

  def createAccount = Action(parse.json) {
    Ok(views.html.index("Hello Play Framework"))
  }


  //  def accountByEmail = Action.async {
//    scala.concurrent.Future{("email")}.map (Ok(_))
//  }
  import model.jsonTransformers.AccountWrites.accountWrites
  import model.jsonTransformers.AccountReads.accountReads
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
      "milori@bmj.com",
      Some("mr"),
      Some("mike"),
      Some("ilori"),
      Some("suffolk road"),
      "UK",
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