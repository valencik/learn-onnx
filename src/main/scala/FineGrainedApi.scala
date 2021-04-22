package FineGrainedApi.Example

import org.emergentorder.onnx.Tensors._
import org.emergentorder.onnx.backends.ORTOperatorBackendAll
import org.emergentorder.compiletime._
import io.kjaer.compiletime._

@main def fine: Unit =
  val onnxBackend = new ORTOperatorBackendAll()
  val data = Array.fill(1*3*224*224){scala.util.Random.nextFloat}
  val shape =                    1     #:     3      #:    224    #: 224     #: SNil
  val tensorShapeDenotation = "Batch" ##: "Channel" ##: "Height" ##: "Width" ##: TSNil
  val tensor = Tensor(data, "Image", tensorShapeDenotation, shape)

  //val x = onnxBackend.GlobalAveragePoolV1("avgPool", X=tensor)
  //  ^^^ No singleton value available for Nothing.

  // Unfortunately we have to fully spell out the type?
  val x = onnxBackend.GlobalAveragePoolV1[Float,
                                          1, 3, 224, 224,
                                          "Image", "Batch" ##: "Channel" ##: "Height" ##: "Width" ##: TSNil,
                                          1     #:     3      #:    224    #: 224     #: SNil,
                                          "Image", "Batch" ##: "Channel" ##: "Height" ##: "Width" ##: TSNil,
                                          1     #:     3      #:    1    #: 1     #: SNil]("avgPool", X=tensor)
  println(x.data.toList)
