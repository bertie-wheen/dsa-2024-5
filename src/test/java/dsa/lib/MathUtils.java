package dsa.lib;

public class MathUtils
{
  public static int addSaturating(int a, int b)
  {
    try
    {
      return Math.addExact(a, b);
    }
    catch (ArithmeticException e)
    {
      return Integer.MAX_VALUE;
    }
  }
}
