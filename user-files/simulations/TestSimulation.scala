import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class TestSimulation extends Simulation {

  private val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (X11; Linux x86_64; rv:104.0) Gecko/20100101 Firefox/104.0")

  private val scn = scenario("TestSimulation")
    .exec(
      http("books")
        .get("/books")
    )

  setUp(scn.inject(
    rampUsersPerSec(0).to(3000).during(1.minutes)
  )).protocols(httpProtocol)
}

