package task1.modifyme

/*
 * A representation of the Dijkstra search space. A path.
 */
case class Search[A](path: List[A]) {

  def node: A = path.head

  def -->(node: A): Search[A] = copy(node :: path)

  def toEntry[W](time: W): Entry[A, W] = Entry(time, path.reverse)
}

object Search {
  def start(node: Int): Search[Int] = new Search(node :: Nil)
}
