package model

import scala.collection.immutable.List

/**
 * Created by MICHAEL on 23/03/2015.
 */
case class Account  (
 accountId :Option[String],
 email:Option[String],
 title:Option[String],
 givenName :Option[String],
 familyName :Option[String],
 address :Option[String],
 country :Option[String] ,
 bmaNumber:Option[String] ,
 province :Option[String],
 organisation: Option[String],
 postalCode :Option[String],
 city :Option[String],
 telephone:Option[String],
 graduationYear :Option[String],
 specialties: Option[List[String]],
 professions: Option[List[String]]
)

