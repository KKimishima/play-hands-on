package domain

import scala.collection.Seq

case class Name(value: String)

case class Id(value: Int)

case class Todo(id: Option[Id], name: Name)

case class TodoList(todo: Seq[Todo])

