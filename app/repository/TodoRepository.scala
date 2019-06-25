package repository

import com.google.inject.ImplementedBy
import domain.{Id, Todo, TodoList}

@ImplementedBy(classOf[TodoDataSource])
trait TodoRepository {

  def findById(id: Id): Option[Todo]

  def findAll(): TodoList

  def save(todo: Todo): Unit

  def update(todo: Todo): Unit

  def delete(id: Id): Unit
}
