package dsa.lib.lab09;

import dsa.lab09.base.PriorityQueueItem;
import dsa.lib.Source;

public class PriorityQueueItemSourceData
{

  public static class IntsToInts
  {

    public static final Source<Source<PriorityQueueItem<Integer, Integer>>>
      EMPTY = Source.singleton(Source.empty()),
      SINGLETON = PriorityQueueItemData.INTS_TO_INTS.replace(Source::singleton),
      MULTI_ITEM = Source.powerSet(PriorityQueueItemData.INTS_TO_INTS, 2, 4),
      NON_EMPTY = Source.chain(SINGLETON, MULTI_ITEM),
      ALL = Source.chain(EMPTY, NON_EMPTY);

  }


  public static class IntsToStrings
  {

    public static final Source<Source<PriorityQueueItem<Integer, String>>>
      EMPTY = Source.singleton(Source.empty()),
      SINGLETON = PriorityQueueItemData.INTS_TO_STRINGS.replace(Source::singleton),
      MULTI_ITEM = Source.powerSet(PriorityQueueItemData.INTS_TO_STRINGS, 2, 4),
      NON_EMPTY = Source.chain(SINGLETON, MULTI_ITEM),
      ALL = Source.chain(EMPTY, NON_EMPTY);

  }

}
