package dd.renamer

import dd.renamer.Args._
import org.rogach.scallop._

import java.nio.file.Path
import java.util.regex.{Pattern, PatternSyntaxException}

class Args(args: Seq[String]) extends ScallopConf(args) {
  private val _path: ScallopOption[Path] =
    opt[Path](
      required = true,
      name = "path",
      descr = "Path to rename the files down from",
    )
  private val _regex: ScallopOption[Pattern] =
    opt[Pattern](
      required = true,
      name = "regex",
      descr = "Pattern file names are matched against, can contain capture groups"
    )
  private val _replacement: ScallopOption[String] =
    opt[String](
      required = false,
      name = "replacement",
      short = 'e',
      descr = "Replacement for file names, can refer to capture groups from the regex. " +
        "If not specified no renaming is done, just matching of the file names"
    )
  verify()

  def path: Path = _path()

  def regex: Pattern = _regex()

  def maybeReplacement: Option[String] = _replacement.toOption
}

object Args {
  implicit val regexValueConverter: ValueConverter[Pattern] =
    singleArgConverter[Pattern](
      Pattern.compile,
      {
        case e: PatternSyntaxException => Left(s"Bad regex: ${e.getMessage}")
      }
    )
}