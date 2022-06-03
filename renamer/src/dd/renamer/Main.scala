package dd.renamer

import com.typesafe.scalalogging.Logger

import scala.util.Try

object Main extends App {
  val logger = Logger("dd.renamer")
  val a = new Args(args)
  val regex = a.regex
  a.path
    .allFilesIn
    .foreach { p =>
      a.maybeReplacement match {
        case Some(replacement) =>
          val newName = p.replace(regex, replacement)
          Try(p.rename(newName)).fold(
            t => {
              logger.error(s"failed to rename $p to $newName", t)
            },
            changed => {
              if (changed)
                logger.info(s"$p\t - renamed to $newName")
              else
                logger.info(s"$p\t - skipped")
            }
          )
        case None =>
          val result = if (p.matches(regex)) "match" else "no match"
          logger.info(s"$p\t- $result")
      }
    }
}
