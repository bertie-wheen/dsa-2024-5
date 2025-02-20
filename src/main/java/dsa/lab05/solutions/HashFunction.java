package dsa.lab05.solutions;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A randomly-chosen hash function from a universal family.
 */
public class HashFunction
{
  private static long LARGE_PRIME = 9_000_123_456_789_000_007L;
  private long a;
  private long b;
  private int size;

  /**
   * Construct a hash function, randomly choosing its parameters.
   *
   * @param size the range of hashes that {@link #hash(Object)} should return
   */
  public HashFunction(int size)
  {
    if (size < 1)
    {
      throw new IllegalArgumentException();
    }
    this.a = ThreadLocalRandom.current().nextLong(1, LARGE_PRIME - 1);
    this.b = ThreadLocalRandom.current().nextLong(0, LARGE_PRIME - 1);
    this.size = size;
  }

  /**
   * Get the size of the range of hashes.
   *
   * @return the range of hashes that {@link #hash(Object)} should return
   */
  public int size()
  {
    return this.size;
  }

  /**
   * Hash the given object to a non-negative int less than {@link #size()}.
   *
   * @param object an object
   * @return a hash of that object
   */
  public int hash(Object object)
  {
    long initialHash = Objects.hashCode(object);
    return (int) (
      Math.floorMod(
        initialHash * this.a + this.b,
        HashFunction.LARGE_PRIME) % this.size);
  }
}
