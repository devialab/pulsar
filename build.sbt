name := "pulsar"

version := "0.0.1"

scalaVersion := "2.11.5"

val AkkaVersion = "2.3.7"

resolvers ++= Seq(
  Classpaths.sbtPluginReleases
  ,"Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
  ,"Sonatype (releases)" at "https://oss.sonatype.org/content/repositories/releases/"
  ,"mDialog releases" at "http://mdialog.github.io/releases/"
  , Resolver.url("alexdeleon-repo", url("http://maven.alexdeleon.name/snapshot/"))(Resolver.ivyStylePatterns))

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.0.1")

addSbtPlugin("org.scoverage" % "sbt-coveralls" % "1.0.0.BETA1")

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % AkkaVersion,
  "com.typesafe.akka"   %%  "akka-testkit"  % AkkaVersion  % "test",
  "org.clapper" % "grizzled-slf4j_2.11" % "1.0.2",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "com.mdialog" %% "scala-zeromq" % "1.1.1",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "org.zeromq" % "zeromq-scala-binding_2.11.2" % "0.1.0-SNAPSHOT")