package dsa.lib;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;

public class ReplaceCamelCaseDisplayNameGenerator
  implements DisplayNameGenerator
{

  @Override
  public String generateDisplayNameForClass(Class<?> testClass)
  {
    return replaceCamelCase(testClass.getSimpleName());
  }


  @Override
  public String generateDisplayNameForNestedClass(
    Class<?> nestedClass)
  {
    return replaceCamelCase(nestedClass.getSimpleName());
  }


  @Override
  public String generateDisplayNameForMethod(
    Class<?> testClass,
    Method testMethod)
  {
    return replaceCamelCase(testMethod.getName());
  }


  private static String replaceCamelCase(String camelCase)
  {
    return camelCase.replaceAll("(?=[A-Z])", " ").toLowerCase();
  }

}
