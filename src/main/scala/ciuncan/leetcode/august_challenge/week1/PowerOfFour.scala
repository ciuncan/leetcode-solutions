package ciuncan.leetcode.august_challenge.week1

object PowerOfFour {
  def isPowerOfFour(num: Int): Boolean = {
    val positiveNum = num.abs
    Integer.bitCount(positiveNum) == 1 &&
    Integer.numberOfTrailingZeros(positiveNum) % 2 == 0
  }
}
