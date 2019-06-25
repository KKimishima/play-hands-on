package repository

import anorm._
import domain.{Id, Todo, TodoList}
import javax.inject.{Inject, Singleton}

import scala.language.postfixOps

@Singleton
class TodoDataSource @Inject()(private val dbAdapter: DbAdapter) extends TodoRepository {
  override def findById(id: Id): Option[Todo] = {
    dbAdapter.adapter.withConnection { implicit connection =>
      SQL(
        """
          select * from todo where id = {id}
        """
      ).on(
        'id -> id.value
      ).as(TodoMapper().singleOpt)
    }
  }

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

  override def update(todo: Todo): Unit = {
    dbAdapter.adapter.withConnection { implicit connection =>
      SQL(
        """
        update todo
        set name = {name}
        where id = {id}
      """
      ).on(
        'name -> todo.name.value,
        'id -> todo.id.getOrElse(Id(0)).value
      ).executeUpdate()
    }
  }


  override def delete(id: Id): Unit = {
    dbAdapter.adapter.withConnection { implicit connection =>
      SQL(
        """
          delete from todo where id = {id}
        """
      ).on(
        'id -> id.value
      ).executeUpdate()
    }
  }
}


