name := "Peppper"

version := "1.0"

scalaVersion := "2.11.8"

retrieveManaged := true

//resolvers += "Jboss Mven Repository" at "http://repository.jboss.org/nexus/content/groups/public/"
libraryDependencies ++= Seq(//"org.msgpack" % "msgpack-rpc" % "0.7.0",
  "ca.pjer" % "chatter-bot-api" % "1.4.2")

mainClass in assembly := Some("com.github.haretaro.peppper.Main")

