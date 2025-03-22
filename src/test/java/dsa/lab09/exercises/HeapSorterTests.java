package dsa.lab09.exercises;

import dsa.lab04.base.SorterTests;
import dsa.lib.ClassUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.RegisterExtension;

@DisplayName("HeapSorter")
public class HeapSorterTests
{

  @RegisterExtension
  static final ParameterResolver classResolver =
    ClassUtils.resolver(HeapSorter.class);

  @Nested
  public class Sort
    implements SorterTests.Sort
  {

  }

}
