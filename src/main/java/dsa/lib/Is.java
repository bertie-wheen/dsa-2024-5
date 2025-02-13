package dsa.lib;

import dsa.lab02.base.Container;
import dsa.lab04.base.Map;
import dsa.lab04.base.MapItem;

import java.util.Arrays;

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

  public static boolean mapItem(Object object)
  {
    return Is.mapItem(object.getClass());
  }

  public static boolean mapItem(Class<?> class_)
  {
    return class_.equals(MapItem.class);
  }

  public static boolean container(Object object)
  {
    return Is.container(object.getClass());
  }

  public static boolean container(Class<?> class_)
  {
    return Is.interface_(class_, Container.class);
  }

  public static boolean map(Object object)
  {
    return Is.map(object.getClass());
  }

  public static boolean map(Class<?> class_)
  {
    return Is.interface_(class_, Map.class);
  }

  private static boolean interface_(Class<?> class_, Class<?> interface_)
  {
    return class_.equals(interface_) ||
      Arrays.stream(class_.getInterfaces())
        .anyMatch((i) -> Is.interface_(i, interface_));
  }
}
