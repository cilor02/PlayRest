package model.jsonTransformers

import model.Account
import play.api.libs.functional.syntax._
import play.api.libs.json.{Reads, JsPath}

/**
 * Created by MICHAEL on 23/03/2015.
 */
object AccountReads {
  implicit val accountReads: Reads[Account] = (
    (JsPath \ "id").readNullable[String] and
      (JsPath \ "email").read[String](email) and
      (JsPath \ "title").readNullable[String] and
      (JsPath \ "foreName").readNullable[String] and
      (JsPath \ "familyName").readNullable[String] and
      (JsPath \ "address").readNullable[String] and
      (JsPath \ "country").read[String] and
      (JsPath \ "province").readNullable[String] and
      (JsPath \ "organisation").readNullable[String] and
      (JsPath \ "postalCode").readNullable[String] and
      (JsPath \ "city").readNullable[String] and
      (JsPath \ "telephone").readNullable[String] and
      (JsPath \ "graduationYear").readNullable[String] and
      (JsPath \ "specialties").read[List[String]] and
      (JsPath \ "professions").read[List[String]]
    )(Account.apply _)
}
