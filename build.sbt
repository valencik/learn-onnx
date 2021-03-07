val scala3Version = "3.0.0-M1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3-simple",
    version := "0.1.0",

    scalaVersion := scala3Version,

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
    libraryDependencies += "com.github.EmergentOrder" %% "onnx-scala-backends" % "0.8.0"

  )
