name := "pulsar"

version := "0.0.1"

scalaVersion := "2.11.5"

resolvers ++= Seq(
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
  ,"Sonatype (releases)" at "https://oss.sonatype.org/content/repositories/releases/"
  ,"mDialog releases" at "http://mdialog.github.io/releases/")

libraryDependencies ++= Seq(
  "org.clapper" % "grizzled-slf4j_2.11" % "1.0.2"
  ,"ch.qos.logback" % "logback-classic" % "1.1.2"
  ,"com.typesafe.akka" %% "akka-actor" % "2.3.7"
  ,"org.zeromq" % "zeromq-scala-binding_2.11.2" % "0.1.0-SNAPSHOT" % "provided"
  , "com.mdialog" %% "scala-zeromq" % "1.1.1"
  , "org.scalatest" %% "scalatest" % "2.2.1" % "test")