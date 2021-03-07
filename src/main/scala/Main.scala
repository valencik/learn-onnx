package SqueezeNet.Example

import java.nio.file.{Files, Paths}
import org.emergentorder.onnx.Tensors._
import org.emergentorder.onnx.backends.ORTOperatorBackendAll
import org.emergentorder.onnx.backends.ORTModelBackend
import org.emergentorder.compiletime._
import io.kjaer.compiletime._


val squeezenetBytes = Files.readAllBytes(Paths.get("squeezenet1.1.onnx"))
val squeezenet = new ORTModelBackend(squeezenetBytes)

//type ImageTensor = Tensor[Float, 
//                          ("ImageNetClassification",
//                          "Batch" ##: "Class" ##: TSNil,
//                          1 #: 1000 #: SNil)]

type TshapeImage = ("Image",
                           "Batch" ##: "Channel" ##: "Height" ##: "Width" ##: TSNil,
                            1 #:        3 #:          224    #:    224     #: SNil)

type TshapeClass = ("ImageNetClassification",
                           "Batch" ##: "Class"  ##: TSNil,
                            1 #:        1000 #: SNil)

def imageTens(data: Array[Float]): Tensor[Float, TshapeImage] =
  //In NCHW tensor image format
  val shape =                    1     #:     3      #:    224    #: 224     #: SNil
  val tensorShapeDenotation = "Batch" ##: "Channel" ##: "Height" ##: "Width" ##: TSNil
  Tensor(data, "Image", tensorShapeDenotation, shape)

def classify(tensor: Tensor[Float, TshapeImage]): Int =
  val out: Tensor[Float, TshapeClass] =
    squeezenet.fullModel[Float, 
                         "ImageNetClassification",
                         "Batch" ##: "Class" ##: TSNil,
                         1 #: 1000 #: SNil](Tuple(tensor))
  out.data.indices.maxBy(out.data)

@main def hello: Unit = {
    val data = Array.fill(1*3*224*224){42f}
    val x = classify(imageTens(data))
    println(s"The predicted class is ${x}")
}
