name := "pulsar"

version := "0.0.1"

scalaVersion := "2.11.5"

val AkkaVersion = "2.3.7"

resolvers ++= Seq(
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
  ,"Sonatype (releases)" at "https://oss.sonatype.org/content/repositories/releases/"
  ,"mDialog releases" at "http://mdialog.github.io/releases/"
  ,"Devialab Repository" at "http://artifacts.devialab.com/artifactory/libs")

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % AkkaVersion,
  "com.typesafe.akka"   %%  "akka-testkit"  % AkkaVersion  % "test",
  "org.clapper" % "grizzled-slf4j_2.11" % "1.0.2",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "com.mdialog" %% "scala-zeromq" % "1.1.1",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "org.zeromq" % "jeromq" % "0.3.5")


lazy val `pulsar-client` = project

//publish settings
publishMavenStyle := true

publishTo := {
  val artifactory = "http://artifacts.devialab.com/artifactory/"
  if (isSnapshot.value)
    Some("snapshots" at artifactory + "devialab-snapshot;build.timestamp=" + new java.util.Date().getTime)
  else
    Some("releases"  at artifactory + "devialab-release")
}