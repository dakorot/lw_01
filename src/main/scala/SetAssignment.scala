package labw01

import Set.NonEmpty
import scala.annotation.tailrec

enum Set[+A]:
  case Empty
  case NonEmpty private[Set](a: A, rest: Set[A])

  def alter[A](xs: Set[A], a: A): Set[A] =
    xs match
      case Empty => NonEmpty(a, Empty)
      case NonEmpty(ele, rest) =>
        if (ele == a) {
          rest match
            case Empty => return Empty
            case NonEmpty(next, restOfRest) => return NonEmpty(next, restOfRest)
        }
        NonEmpty(ele, alter(rest, a))

  def symmDiff[A](as: Set[A], bs: Set[A]): Set[A] =
    as match
      case Empty => bs
      case NonEmpty(a, rest) =>
        if (contains(bs, a)) symmDiff(rest, bs)
        NonEmpty(a, symmDiff(rest, bs))

  def subsets[A](xs: Set[A]): Set[Set[A]] =
    def f[A](xs: Set[A]): List[A] =
      xs match
        case Empty => List.empty
        case NonEmpty(a, rest) => a :: f(rest)
    var s: List[A] = f(xs)
    val size: Int = 1 << s.size
    var i: Int = 0
    var k: Int = 0
    var set: Set[Set[A]] = Empty
    while (i < size)
      //beginning of subset
      var met: Set[A] = Empty
      while (k < size && k <= i)
        if (((1 << k) & i) > 0)
          met = alter[A](met, s(k))
        k += 1
      //end of subset
      set = alter(set, met)
      i += 1
      k = 0
    set

def makeSet[A](xs: A*): Set[A] =
  var foo: Set[A] = Set.Empty
  for (i <- xs)
    if (!contains(foo, i))
      foo = foo.alter(foo, i)
  return foo

@tailrec
def contains[A](xs: Set[A], a: A): Boolean =
  xs match
    case Set.Empty => false
    case Set.NonEmpty(elem, rest) =>
      if (a == elem) return true
      contains(rest, a)

@tailrec
def exists[A](xs: Set[A], p: A => Boolean): Boolean =
  xs match
    case Set.Empty => false
    case Set.NonEmpty(a, rest) =>
      if (p(a)) return true
      exists(rest, p)
