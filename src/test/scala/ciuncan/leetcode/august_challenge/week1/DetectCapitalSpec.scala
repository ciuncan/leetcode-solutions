package ciuncan.leetcode.august_challenge.week1

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DetectCapitalSpec extends AnyFlatSpec with Matchers:
  import DetectCapital._
  "detectCapitalUse" should "return true for strings with length less than 2" in {
    detectCapitalUse("") should be(true)
    detectCapitalUse("I") should be(true)
    detectCapitalUse("u") should be(true)
  }
  it should "return true for strings with all uppercase characters" in {
    detectCapitalUse("USA") should be(true)
  }
  it should "return true for strings with only first character uppercase" in {
    detectCapitalUse("Ceyhun") should be(true)
  }
  it should "return true for strings with lowercase characters" in {
    detectCapitalUse("hello") should be(true)
  }
  it should "return false for all other strings" in {
    detectCapitalUse("aA") should be(false)
    detectCapitalUse("AaA") should be(false)
    detectCapitalUse("AAa") should be(false)
  }
