package ciuncan.utils

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DebuggableSpec extends AnyFlatSpec with Matchers:
  import ciuncan.utils.Debuggable.{given _}

  "Debuggable" should "return double-quoted version of given strings" in {
    "ceyhun".debug should be("\"ceyhun\"")
  }
  it should "return single-quoted version of given char" in {
    'o'.debug should be("'o'")
  }
  it should "return recursively debuggable version of each element for Arrays" in {
    Array("ceyhun", "hello").debug should be("ArraySeq$ofRef[\"ceyhun\", \"hello\"]")
  }
  it should "return recursively debuggable version of each element for any iterable" in {
    Seq("ceyhun", "hello").debug should be("$colon$colon[\"ceyhun\", \"hello\"]")
    Set("ceyhun", "hello").debug should be("Set$Set2[\"ceyhun\", \"hello\"]")
    LazyList("ceyhun", "hello").debug should be("LazyList[\"ceyhun\", \"hello\"]")
  }
  it should "return recursively debuggable version of each element for any tuple" in {
    (1, false, (true, "x"), LazyList('h','e','l','l','o')).debug should be(
      "1 *: false *: (true *: \"x\" *: ()) *: LazyList['h', 'e', 'l', 'l', 'o'] *: ()"
    )
  }
  it should "return toString for everything else" in {
    1.debug should be("1")
    false.debug should be("false")
    (new Object).debug should startWith("java.lang.Object@")
  }