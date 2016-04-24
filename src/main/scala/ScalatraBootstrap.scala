import _root_.akka.actor.{Props,ActorSystem}
import com.team103.app._
import com.team103.config._
import com.team103.config.ScheduledConfig
import com.team103.services._
import javax.servlet.ServletContext
import org.squeryl.Session
import org.squeryl.SessionFactory
import org.scalatra._
import scala.concurrent.ExecutionContext

/**
  * @author Team 103
  * @version 0.0.1
  * Initializer for container
  */
class ScalatraBootstrap extends LifeCycle with DatabaseInit {

  val system = ActorSystem("airqSystem")
  val cartoDBactor = system.actorOf(Props[cartoDBactor])
  val iPactor = system.actorOf(Props[IPactor])
  protected implicit def executor: ExecutionContext = system.dispatcher

  /**
    * Initialize DB configuration, create table Responses if it does not exist
    * and mount servlet
    */
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
    context.mount(new MapServlet, "/map")
  }

  /** Close database connnection */
  override def destroy(context:ServletContext) {
    closeDbConnection()
  }

  /** Configures the scheduled tasks */
  protected def configureSchedulers = {
    // Data scheduling
    system.scheduler.schedule(ScheduledConfig.CHECK_DATA_DELAY,
      ScheduledConfig.CHECK_DATA_INTERVAL, cartoDBactor,
      ScheduledConfig.CHECK_DATA_MESSAGE)
    // IP scheduling
    system.scheduler.schedule(ScheduledConfig.CHECK_IP_DELAY,
      ScheduledConfig.CHECK_IP_INTERVAL, iPactor,
      ScheduledConfig.CHECK_IP_MESSAGE)
  }
}
