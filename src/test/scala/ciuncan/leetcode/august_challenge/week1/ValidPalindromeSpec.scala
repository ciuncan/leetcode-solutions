package ciuncan.leetcode.august_challenge.week1

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ValidPalindromeSpec extends AnyFlatSpec with Matchers:
  import ValidPalindrome._
  "validPalindrome" should "return true for strings that are same as reverse" in {
    isPalindrome("amanaplanacanalpanama") should be(true)
    isPalindrome("bananab") should be(true)
  }
  it should "return true for strings that are same as reverse regardless of case" in {
    isPalindrome("AManAPlanACanalPanama") should be(true)
    isPalindrome("bAnAnaB") should be(true)
  }
  it should "return true for strings that are same as reverse regardless of non-alphanumeric characters" in {
    isPalindrome("A Man, A Plan, A Canal, Panama!") should be(true)
    isPalindrome("!!! b-A-n-A-n-a-B !!!") should be(true)
  }
  it should "return false for any other string" in {
    val random = new scala.util.Random
    isPalindrome(random.alphanumeric.take(100).mkString) should be(false)
  }
