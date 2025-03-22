package dsa.lab01.exercises;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("StringArray")
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
