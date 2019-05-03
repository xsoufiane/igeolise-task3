### Task 3 - optimization : Solution

Several changes have been conducted on the *modifyme* package :
* I resolved the problem of boxing/unboxing using specialization.
* I used tagged types instead of value classes to escape instantiation, and to support specialization.
* I used arrays to ensure locality, and I have rewritten the foreach method to use a while loop because while loops are way too fast. Also, if we used foreach, then we will have an anonymous class meaning additional time overhead.
* In the [Distances](src/main/scala/task1/modifyme/Distances.scala), I have used a mutable HashMap instead of an immutable one. This is because foreach Dijkstra run, we have a heterogeneous Distance Map. So, it is safe to use an underlying mutable map. This reduces time overhead.
* For the same reasons, I have used a ListBuffer in the  [FoundEntries](src/main/scala/task1/modifyme/FoundEntries.scala).

Now for the runtime, I have used these options **-Xms1g -Xmx15g -XX:+UseG1GC**. I have used the G1 garbage collector because it has show less time pauses. The execution time went down in an order of magnitude. Moreover, the genric graph implementation, which I have provided assures abstraction and lead in an approximate time reduction of 40%. 
