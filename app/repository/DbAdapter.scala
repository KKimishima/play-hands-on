package repository

import javax.inject.{Inject, Singleton}
import play.api.db.{DBApi, Database}

@Singleton
class DbAdapter @Inject()(private val dBApi: DBApi) {
  val adapter: Database = dBApi.database("default")
}
