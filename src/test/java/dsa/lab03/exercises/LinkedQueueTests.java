package dsa.lab03.exercises;

import dsa.lab03.base.QueueTests;
import dsa.lib.ClassUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.RegisterExtension;

@DisplayName("LinkedQueue")
public class LinkedQueueTests
{

  @RegisterExtension
  static final ParameterResolver classResolver =
    ClassUtils.resolver(LinkedQueue.class);

  @Nested
  public class Enqueue
    implements QueueTests.Enqueue
  {

  }

  @Nested
  public class Front
    implements QueueTests.Front
  {

  }

  @Nested
  public class Dequeue
    implements QueueTests.Dequeue
  {

  }

}
