package controllers

import domain.{Name, Todo}
import play.api.data.Forms._
import play.api.data._
import play.api.mvc.{AnyContent, MessagesRequest}

object TodoForm {
  def apply(): Form[String] = {
    Form("name" -> nonEmptyText)
  }

  def createTodo(implicit request: MessagesRequest[AnyContent]): Todo = {
    val name: String = this.apply().bindFromRequest().get
    Todo(
      id = None,
      name = Name(name)
    )
  }
}
