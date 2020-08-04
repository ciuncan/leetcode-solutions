package ciuncan
import utils._

object Main extends App {
  given DebugMode = DebugMode.On
  val arr = Array(1, 2, 3, 4)
  val lst = LazyList(1, 2, 3)
  val a   = "strink".tap("a string is") + 3.tap("hello")
    + 4.tapF("world", _ * 2)
    // + (arr.tap("HUH?"))(3)
    + lst.tap("dap")
    + (1, false, (true, "x"), LazyList('h', 'e', 'l', 'l', 'o')).tap("test nested tuple")
  println(a)
}
