package task1.modifyme

import scala.collection.mutable.HashMap

abstract class Distances[A, W] {

  def distanceAt(a: A): W

  def updated(label: A, value: W): Unit
}

object Distances {
  def empty(implicit ev: Infinity[Int]): Distances[Int, Int] = new MapDistances
}

class MapDistances[A, W](implicit ev: Infinity[W]) extends Distances[A, W] {
  val underlying = HashMap.empty[A, W]

  @inline def distanceAt(a: A): W = underlying.getOrElse(a, Infinity[W])

  @inline def updated(label: A, value: W): Unit = {
    underlying.update(label, value)

    ()
  }
}
