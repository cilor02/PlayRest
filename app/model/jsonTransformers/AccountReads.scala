package model.jsonTransformers

import model.Account
import play.api.libs.json.{Reads, JsPath}

/**
 * Created by MICHAEL on 23/03/2015.
 */
object AccountReads {
  import play.api.libs.functional.syntax._

  implicit val accountReads: Reads[Account] = (
    (JsPath \ "accountId").readNullable[String] and
      (JsPath \ "email").readNullable[String] and
      (JsPath \ "title").readNullable[String] and
      (JsPath \ "givenName").readNullable[String] and
      (JsPath \ "familyName").readNullable[String] and
      (JsPath \ "address").readNullable[String] and
      (JsPath \ "country").readNullable[String] and
      (JsPath \ "bmaNumber").readNullable[String] and
      (JsPath \ "province").readNullable[String] and
      (JsPath \ "organisation").readNullable[String] and
      (JsPath \ "postalCode").readNullable[String] and
      (JsPath \ "city").readNullable[String] and
      (JsPath \ "telephone").readNullable[String] and
      (JsPath \ "graduationYear").readNullable[String] and
      (JsPath \ "specialties").readNullable[List[String]] and
      (JsPath \ "professions").readNullable[List[String]]
    )(Account)
}
