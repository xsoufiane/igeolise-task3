package task1.modifyme

import scalaz.{Tag, @@}

sealed trait GraphTag
sealed trait NeighboursTag
sealed trait NodeTag
sealed trait WeightTag

object GraphUtil {

  import GraphOps._

  implicit def unwrap[A, T](t: T @@ A): T = Tag.unwrap(t)

  def tagNode[@specialized(Int) A](node: A): A @@ NodeTag =
    Tag[A, NodeTag](node)

  def tagWeight[@specialized(Int) A](weight: A): A @@ WeightTag =
    Tag[A, WeightTag](weight)

  def tagGraph[A, W](
      points: Map[A @@ NodeTag,
                  Array[(A @@ NodeTag, W @@ WeightTag)] @@ NeighboursTag]
  ): Graph[A, W] = {

    Tag[Map[A @@ NodeTag, Array[(A @@ NodeTag, W @@ WeightTag)]
      @@ NeighboursTag], GraphTag](points)
  }

  def tagNeighbours[A, W](
      array: Array[(A @@ NodeTag, W @@ WeightTag)]
  ): Array[(A @@ NodeTag, W @@ WeightTag)] @@ NeighboursTag = {

    Tag[Array[(A @@ NodeTag, W @@ WeightTag)], NeighboursTag](array)
  }

  def emptyNeighbours[A, W]()
    : Array[(A @@ NodeTag, W @@ WeightTag)] @@ NeighboursTag = {

    Tag[Array[(A @@ NodeTag, W @@ WeightTag)], NeighboursTag](Array.empty)
  }
}

object GraphOps {

  import GraphUtil._

  implicit class TaggedNeighboursOps[A, W](
      private val t: Array[(A @@ NodeTag, W @@ WeightTag)] @@ NeighboursTag
  ) extends AnyVal
      with Neighbours[A, W] {

    @inline override def foreach(f: (A, W) => Unit): Unit = {
      var i = 0

      while (i < t.length) {
        val (node, weight) = t(i)

        f(node, weight)
        i += 1
      }
    }
  }

  implicit class TaggedGraphOps[A, W](
      private val t: Map[A @@ NodeTag,
        Array[(A @@ NodeTag, W @@ WeightTag)] @@ NeighboursTag] @@ GraphTag
  ) extends AnyVal
      with Graph[A, W] {

    @inline def neighbours(id: A): TaggedNeighboursOps[A, W] =
      new TaggedNeighboursOps(
        t.getOrElse(tagNode(id), emptyNeighbours[A, W]())
      )
  }
}
