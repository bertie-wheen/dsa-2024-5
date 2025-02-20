package dsa.lab05.exercises;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("HashFunction")
public class HashFunctionTests
{
  @ParameterizedTest
  @DisplayName("hash")
  @FieldSource("dsa.lib.examples.ints.Positive#AND_OBJECTS")
  void hash(int size, Object object)
    throws NoSuchFieldException, IllegalAccessException
  {
    HashFunction hashFunction = new HashFunction(size);
    dsa.lab05.solutions.HashFunction solutionHashFunction =
      new dsa.lab05.solutions.HashFunction(size);
    //<editor-fold defaultstate="collapsed" desc="Ensure solution is using same parameters">
    {
      Field solutionA = solutionHashFunction.getClass().getDeclaredField("a");
      Field solutionB = solutionHashFunction.getClass().getDeclaredField("b");
      solutionA.setAccessible(true);
      solutionB.setAccessible(true);
      Field a = hashFunction.getClass().getDeclaredField("a");
      Field b = hashFunction.getClass().getDeclaredField("b");
      a.setAccessible(true);
      b.setAccessible(true);
      solutionA.set(solutionHashFunction, a.get(hashFunction));
      solutionB.set(solutionHashFunction, b.get(hashFunction));
    }
    //</editor-fold>
    int correctHash = solutionHashFunction.hash(object);
    assertEquals(correctHash, hashFunction.hash(object));
  }
}
