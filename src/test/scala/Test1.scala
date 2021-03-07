import SqueezeNet.Example._
import org.junit.Test
import org.junit.Assert._

class Test1 {
  @Test def t1(): Unit = {
    val data = Array.fill(1*3*224*224){42f}
    assertEquals(418, classify(imageTens(data)))
  }
}
