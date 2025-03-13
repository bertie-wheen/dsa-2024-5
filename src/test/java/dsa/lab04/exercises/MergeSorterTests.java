package dsa.lab04.exercises;

import dsa.lab04.base.SorterTests;
import dsa.lib.ClassUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.RegisterExtension;

@DisplayName("MergeSorter")
public class MergeSorterTests
{
  @RegisterExtension
  static final ParameterResolver classResolver =
    ClassUtils.resolver(MergeSorter.class);

  @Nested
  public class Sort
    implements SorterTests.Sort
  {
  }
}
