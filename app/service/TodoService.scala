package service

import domain._
import javax.inject.{Inject, Singleton}
import repository.{TodoDataSource, TodoRepository}

@Singleton
class TodoService @Inject()(repository: TodoRepository) {
  def getAll: TodoList = repository.findAll()

  def save(todo: Todo): Unit = repository.save(todo)
}
