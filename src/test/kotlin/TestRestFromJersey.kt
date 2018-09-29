import org.junit.After
import org.junit.Before
import org.junit.Test
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.WebTarget


class TestRestFromJersey {


    private var target: WebTarget? = null

    data class Greeting(var id: Long = 0, var content: String = "")

    @Before
    @Throws(Exception::class)
    fun setUp() {

        val c = ClientBuilder.newClient()
        target = c.target("http://localhost:8080/greeting")
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {

    }

    @Test
    fun test() {
        val responseMsg = target!!.path("hello").request().get(String::class.java)
        println(responseMsg)
    }

    @Test
    fun test2() {
        val responseMsg = target!!.path("hello").request().get(Greeting::class.java)
        //  val responseMsg = target!!.path("hello").request(MediaType.APPLICATION_JSON_TYPE).get(Greeting::class.java)
        println(responseMsg)
    }
}