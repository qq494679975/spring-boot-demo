package demo.cwd.scala.gatling

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit


object ApiGatlingTest{
  def main(args: Array[String]) {
    var api=new ApiGatlingTest();
    api.funciton();
  }
}
class ApiGatlingTest extends Simulation {

  def funciton() {

    val scn = scenario("Gatling-report")
      .repeat(1000, "n") {
        exec(
          http("systemOut-API")
            .get("http://localhost:8080/jarDemo/systemOut") //get请求地址
            .header("Content-Type", "application/json") //请求投设置
            .check(status.is(200))
        ).pause(Duration.apply(5, TimeUnit.MILLISECONDS))
      }.repeat(1000, "n") {
      exec(
        http("logOut-API")
          .get("http://localhost:8080/jarDemo/logOut")
          .check(status.is(200))
      )
    }

    setUp(scn.inject(atOnceUsers(30))).maxDuration(FiniteDuration.apply(10, "minutes"))
  }

}