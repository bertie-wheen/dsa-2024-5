package dsa.lab01.base;

/**
 * A string container.
 * <p>
 * Something that contains some number of strings,
 * not necessarily in any particular order.
 */
public interface StringContainer
{

  /**
   * Get the number of contained strings.
   *
   * @return the size
   */
  int size();


  /**
   * Check if it's empty.
   *
   * @return whether there are no strings
   */
  default boolean isEmpty()
  {
    return this.size() == 0;
  }


  /**
   * Check if the given string is equal to any of those contained.
   *
   * @param string the string to check for membership
   * @return whether such a string is contained
   */
  boolean contains(String string);

}
