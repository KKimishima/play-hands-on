package controllers

import domain.{Todo, TodoList}
import service.TodoService
import javax.inject.{Inject, Singleton}
import play.api.data.Form

import play.api.mvc.{AnyContent, MessagesAbstractController, MessagesControllerComponents, MessagesRequest}


@Singleton
class TodoController @Inject()(mcc: MessagesControllerComponents)(val service: TodoService) extends MessagesAbstractController(mcc) {
  def index = Action { implicit request: MessagesRequest[AnyContent] =>
    val todoList = service.getAll
    Ok(views.html.index(todoList))
  }

  def neW = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.neW(TodoForm()))
  }

  def create = Action { implicit request: MessagesRequest[AnyContent] =>
    service.save(TodoForm.createTodo)
    Redirect(routes.TodoController.index())
  }
}
