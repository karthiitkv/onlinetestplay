package org.kvkit.controllers

import play.api.mvc.Action
import play.api.mvc.Results._
import play.api.mvc.Controller
import javax.inject.Inject
import play.api.libs.ws.WSClient
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.ExecutionContext
import play.api.http.ContentTypeOf
import play.api.libs.json._
import play.api.libs.json.Reads._ 
import org.kvkit.model._
import org.kvkit.model.OnlineTestModels._


class OnlineTestController @Inject() (ws: WSClient) (implicit context: ExecutionContext) extends Controller {
  def firstAction = Action { implicit request =>
    Ok("The Request is ["+request+"]")
    //val result = ws.url("http://onlinetest-kvkit.rhcloud.com/kvkapi/getAllQueAns").get()
  }
  
  def getAllQueAns = Action.async { implicit request =>
    //val result = ws.url("http://onlinetest-kvkit.rhcloud.com/kvkapi/getAllQueAns").withHeaders("Accept" -> "application/json").get()
    val result = ws.url("http://localhost:8080/OnlineTestApp/kvkapi/getAllQueAns").withHeaders("Accept" -> "application/json").get()
    result.map { allQueAns => 
      //JsPath to get all the ques
      val que = allQueAns.json \ "quesAns" \ "question"
      println("que : " + que)
      val queAnsJson = allQueAns.body
      println("queAnsJson: " + queAnsJson)
      println("-----------------------")
      //Sending the questions from all Que and Ans
      Ok(allQueAns.body).as("application/json") 
    }
  }
  
  def getAllQue = Action.async { implicit request =>
    val result = ws.url("http://onlinetest-kvkit.rhcloud.com/kvkapi/getAllQueAns").withHeaders("Accept" -> "application/json").get()
    //val result = ws.url("http://localhost:8080/OnlineTestApp/kvkapi/getAllQueAns").withHeaders("Accept" -> "application/json").get()
    result.map { allQueAns => 
      //JsPath to get all the ques
      var queList: List[String] = List()
      val que = allQueAns.json \ "question"
      //println("que : " + que)
      val queAnsJson = allQueAns.body
      //println("queAnsJson: " + queAnsJson)
      //println("-----------------------")
      //Parsing the body to json and validate it
      Json.parse(queAnsJson).validate[QuesAnsList].fold(
          invalid = { errorResponse => 
            println("Error -> "+errorResponse)
          },
          valid = { successRespone => 
            println("Success -> "+successRespone)
            successRespone.quesAns foreach { queAns =>
              queList = queList :+ (queAns.question)
            }
          }
      )
      //Sending the questions from all Que and Ans
      Ok(Json.obj("questions"-> queList)).as("application/json") 
    }
  }
}