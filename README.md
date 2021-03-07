## Playing with onnx-scala

### Usage

Publish `onnx-scala` `0.9.0` locally:

```sh
git clone https://github.com/EmergentOrder/onnx-scala.git
cd onnx-scala
sbt publishLocal
```

Get the squeezenet model:

```sh
curl -O "https://s3.amazonaws.com/onnx-model-zoo/squeezenet/squeezenet1.1/squeezenet1.1.onnx"
```

Run the example:

```sh
sbt run
```

You should see the follow:

```
...
[info] running SqueezeNet.Example.hello
The predicted class is 418
```
