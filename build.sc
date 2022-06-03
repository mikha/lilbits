import mill._, scalalib._
import $file.dependencies

object renamer extends ScalaModule {
  def scalaVersion = "2.13.8"

  override def ivyDeps = Agg(
    dependencies.scallop,
    dependencies.logging,
  )

  override def runIvyDeps = Agg(
    dependencies.logback,
  )
}
