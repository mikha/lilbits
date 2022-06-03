import mill._, scalalib._

object renamer extends ScalaModule {
  def scalaVersion = "2.13.8"

  override def ivyDeps = Agg(
    ivy"org.rogach::scallop:4.1.0",
    ivy"com.typesafe.scala-logging::scala-logging:3.9.4",
  )

  override def runIvyDeps = Agg(
    ivy"ch.qos.logback:logback-classic:1.2.10",
  )
}
