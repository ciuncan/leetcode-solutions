package ciuncan.utils

trait Debuggable[-T] {
  def (t: T).debug: String
}

object Debuggable {
  given defaultDebuggable[T] as Debuggable[T] {
    def (t: T).debug: String = t.toString
  }

  given charDebuggable as Debuggable[Char] {
    def (c: Char).debug = s"'$c'"
  }

  given stringDebuggable as Debuggable[String] {
    def (s: String).debug = s""""$s""""
  }
  
  given iterableDebuggable [T: Debuggable] as Debuggable[Iterable[T]] {
    def (iterable: Iterable[T]).debug = iterable.iterator
      .map(_.debug)
      .mkString(iterable.getClass.simpleName + "[", ", ", "]")
  }

  given arrayDebuggable [T: Debuggable] as Debuggable[Array[T]] {
    def (arr: Array[T]).debug = arr.toIterable.debug
  }
  
  given emptyTupleDebuggable as Debuggable[Unit] {
    def (arr: Unit).debug = "()"
  }
  
  given tupleDebuggable [HD: Debuggable, TL <: Tuple : Debuggable] as Debuggable[HD *: TL] {
    def (tup: HD *: TL).debug =
      val headString = if tup.head.isInstanceOf[NonEmptyTuple] then
        s"(${tup.head.debug})"
      else
        tup.head.debug
      s"$headString *: ${tup.tail.debug}"
  }
  
  private def (c: Class[_]).simpleName: String =
    c.getName.substring(c.getName.lastIndexOf('.') + 1)

}