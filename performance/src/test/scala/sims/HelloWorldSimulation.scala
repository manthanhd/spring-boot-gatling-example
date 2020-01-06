package sims;

import io.gatling.commons.stats.KO
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.jdbc.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._  // used for specifying duration unit, eg "5 second"

class HelloWorldSimulation extends Simulation {

  var isOk = true

  val scn: ScenarioBuilder = scenario("Hello World")
    .exec(session => {
      if (!isOk) {
        val retSession: Session = session.copy()
        retSession.exit()
        retSession
      } else {
        session
      }
    })
    .exec(
      http("GET /greeting")
        .get("http://localhost:8080/greeting")
        .check(status.is(200))
    )
    .exec( session => {
      if (session.status == KO) {
        println("not ok")
        isOk = false
        val retSession: Session = session.copy()
        retSession.exit()
        retSession
      } else {
        session
      }
    })

  setUp(scn.inject(
    atOnceUsers(100),
    rampUsersPerSec(10) to 1000 during (30 seconds)
  ).protocols(http))
}
