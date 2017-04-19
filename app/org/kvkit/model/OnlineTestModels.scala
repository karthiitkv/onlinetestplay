package org.kvkit.model

import play.api.libs.json.Json
import play.api.libs.json._

case class QuesAnsList (
    quesAns: List[QuesAns]    
)

case class QuesAns (
    queId: Option[Int],
    question: String,
    opt1: String,
    opt2: String,
    opt3: String,
    opt4: String,
    ans: String,
    display: Option[Boolean],
    difficultLevel: Option[Int]
)

case class QuesAnsListForUpdate (
    quesAns: List[QuesAnsForUpdate]    
)

case class QuesAnsForUpdate (
    queId: Int,
    question: Option[String],
    opt1: Option[String],
    opt2: Option[String],
    opt3: Option[String],
    opt4: Option[String],
    ans: Option[String],
    display: Option[Boolean],
    difficultLevel: Option[Int]
)

object OnlineTestModels {
  implicit val QuesAnsFormat = Json.format[QuesAns]
  implicit val QuesAnsListFormat = Json.format[QuesAnsList]
  implicit val QuesAnsForUpdateFormat = Json.format[QuesAnsForUpdate]
  implicit val QuesAnsListForUpdateFormat = Json.format[QuesAnsListForUpdate]
}