package task1.modifyme

abstract class Visited[A] {

  def notVisited(a: A): Boolean

  def updated(a: A): Unit
}

object Visited {
  def empty: SetVisited = SetVisited(new scala.collection.mutable.HashSet[Int])
}

case class SetVisited(val underlying: scala.collection.mutable.Set[Int])
    extends Visited[Int] {

  override def notVisited(a: Int): Boolean = !underlying.contains(a)

  override def updated(a: Int): Unit = {
    underlying += a
    ()
  }
}
