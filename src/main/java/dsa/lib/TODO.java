package dsa.lib;

/**
 * A runtime exception raised when part of an exercise hasn't been implemented (yet!).
 */
public class TODO
  extends RuntimeException
{
  /**
   * Construct a TODO.
   */
  public TODO()
  {
    super("Not yet implemented.");
  }
}
