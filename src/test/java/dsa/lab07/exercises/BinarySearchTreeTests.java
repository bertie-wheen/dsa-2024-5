package dsa.lab07.exercises;

import dsa.lab04.base.MapTests;
import dsa.lib.ClassUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.RegisterExtension;

@DisplayName("BinarySearchTree")
public class BinarySearchTreeTests
{

  @RegisterExtension
  static final ParameterResolver classResolver =
    ClassUtils.resolver(BinarySearchTree.class);

  @Nested
  class Find
    implements MapTests.Find
  {

  }

  @Nested
  class Insert
    implements MapTests.Insert
  {

  }

  @Nested
  class Remove
    implements MapTests.Remove
  {

  }

}
