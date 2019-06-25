package service

import domain._
import javax.inject.{Inject, Singleton}
import repository.TodoRepository

@Singleton
class TodoService @Inject()(repository: TodoRepository) {
  def getOne(id: Id): Option[Todo] = repository.findById(id)

  def getAll: TodoList = repository.findAll()

  def save(todo: Todo): Unit = repository.save(todo)

  def update(todo: Todo): Unit = repository.update(todo)

  def delete(id: Id): Unit = repository.delete(id)
}
