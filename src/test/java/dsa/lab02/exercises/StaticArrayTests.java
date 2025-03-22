package dsa.lab02.exercises;

import dsa.lab02.base.DynamicSequenceTests;
import dsa.lib.ClassUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.RegisterExtension;

@DisplayName("StaticArray")
public class StaticArrayTests
{

  @RegisterExtension
  static final ParameterResolver classResolver =
    ClassUtils.resolver(StaticArray.class);

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
