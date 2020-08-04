package ciuncan.leetcode.august_challenge.week1

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DesignHashSetSpec extends AnyFlatSpec with Matchers:
  "MyHashSet" should "add distinct numbers, size increase and iterator should return them" in {
    val hashSet = new MyHashSet(4)
    hashSet.add(1)
    hashSet.add(2)
    hashSet.add(5)

    hashSet.size should be(3)
    hashSet._arr should be(Array(-1, 1, 2, 5))
    hashSet.to(Set) should be(Set(1, 2, 5))
  }
  it should "add existing numbers without increasing size" in {
    val hashSet = new MyHashSet(4)
    hashSet.add(1)
    hashSet.add(2)
    hashSet.add(1)
    hashSet.add(5)

    hashSet.size should be(3)
    hashSet._arr should be(Array(-1, 1, 2, 5))
    hashSet.to(Set) should be(Set(1, 2, 5))
  }
  it should "double the capacity if size is greater than LOAD_FACTOR * capacity" in {
    val hashSet = new MyHashSet(4)
    hashSet.add(1)
    hashSet.add(2)
    hashSet.add(5)
    hashSet.add(9)

    hashSet.size should be(4)
    hashSet.capacity should be(8)
    hashSet._arr should be(Array(-1, 1, 2, 9, -1, 5, -1, -1))
    hashSet.to(Set) should be(Set(1, 2, 9, 5))
  }
  it should "not change when removing non-existing items" in {
    val hashSet = new MyHashSet(4)
    hashSet.add(1)
    hashSet.add(2)
    hashSet.add(5)
    hashSet.add(9)

    hashSet.remove(10)
    hashSet.remove(0)
    hashSet.remove(3)
    hashSet.remove(4)
    hashSet.remove(7)

    hashSet.size should be(4)
    hashSet.capacity should be(8)
    hashSet._arr should be(Array(-1, 1, 2, 9, -1, 5, -1, -1))
    hashSet.to(Set) should be(Set(1, 2, 9, 5))
  }
  it should "remove existing items" in {
    val hashSet = new MyHashSet(4)
    hashSet.add(1)
    hashSet.add(2)
    hashSet.add(5)
    hashSet.add(9)

    hashSet.remove(1)
    hashSet.remove(2)
    hashSet.remove(5)
    hashSet.remove(9)

    hashSet.size should be(0)
    hashSet.capacity should be(8)
    hashSet._arr should be(Array(-1, -1, -1, -1, -1, -1, -1, -1))
    hashSet.to(Set) should be(Set.empty)
  }
  it should "act like any other set" in {
    val random  = new scala.util.Random
    val hashSet = new MyHashSet
    val refImpl = new scala.collection.mutable.HashSet[Int]

    var opCount      = 0L
    val MAX_OP_COUNT = 1000L
    while hashSet.size < MyHashSet.MAX_CAPACITY && opCount < MAX_OP_COUNT do
      opCount += 1
      if random.nextBoolean || random.nextBoolean then
        val value = random.nextInt.abs
        hashSet.add(value)
        refImpl.add(value)

        hashSet should be(refImpl)
      else if random.nextBoolean then
        val value = random.between(0, refImpl.size * 2 + 1)
        if refImpl.contains(value) then
          hashSet should contain(value)
        else
          hashSet should not contain value
      else
        val value = random.between(0, refImpl.size * 2 + 1)
        hashSet.remove(value)
        refImpl.remove(value)

        hashSet should be(refImpl)
  }
  it should "pass the failing leetcode test" in {
    val hashSet = new MyHashSet
    val refImpl = new scala.collection.mutable.HashSet[Int]

    val resourcePath =
      s"${getClass.getPackage.getName.replace(".", "/")}/failing_test_case1.txt"

    val List(commandsJson, argumentsJson) = io.Source
      .fromInputStream(
        getClass.getClassLoader.getResourceAsStream(resourcePath)
      )
      .getLines
      .map(ujson.read(_): ujson.Value)
      .map(_.arr.toSeq.tail)
      .toList

    val commands    = commandsJson.map(_.str)
    val commandArgs = argumentsJson.map(_.arr(0).num.toInt)

    commands
      .to(LazyList)
      .zip(commandArgs)
      .map({
        case (c @ "add", arg)      => (c, arg, hashSet.add(arg), refImpl.add(arg))
        case (c @ "remove", arg)   => (c, arg, hashSet.remove(arg), refImpl.remove(arg))
        case (c @ "contains", arg) => (c, arg, hashSet.contains(arg), refImpl.contains(arg))
        case ca                    => throw new IllegalArgumentException("Illegal command-argument: " + ca)
      })
      .collectFirst({
        case (command, arg, lRes, rRes) if lRes != rRes =>
          fail(
            s"""hashSet.$command($arg) = $lRes
            |wasn't equal to
            |refImpl.$command($arg) = $rRes
            |
            |hashSet = $hashSet
            |refImpl = $refImpl
          """.stripMargin
          )
      }) should be(None)
  }

