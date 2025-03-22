package dsa.lab09.exercises;

import dsa.lab09.base.PriorityQueueTests;
import dsa.lib.ClassUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.RegisterExtension;

@DisplayName("SortedArrayPriorityQueue")
public class SortedArrayPriorityQueueTests
{

  @RegisterExtension
  static final ParameterResolver classResolver =
    ClassUtils.resolver(SortedArrayPriorityQueue.class);

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
