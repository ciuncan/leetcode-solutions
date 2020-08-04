package ciuncan.leetcode.august_challenge.week1

object ValidPalindrome:

  def simplifyIterator(s: Iterator[Char]): Iterator[Char] =
    s
      .filter(c => Character.isAlphabetic(c) || Character.isDigit(c))
      .map(Character.toLowerCase)

  def isPalindrome(s: String): Boolean =
    simplifyIterator(s.iterator)
      .zip(simplifyIterator(s.reverseIterator))
      .take(s.length / 2)
      .forall({ case (start, end) => start == end })
