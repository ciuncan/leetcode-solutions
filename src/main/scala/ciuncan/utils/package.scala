package ciuncan
package object utils {
  implicit class Tapper[T](private val t: T) extends AnyVal {
    def tap(label: String = "", xForm: T => String = _.toString): T = {
      println(s"${if (label.isEmpty) "" else s"$label: "}${xForm(t)}")
      t
    }
  }
}
