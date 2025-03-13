package dsa.lib;

public class StringData
{
  public static final Source<String>
    NULL = Source.singleton(null),
    EMPTY = Source.singleton(""),
    NON_EMPTY =
      Source.from(
        "A",
        "B",
        "CS",
        "foo",
        "bar",
        "testing testing 123",
        "*&^%$£!"),
    NON_NULL = Source.chain(EMPTY, NON_EMPTY),
    ALL = Source.chain(NULL, NON_NULL);
}
