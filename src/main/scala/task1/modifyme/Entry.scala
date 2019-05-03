package task1.modifyme

case class Entry[A, W](time: W, fullPath: Seq[A]) {

  override def toString: String = time + " " + fullPath.mkString(" ")
}
