package dsa.lib;

public class TestNames
{

  private static String argumentsToString(Object[] arguments)
  {
    return '(' + String.join(
      ", ",
      ArrayUtils.replace(
        CharSequence.class, arguments,
        (argument) -> To.string(argument))) + ')';
  }


  public static class ConstructorInvocation
  {

    private Class<?> class_;


    private Object[] arguments;


    private ConstructorInvocation(Class<?> class_, Object... arguments)
    {
      this.class_ = class_;
      this.arguments = arguments;
    }


    @Override
    public String toString()
    {
      StringBuilder sb = new StringBuilder();
      sb.append("new ");
      sb.append(this.class_.getSimpleName());
      if (this.class_.getTypeParameters().length > 0)
      {
        sb.append("<>");
      }
      sb.append(argumentsToString(this.arguments));
      return sb.toString();
    }

  }

  public static class MethodCall
  {

    private String name;


    private Object[] arguments;


    private MethodCall(String name, Object... arguments)
    {
      this.name = name;
      this.arguments = arguments;
    }


    @Override
    public String toString()
    {
      return '.' + this.name + argumentsToString(this.arguments);
    }

  }


  public static ConstructorInvocation constructor(
    Class<?> class_,
    Object... arguments)
  {
    return new ConstructorInvocation(class_, arguments);
  }


  public static ConstructorInvocation constructorFor(
    Class<?> class_,
    Object object)
  {
    return object instanceof Iterable
      ? constructorFor(class_, (Iterable<?>) object)
      : constructor(class_, '<' + To.string(object) + '>');
  }


  public static ConstructorInvocation constructorFor(
    Class<?> class_,
    Iterable<?> object)
  {
    return constructor(class_, (Object[]) ArrayUtils.from(object));
  }


  public static MethodCall method(
    String name,
    Object... arguments)
  {
    return new MethodCall(name, arguments);
  }


  public static String format(
    ConstructorInvocation constructor,
    MethodCall... methods)
  {
    StringBuilder sb = new StringBuilder();
    sb.append(constructor);
    for (MethodCall method : methods)
    {
      sb.append(method);
    }
    return sb.toString();
  }

}
