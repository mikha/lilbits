package dd

import java.nio.file.{Files, Path}
import java.util.regex.Pattern
import scala.jdk.StreamConverters._

package object renamer {
  implicit class RichPath(val p: Path) extends AnyVal {
    def allFilesIn: LazyList[Path] =
      Files
        .walk(p)
        .toScala(LazyList)
        .filter(_.toFile.isFile)

    def matches(regex: Pattern): Boolean =
      regex.matcher(p.getFileName.toString).matches()

    def replace(regex: Pattern, replaceWith: String): String =
      regex.matcher(p.getFileName.toString).replaceAll(replaceWith)

    def rename(newName: String): Boolean = {
      if (p.getFileName.toString.equalsIgnoreCase(newName))
        false
      else {
        Files.move(p, p.resolveSibling(newName))
        true
      }
    }
  }
}
