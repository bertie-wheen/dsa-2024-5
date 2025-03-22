package dsa.lab05.exercises;

import dsa.lib.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;

@DisplayName("HashFunction")
public class HashFunctionTests
{

  @RegisterExtension
  static final ParameterResolver classResolver =
    ClassUtils.resolver(HashFunction.class);

  @DisplayName("hash")
  @DefaultDisplayNameGeneration
  interface HashTests
  {

    @ParameterizedTest
    @MethodSource
    default void returnsSameAsSolution(int size, Object object)
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
      int solutionHash = solutionHashFunction.hash(object);
      int hash = hashFunction.hash(object);
      assertEquals(solutionHash, hash);
    }


    //<editor-fold defaultstate="collapsed" desc="returnsSameAsSolution arguments">
    static Source<Arguments> returnsSameAsSolution(Class<?> hashFunctionClass)
    {
      return IntData.POSITIVE.flatReplace((size) ->
        ObjectData.ALL.replace((object) ->
          argumentSet(
            TestNames.format(
              TestNames.constructor(hashFunctionClass, size),
              TestNames.method("hash", object)),
            size,
            object)));
    }
    //</editor-fold>

  }

  @Nested
  class Hash
    implements HashTests
  {

  }

}
