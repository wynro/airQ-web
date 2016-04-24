import com.team103.app._
import com.team103.config._
import javax.servlet.ServletContext
import org.squeryl.Session
import org.squeryl.SessionFactory
import org.scalatra._


class ScalatraBootstrap extends LifeCycle with DatabaseInit {

  override def init(context: ServletContext) {
    configureDb()
    val session = SessionFactory.newSession
    session.bindToCurrentThread
    try {
      Repository.create
    } catch {
      case e:Exception => {}
    }

    session.close
    session.unbindFromCurrentThread
    context.mount(new InsertDataServlet, "/insert")
  }

  override def destroy(context:ServletContext) {
    closeDbConnection()
  }
}
