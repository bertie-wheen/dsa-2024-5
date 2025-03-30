package dsa.lab05.solutions;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A randomly-chosen hash function from a universal family.
 */
public class HashFunction
{

  /** An arbitrary prime > 2^31 - 1. */
  private static long LARGE_PRIME = 9_000_123_456_789_000_007L;
  // NOTE: We need a prime greater than size.
  // NOTE: Since size is an int, its maximum value is 2^31 - 1.
  // NOTE: For this to be larger, it must be a long.
  // NOTE: (This prime was chosen specially for this module!)
  // NOTE: In a highly-optimized implementation, you would probably choose a
  //       Mersenne prime, as it's possible to more efficiently compute the
  //       modulus if the divisor is a Mersenne prime than other sorts of
  //       primes. You would also vary the prime based on the specific size.
  // NOTE: https://ariya.io/2007/02/modulus-with-mersenne-prime


  /** A random value 1 <= a < LARGE_PRIME. */
  private long a;
  // NOTE: This is the factor we multiply by.


  /** A random value 0 <= b < LARGE_PRIME. */
  private long b;
  // NOTE: This is the offset we add.


  /** The number of possible hashes. */
  private int size;
  // NOTE: Hashes will be integers h where 0 <= h < size.


  /**
   * Construct a hash function, randomly choosing its parameters.
   *
   * @param size the range of hashes that {@link #hash(Object)} should return
   */
  public HashFunction(int size)
  {
    // NOTE: Size must be at least 1, otherwise no hashes would be possible.
    if (size < 1)
    {
      throw new IllegalArgumentException();
    }

    // NOTE: (Pseudo-)randomly choose a factor and an offset.
    // NOTE: We're not using ThreadLocalRandom because we particularly care
    //       about thread-safety, just because it has a nice nextLong() method.
    // NOTE: You could use java.util.Random, but it would take more than 1 line.
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
    // NOTE: We use Java's standard hash function so that we can deal with
    //       numbers rather than objects.
    // NOTE: Objects.hashCode(object) is just a null-safe version of
    //       object.hashCode(), much like Objects.equals() v Object.equals().
    // NOTE: hashCode() uses the full range of int, i.e. initialHash can be any
    //       value between -2^31 and 2^31 - 1 (inclusive).
    int initialHash = Objects.hashCode(object);

    // NOTE: We want to narrow initialHash into the range 0 to size - 1.
    // NOTE: We use our a, b and LARGE_PRIME to try and improve the expected
    //       uniformity of the distribution.
    // NOTE: You could think of this as two stages:
    //         long redistributed = floorMod(initialHash * a + b, LARGE_PRIME);
    //         int hash = (int) (redistributed % size);
    // NOTE: First we redistribute using the randomly-chosen parameters.
    // NOTE: (initialHash may be negative, but even if it wasn't, multiplying by
    //       a (and then adding b) could make the result overflow a long, wrap
    //       around, and become negative. We therefore use Math.floorMod()
    //       rather than % as in Java the % operator doesn't do what you might
    //       expect with negative inputs. (In some languages, e.g. Python, it
    //       does, in others, e.g. Java, it doesn't. There are two valid ways
    //       to implement it, and each language chooses one or the other.)
    //       floorMod() will always give a non-negative result (< LARGE_PRIME)
    //       even if initialHash * a + b is negative.)
    // NOTE: We then mod by size to bring the hash into the right range.
    // NOTE: (redistributed won't be negative, so we needn't use floorMod()
    //       here. Also, once we've modded by size - which is an int - the
    //       result will necessarily be in the range of an int, so we can safely
    //       cast the result to int without worrying about overflow. Note that
    //       if we had written (int) redistributed % size, that would have
    //       casted redistributed to an int (which may result in overflow), and
    //       _then_ modded, whereas we want to cast _after_ modding by size,
    //       hence the parentheses around redistributed % size.)
    return (int) (
      Math.floorMod(
        initialHash * this.a + this.b,
        HashFunction.LARGE_PRIME) % this.size);
  }

}
