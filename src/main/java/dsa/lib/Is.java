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

  public static boolean stringArrayExercise(Object object)
  {
    return Is.stringArrayExercise(object.getClass());
  }

  public static boolean stringArrayExercise(Class<?> class_)
  {
    return dsa.lab01.exercises.StringArray.class.equals(class_);
  }

  public static boolean stringArraySolution(Object object)
  {
    return Is.stringArraySolution(object.getClass());
  }

  public static boolean stringArraySolution(Class<?> class_)
  {
    return dsa.lab01.solutions.StringArray.class.equals(class_);
  }

  public static boolean arrayExercise(Object object)
  {
    return Is.arrayExercise(object.getClass());
  }

  public static boolean arrayExercise(Class<?> class_)
  {
    return dsa.lab01.exercises.Array.class.equals(class_);
  }

  public static boolean arraySolution(Object object)
  {
    return Is.arraySolution(object.getClass());
  }

  public static boolean arraySolution(Class<?> class_)
  {
    return dsa.lab01.solutions.Array.class.equals(class_);
  }
}
