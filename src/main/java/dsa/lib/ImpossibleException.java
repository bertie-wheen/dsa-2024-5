package dsa.lib;

/**
 * A runtime exception raised when something that should never happen happens.
 */
class ImpossibleException
  extends RuntimeException
{

  /**
   * Construct an ImpossibleException.
   */
  public ImpossibleException()
  {
    super("Something impossible happened - contact the module team!");
  }


  /**
   * Construct an ImpossibleException with the given cause.
   */
  public ImpossibleException(Throwable cause)
  {
    super("Something impossible happened - contact the module team!", cause);
  }

}
