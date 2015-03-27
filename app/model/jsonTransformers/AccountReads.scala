package model.jsonTransformers

import model.Account
import play.api.libs.functional.syntax._
import play.api.libs.json.{Reads, JsPath}

/**
 * Created by MICHAEL on 23/03/2015.
 */
object AccountReads {
  implicit val accountReads: Reads[Account] = (
    (JsPath \ "id").read[String] and
      (JsPath \ "foreName").read[String] and
      (JsPath \ "familyName").read[String] and
      (JsPath \ "address").read[String] and
      (JsPath \ "country").read[String] and
      (JsPath \ "province").read[String] and
      (JsPath \ "postalCode").read[String] and
      (JsPath \ "city").read[String] and
      (JsPath \ "graduationYear").read[String]
    )(Account.apply _)
}
