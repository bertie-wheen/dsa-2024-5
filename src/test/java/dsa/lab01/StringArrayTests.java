package dsa.lab01;

import dsa.lab01.exercises.StringArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringArrayTests
{
  @Test
  void sizeOfEmptyStringArrayIsZero()
  {
    StringArray stringArray = new StringArray();
    assertEquals(0, stringArray.size());
  }

  @Test
  void sizeOfOneItemStringArrayIsOne()
  {
    StringArray stringArray = new StringArray("foo");
    assertEquals(1, stringArray.size());
  }

  @Test
  void sizeOfTwoItemStringArrayIsTwo()
  {
    StringArray stringArray = new StringArray("foo", "bar");
    assertEquals(2, stringArray.size());
  }

  @Test
  void sizeOfThreeItemStringArrayIsThree()
  {
    StringArray stringArray = new StringArray("foo", "bar", "quux");
    assertEquals(3, stringArray.size());
  }
}
