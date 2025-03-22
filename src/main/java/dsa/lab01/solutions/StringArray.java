package dsa.lab01.solutions;

import dsa.lab01.base.StringContainer;

/**
 * An array-based string container.
 * <p>
 * Contains some number of strings using an array.
 */
public class StringArray
  implements StringContainer
{

  private String[] strings;


  //<editor-fold defaultstate="collapsed" desc="Constructors">


  /**
   * Construct an empty string array.
   */
  public StringArray()
  {
    this.strings = new String[0];
  }


  /**
   * Construct a string array containing the given strings.
   *
   * @param strings the strings
   */
  public StringArray(String... strings)
  {
    this.strings = new String[strings.length];
    for (int i = 0; i < strings.length; i++)
    {
      this.strings[i] = strings[i];
    }
  }


  //</editor-fold>


  @Override
  public int size()
  {
    return this.strings.length;
  }


  @Override
  public boolean contains(String string)
  {
    for (String containedString : this.strings)
    {
      if (string == null && containedString == null)
      {
        return true;
      }
      if (string != null && string.equals(containedString))
      {
        return true;
      }
      // NOTE: The two above conditions could instead be written as
      //         if (Objects.equals(string, containedString))
      //         {
      //           return true;
      //         }
      //       which handles null checks.
      //       The problem is that strings are objects and can thus be null,
      //       and if you tried to call .equals (or any method) on null,
      //       you'd get a NullPointerException.
    }
    return false;
  }

}
