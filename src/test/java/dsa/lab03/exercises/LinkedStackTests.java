package dsa.lab03.exercises;

import dsa.lab03.base.StackTests;
import dsa.lib.ClassUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.RegisterExtension;

@DisplayName("LinkedStack")
public class LinkedStackTests
{

  @RegisterExtension
  static final ParameterResolver classResolver =
    ClassUtils.resolver(LinkedStack.class);

  @Nested
  public class Push
    implements StackTests.Push
  {

  }

  @Nested
  public class Top
    implements StackTests.Top
  {

  }

  @Nested
  public class Pop
    implements StackTests.Pop
  {

  }

}
