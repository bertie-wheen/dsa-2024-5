package dsa.lab09.exercises;

import dsa.lab09.base.PriorityQueueTests;
import dsa.lib.ClassUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.RegisterExtension;

@DisplayName("BinaryHeapPriorityQueue")
public class BinaryHeapPriorityQueueTests
{

  @RegisterExtension
  static final ParameterResolver classResolver =
    ClassUtils.resolver(BinaryHeapPriorityQueue.class);

  @Nested
  public class Max
    implements PriorityQueueTests.Max
  {

  }

  @Nested
  public class Insert
    implements PriorityQueueTests.Insert
  {

  }

  @Nested
  public class RemoveMax
    implements PriorityQueueTests.RemoveMax
  {

  }

}
