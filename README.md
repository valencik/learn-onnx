## Playing with onnx-scala

### Usage

Publish `onnx-scala` `0.9.0` locally:

```sh
git clone https://github.com/EmergentOrder/onnx-scala.git
cd onnx-scala
sbt publishLocal
```

Get the models:

```sh
# 4.7mb
curl -O "https://s3.amazonaws.com/onnx-model-zoo/squeezenet/squeezenet1.1/squeezenet1.1.onnx"

# 476mb
curl -O "https://github.com/onnx/models/raw/master/text/machine_comprehension/roberta/model/roberta-sequence-classification-9.onnx"
```

Run and choose an example:

```sh
sbt run
```

You should see something like the follow:

```
...
[info] running SqueezeNet.Example.hello
The predicted class is 418
```

or

```
...
[info] running Roberta.Example.robertaHello
The predicted class is 1
```
