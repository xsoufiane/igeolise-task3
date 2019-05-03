package task1.modifyme

import scala.collection.mutable.ListBuffer

abstract class FoundEntries[A, W] {

  def add(entry: Entry[A, W]): Unit

  def nEntries: Int
  def isEmpty: Boolean = nEntries == 0
  def toString: String
}

object FoundEntries {
  def empty: FoundEntries[Int, Int] = new ListFoundEntries[Int, Int]
}

class ListFoundEntries[A, W] extends FoundEntries[A, W] {
  val entries = ListBuffer.empty[Entry[A, W]]

  def add(entry: Entry[A, W]): Unit = entries += entry

  def nEntries: Int = entries.length - 1

  override def toString: String = {
    entries.dropRight(1).mkString("\n")
  }
}
