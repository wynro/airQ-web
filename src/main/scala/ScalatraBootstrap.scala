import com.team103.app._
import com.team103.config._
import javax.servlet.ServletContext
import org.scalatra._


class ScalatraBootstrap extends LifeCycle with DatabaseInit {

  override def init(context: ServletContext) {
    configureDb()
    context.mount(new InsertDataServlet, "/insert")
  }

  override def destroy(context:ServletContext) {
    closeDbConnection()
  }
}
