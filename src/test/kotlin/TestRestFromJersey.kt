import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.concurrent.CompletableFuture
import java.util.function.Supplier
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget


class TestRestFromJersey {


    private lateinit var target: WebTarget

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
        val responseMsg = target.path("hello").request().get(String::class.java)
        println(responseMsg)
    }

    @Test
    fun test2() {
        val responseMsg = target.path("hello").request().get(Greeting::class.java)
        //  val responseMsg = target!!.path("hello").request(MediaType.APPLICATION_JSON_TYPE).get(Greeting::class.java)
        println(responseMsg)
    }

    @Test
    fun test3() {
        val response = target.path("message").request().post(Entity.text("Hello"))

        println(response)
    }

    @Test
    fun test4() {
        val supplier: Supplier<Greeting> = Supplier { target.path("hello").request().get(Greeting::class.java)}

        CompletableFuture.supplyAsync(supplier).thenAccept(this::consume).get()
        //  val responseMsg = target!!.path("hello").request(MediaType.APPLICATION_JSON_TYPE).get(Greeting::class.java)
//        println(responseMsg)
    }

    fun consume(greeting: Greeting){
        target.path("message").request().post(Entity.json(greeting))
    }
}