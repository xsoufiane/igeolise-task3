package task1.modifyme

import scalaz.@@

trait Graph[@specialized(Int) A, W] extends Any {

  def neighbours(label: A): Neighbours[A, W]
}

object Graph {

  import GraphUtil._

  type IntNode   = Int @@ NodeTag
  type IntWeight = Int @@ WeightTag

  type IntGraph =
    Map[Int @@ NodeTag, Array[(Int @@ NodeTag, Int @@ WeightTag)] @@ NeighboursTag] @@ GraphTag

  def from(genericGraph: task1.GenericGraph[Int, Int]): Graph[Int, Int] = {
    val elements = genericGraph.points.map {
      case (node, neighbours) =>
        (
          tagNode(node),
          tagNeighbours(
            neighbours.elements
              .map {
                case (node, weight) => (tagNode(node), tagWeight(weight))
              }
              .toArray[(IntNode, IntWeight)]
          )
        )
    }

    tagGraph(elements)
  }

}

trait Neighbours[A, W] extends Any {
  def foreach(f: (A, W) => Unit): Unit
}
