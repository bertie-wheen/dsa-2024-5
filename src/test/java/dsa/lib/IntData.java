package dsa.lib;

public class IntData
{
  public static final Source<Integer>
    ZERO = Source.singleton(0),
    POSITIVE = Source.from(1, 2, 4, 7, 10, 40, 100, Integer.MAX_VALUE),
    NEGATIVE = Source.from(-1, -2, -4, -7, -10, -40, -100, Integer.MIN_VALUE),
    NON_ZERO = Source.chain(POSITIVE, NEGATIVE),
    NON_NEGATIVE = Source.chain(ZERO, POSITIVE),
    NON_POSITIVE = Source.chain(ZERO, NEGATIVE),
    ALL = Source.chain(NON_NEGATIVE, NEGATIVE);
}
