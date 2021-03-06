name := "Simple Project"

version := "1.0"

scalaVersion := "2.10.3"

libraryDependencies += "org.apache.spark" %% "spark-core" % "0.9.0-incubating"

libraryDependencies += "org.apache.spark" %% "spark-streaming" % "0.9.0-incubating"

resolvers += "Akka Repository" at "http://repo.akka.io/releases/"
