package ciuncan.leetcode.august_challenge.week1

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PowerOfFourSpec extends AnyFlatSpec with Matchers {
  import PowerOfFour._
  "isPowerOfFour" should "return true for positive numbers that are power of 4" in {
    isPowerOfFour(1) should be(true)
    isPowerOfFour(4) should be(true)
    isPowerOfFour(16) should be(true)
  }
  it should "return true for negative numbers that are power of 4" in {
    isPowerOfFour(-1) should be(true)
    isPowerOfFour(-4) should be(true)
    isPowerOfFour(-16) should be(true)
  }
  it should "return false for all other numbers" in {
    isPowerOfFour(0) should be(false)
    isPowerOfFour(-3) should be(false)
    isPowerOfFour(-5) should be(false)
    isPowerOfFour(17) should be(false)
    isPowerOfFour(Int.MinValue) should be(false)
    isPowerOfFour(Int.MaxValue) should be(false)
  }
}
