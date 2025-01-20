package dsa.lib;

public class Is
{
  public static boolean string(Object object)
  {
    return Is.string(object.getClass());
  }

  public static boolean string(Class<?> class_)
  {
    return class_.equals(String.class);
  }
}
