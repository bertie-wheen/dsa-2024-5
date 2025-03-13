package dsa.lab03.exercises;

import dsa.lab02.base.DynamicSequenceTests;
import dsa.lab02.base.StaticSequenceTests;
import dsa.lib.ClassUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.RegisterExtension;

@DisplayName("CircularDynamicArray")
public class CircularDynamicArrayTests
{
  @RegisterExtension
  static final ParameterResolver classResolver =
    ClassUtils.resolver(CircularDynamicArray.class);

  @Nested
  public class Get
    implements StaticSequenceTests.Get
  {
  }

  @Nested
  public class Set
    implements StaticSequenceTests.Set
  {
  }

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
}
