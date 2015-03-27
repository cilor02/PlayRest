package model

/**
 * Created by MICHAEL on 23/03/2015.
 */
case class Account  (
 id :String,
 email: String,
 title:String,
 foreName :String,
 familyName :String,
 address :String,
 country :String,
 province :String,
 organisation: String,
 postalCode :String,
 city :String,
telephone:String,
 graduationYear :String,
specialties : List[String],
professions : List[String]

)
{

}
