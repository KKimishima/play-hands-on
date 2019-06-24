package repository

import anorm.SqlParser._
import anorm._
import domain.{Id, Name, Todo, TodoList}
import javax.inject.{Inject, Singleton}
import scala.language.postfixOps

@Singleton
class TodoDataSource @Inject()(private val dbAdapter: DbAdapter) extends TodoRepository {
  override def findById(id: String): Option[Todo] = ???

  override def findAll(): TodoList = {
    val todo = dbAdapter.adapter.withConnection { implicit connection =>
      SQL(
        """
          select * from todo
        """
      ).as(TodoMapper().*)
    }
    TodoList(todo)
  }

  override def save(todo: Todo): Unit = {
    dbAdapter.adapter.withConnection { implicit connection =>
      SQL(
        """
        insert into todo(name) values ({name})
        """
      ).on(
        'name -> todo.name.value
      ).executeUpdate()
    }
  }
}


