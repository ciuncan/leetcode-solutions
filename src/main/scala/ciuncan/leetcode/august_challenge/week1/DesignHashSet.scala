package ciuncan.leetcode.august_challenge.week1

import MyHashSet._

class MyHashSet(private var _capacity: Int = INITIAL_CAPACITY)
    extends collection.mutable.AbstractSet[Int]:

  private[week1] var _arr = Array.fill[Int](_capacity)(UNOCCUPIED)
  private var _size       = 0

  override def add(v: Int): Boolean =
    findIndex(v) match
      case Left(designatedIndex) =>
        if !addEnsuringCapacity(v) then
          _arr(designatedIndex) = v
          _size += 1
        true
      case Right(foundIndex)     =>
        false

  override def remove(v: Int): Boolean =
    // see https://en.wikipedia.org/wiki/Open_addressing#Example_pseudocode
    findIndex(v) match
      case Left(designatedIndex) => false
      case Right(foundIndex)     =>
        _size -= 1
        var i    = foundIndex
        var j    = i
        val loop = new scala.util.control.Breaks
        loop.breakable {
          while true do
            _arr(i) = UNOCCUPIED
            var r2Cont = true
            while r2Cont do
              j = (j + 1) % _capacity
              if _arr(j) == UNOCCUPIED then
                loop.break()
              val k = _arr(j).hashCode % _capacity
              if i <= j then
                r2Cont = i < k && k <= j
              else
                r2Cont = i < k || k <= j

            _arr(i) = _arr(j)
            i = j
        }
        true

  def capacity: Int                      = _capacity
  override def size: Int                 = _size
  override def contains(v: Int): Boolean = findIndex(v).isRight
  override def addOne(elem: Int): this.type =
    add(elem)
    this
  override def subtractOne(elem: Int): this.type =
    remove(elem)
    this
  override def clear(): Unit =
    _size = 0
    _arr.indices.foreach(_arr(_) = UNOCCUPIED)
  override def iterator: Iterator[Int]   = _arr.iterator.filter(_ != UNOCCUPIED)

  private def findIndex(v: Int): Either[DesignatedIndex, FoundIndex] =
    var nextIndex = v.hashCode % _capacity
    while _arr(nextIndex) != UNOCCUPIED && _arr(nextIndex) != v do
      nextIndex = (nextIndex + 1) % _capacity
    if _arr(nextIndex) == UNOCCUPIED then
      Left(nextIndex)
    else
      Right(nextIndex)

  private def addEnsuringCapacity(v: Int): Boolean =
    if size >= _capacity * LOAD_FACTOR && size < MAX_CAPACITY then
      val newAllocation = new MyHashSet((_capacity * 2).min(MAX_CAPACITY))
      for item <- this do
        newAllocation.add(item)
      newAllocation.add(v)
      _arr = newAllocation._arr
      _size = newAllocation._size
      _capacity = newAllocation._capacity
      true
    else
      false

object MyHashSet:
  val INITIAL_CAPACITY = 1009
  val MAX_CAPACITY     = 1000000
  val LOAD_FACTOR      = 0.75

  val UNOCCUPIED = -1

  type FoundIndex      = Int
  type DesignatedIndex = Int
