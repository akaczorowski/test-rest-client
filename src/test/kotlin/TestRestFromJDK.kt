import org.junit.Test
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class TestRestFromJDK {

    @Test
    fun test() {

        try {

            val url = URL("http://localhost:8080/greeting/hello")
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.setRequestProperty("Accept", "application/json")

            if (conn.responseCode != 200) {
                throw RuntimeException("Failed : HTTP error code : " + conn.responseCode)
            }

            val br = BufferedReader(InputStreamReader((conn.inputStream)))

            println("Output from Server: \n")

            br.forEachLine { System.out.println(it) }

            conn.disconnect()

            System.out.println("###############################\n");

        } catch (e: MalformedURLException) {
            e.printStackTrace()

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

}