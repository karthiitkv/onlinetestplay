package org.kvkit.model

import play.api.libs.json.Json
import play.api.libs.json._

case class QuesAnsList (
    quesAns: List[QuesAns]    
)

case class QuesAns (
    queId: Int,
    question: String,
    opt1: String,
    opt2: String,
    opt3: String,
    opt4: String,
    ans: String,
    display: Option[Boolean],
    difficultLevel: Option[Int]
)

object OnlineTestModels {
  implicit val QuesAnsFormat = Json.format[QuesAns]
  implicit val QuesAnsListFormat = Json.format[QuesAnsList]
}