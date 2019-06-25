package controllers

import domain.Id
import javax.inject.{Inject, Singleton}
import play.api.mvc.{AnyContent, MessagesAbstractController, MessagesControllerComponents, MessagesRequest}
import service.TodoService

@Singleton
class TodoController @Inject()(mcc: MessagesControllerComponents)(val service: TodoService) extends MessagesAbstractController(mcc) {
  def index = Action { implicit request: MessagesRequest[AnyContent] =>
    val todoList = service.getAll
    Ok(views.html.index(todoList))
  }

  def show(id: Int) = Action { implicit request: MessagesRequest[AnyContent] =>
    service.getOne(Id(id)).map { s =>
      Ok(views.html.show(s))
    }.getOrElse(NotFound)
  }

  def neW = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.neW(TodoForm()))
  }

  def create = Action { implicit request: MessagesRequest[AnyContent] =>
    service.save(TodoForm.createTodo)
    Redirect(routes.TodoController.index())
  }

  def edit(id: Int) = Action { implicit request: MessagesRequest[AnyContent] =>
    service.getOne(Id(id)).map { s =>
      Ok(views.html.edit(TodoForm().fill(s)))
    }.getOrElse(NotFound)
  }

  def update = Action { implicit request: MessagesRequest[AnyContent] =>
    val todo = TodoForm.createTodo
    service.update(todo)
    Redirect(routes.TodoController.show(todo.id.getOrElse(Id(0)).value))
  }

  def delete(id: Int) = Action { implicit request: MessagesRequest[AnyContent] =>
    service.delete(Id(id))
    Redirect(routes.TodoController.index())
  }
}
