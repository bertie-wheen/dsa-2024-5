package dsa.lab02.exercises;

import dsa.lab02.base.DynamicSequenceTests;
import dsa.lab02.base.LinkedListTests;
import dsa.lab02.base.LinkedNodeTests;
import dsa.lib.ClassUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.RegisterExtension;

@DisplayName("DoublyLinkedList")
public class DoublyLinkedListTests
{
  @RegisterExtension
  static final ParameterResolver classResolver =
    ClassUtils.resolver(DoublyLinkedList.class);

  @Nested
  public class Insert
    implements DynamicSequenceTests.Insert
  {
  }

  @Nested
  public class Remove
    implements DynamicSequenceTests.Remove
  {
  }

  @Nested
  public class Node
    implements LinkedListTests.Node
  {
  }

  @DisplayName("Node")
  @Nested
  public class LinkedNode
  {
    @Nested
    public class InsertPrevious
      implements LinkedNodeTests.InsertPrevious
    {
    }

    @Nested
    public class InsertNext
      implements LinkedNodeTests.InsertNext
    {
    }

    @Nested
    public class Remove
      implements LinkedNodeTests.Remove
    {
    }
  }
}
