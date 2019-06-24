package repository

import com.google.inject.ImplementedBy
import domain.{Todo, TodoList}

@ImplementedBy(classOf[TodoDataSource])
trait TodoRepository {
  def findById(id: String): Option[Todo]

  def findAll(): TodoList

  def save(todo: Todo): Unit
}
