import org.junit.Test
import javax.json.bind.JsonbBuilder


class TestJsonB {

    data class Dog(val name: String, val age: Int, val bitable: Boolean)

    @Test
    fun test() {
        val dog = Dog("Dog1", 4, false)

        // Create Jsonb and serialize
        val jsonb = JsonbBuilder.create()
        val result = jsonb.toJson(dog)

        println(result)

    }
}