package repository

import anorm.SqlParser.{int, str}
import anorm.{RowParser, ~}
import domain.{Id, Name, Todo}

object TodoMapper {
  def apply(): RowParser[Todo] = {
    (int("id") ~ str("name")).map {
      case id ~ name => Todo(Some(Id(id)), Name(name))
    }
  }
}
