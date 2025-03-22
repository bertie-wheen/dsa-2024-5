package dsa.lab01.exercises;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Array")
public class ArrayTests
{

  @Test
  void sizeOfEmptyArrayIsZero()
  {
    Array<String> stringArray = new Array<>();
    assertEquals(0, stringArray.size());
  }


  @Test
  void sizeOfOneItemArrayIsOne()
  {
    Array<String> stringArray = new Array<>("foo");
    assertEquals(1, stringArray.size());
  }


  @Test
  void sizeOfTwoItemArrayIsTwo()
  {
    Array<String> stringArray = new Array<>("foo", "bar");
    assertEquals(2, stringArray.size());
  }


  @Test
  void sizeOfThreeItemArrayIsThree()
  {
    Array<String> stringArray = new Array<>("foo", "bar", "quux");
    assertEquals(3, stringArray.size());
  }

}
