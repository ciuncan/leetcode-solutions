package ciuncan.leetcode.august_challenge.week1

object DetectCapital:
  def detectCapitalUse(word: String): Boolean =
    val isUp           = (x: Char) => Character.isUpperCase(x)
    val isLo           = (x: Char) => Character.isLowerCase(x)
    val firstTwoChars  = word.iterator.take(2).to(List)
    val remainingChars = word.iterator.drop(2)
    firstTwoChars match
      case Nil                                      => true
      case _ :: Nil                                 => true
      case List(fst, snd) if isUp(fst) && isUp(snd) => remainingChars.forall(isUp)
      case List(fst, snd) if isUp(fst) && isLo(snd) => remainingChars.forall(isLo)
      case List(fst, snd) if isLo(fst) && isLo(snd) => remainingChars.forall(isLo)
      case _                                        => false
