package labw01

import munit.FunSuite

class SetSuite extends FunSuite {
  test("alter on Set(1,2,3,4), removal of 3") {
    val expected = makeSet(1,2,4)
    val actual = expected.alter(makeSet(1,2,3,4), 3)
    assertEquals(actual, expected)
  }

  test("alter on Set(1,2,4), addition of 3") {
    val expected = makeSet(1,2,4,3)
    val actual = expected.alter(makeSet(1,2,4), 3)
    assertEquals(actual, expected) }

  test("subsets of Set(1,2)") {
    val expected = makeSet(makeSet(),makeSet(1), makeSet(2), makeSet(1,2))
    val actual = expected.subsets(makeSet(1,2))
    assertEquals(actual, expected)
  }

  test("contains on Set(5,6,1) looking for 1") {
    val expected = true
    val actual = contains(makeSet(5,6,1), 1)
    assertEquals(expected, actual)
  }

  test("exists on Set(6,8,15)") {
    val expected = true
    val actual = exists(makeSet(6,8,15),y => y != 8)
    assertEquals(expected, actual)
  }
}