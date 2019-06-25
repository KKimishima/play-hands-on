package controllers

import domain.{Id, Name, Todo}
import play.api.data.Forms._
import play.api.data._
import play.api.mvc.{AnyContent, MessagesRequest}

object TodoForm {
  def createTodo(implicit request: MessagesRequest[AnyContent]): Todo = {
    this.apply().bindFromRequest().get
  }

  def apply(): Form[Todo] = Form(
    mapping(
      "id" -> optional(mapping("value" -> number)(Id.apply)(Id.unapply)),
      "name" -> mapping("value" -> text)(Name.apply)(Name.unapply)
    )(Todo.apply)(Todo.unapply)
  )
}
