package Roberta.Example

import java.nio.file.{Files, Paths}
import org.emergentorder.onnx.Tensors._
import org.emergentorder.onnx.backends.ORTOperatorBackendAll
import org.emergentorder.onnx.backends.ORTModelBackend
import org.emergentorder.compiletime._
import io.kjaer.compiletime._


val robertaBytes = Files.readAllBytes(Paths.get("roberta-sequence-classification-9.onnx"))
val roberta = new ORTModelBackend(robertaBytes)

type TshapeInput = ("Input",
                           "Batch" ##: "Length" ##: TSNil,
                            1 #:        5 #:       SNil)

type TshapeClass = ("RobertaClassification",
                           "Batch" ##: "Class"  ##: TSNil,
                            1 #:        2 #: SNil)

def inputTensor(data: Array[Long]): Tensor[Long, TshapeInput] =
  val shape =                    1     #:     5    #: SNil
  val tensorShapeDenotation = "Batch" ##: "Length" ##: TSNil
  Tensor(data, "Input", tensorShapeDenotation, shape)

def classify(tensor: Tensor[Long, TshapeInput]) =
  val out: Tensor[Float, TshapeClass] =
    roberta.fullModel[Float, 
                         "RobertaClassification",
                         "Batch" ##: "Class" ##: TSNil,
                         1 #: 2 #: SNil](Tuple(tensor))
  out.data.indices.maxBy(out.data)

@main def robertaHello: Unit = {
    val data = Array(0L, 31414L, 232L, 328L, 2L) // "Hello world"
    val x = classify(inputTensor(data))
    println(s"The predicted class is ${x}")
}
