package model.jsonTransformers

import model.Account

/**
 * Created by MICHAEL on 23/03/2015.
 */
object AccountWrites {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val accountWrites: Writes[Account] = (
    (JsPath \ "id").write[String] and
      (JsPath \ "foreName").write[String] and
      (JsPath \ "familyName").write[String] and
      (JsPath \ "address").write[String] and
      (JsPath \ "country").write[String] and
      (JsPath \ "province").write[String] and
      (JsPath \ "postalCode").write[String] and
      (JsPath \ "city").write[String] and
      (JsPath \ "graduationYear").write[String]
    )(unlift(Account.unapply))

}
