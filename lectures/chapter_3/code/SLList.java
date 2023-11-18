/**
 * An SLList is a list of integers, which hides the terrible truth
 * of the nakedness within.
 */
public class SLList<LochNess> implements List61B<LochNess>{
  private class StuffNode {
    public LochNess item;
    public StuffNode next;

    public StuffNode(LochNess i, StuffNode n) {
      item = i;
      next = n;
    }
  }

  private StuffNode first;
  private int size;

  public SLList(LochNess x) {
    first = new StuffNode(x, null);
    size = 1;
  }

  /**
   * return element at index of i
   * @param i
   */
  @Override
  public LochNess get(int i) {
    if (size == 0) {
      return null;
    }

    StuffNode head = first;
    for (int j = 0; j < i; ++j) {
         head = head.next;
    }

    return head.item;
  }

  /** Adds x to the front of the list. */
  @Override
  public void addFirst(LochNess x) {
    first = new StuffNode(x, first);
    size += 1;
  }

  /** Returns the first item in the list. */
  @Override
  public LochNess getFirst() {
    if (size == 0) {
      return null;
    }

    return first.item;
  }

  /** Adds an item to the end of the list. */
  @Override
  public void addLast(LochNess x) {
    if (size == 0) {
      first = new StuffNode(x, first);
    } else {
      StuffNode p = first;

      /* Move p until it reaches the end of the list. */
      while (p.next != null) {
        p = p.next;
      }

      p.next = new StuffNode(x, null);
    }

    size += 1;
  }

  /**
   * Time complexity: O(N) - non-optimized
   * @return the `value` of last element
   */
  @Override
  public LochNess getLast() {
    if (size == 0) {
      return null;
    }

    StuffNode node = first;
    while (node.next != null) {
      node = node.next;
    }

    return node.item;
  }

  /**
   * Time complexity: O(N)
   * @return the last element
   */
  @Override
  public LochNess removeLast() {
    /* Need to find the second-to-last node
     * Well, the single element list is a special case
     * which results in an empty list and requires the addLast and getLast
     * method to be updated
     **/
    if (size == 0) {
      return null;
    }

    LochNess returnVal;
    if (size == 1) {
      returnVal = first.item;
      first = null;
    } else {
      StuffNode node = first;
      for (int i = 0; i < size - 1; ++i) {
        node = node.next;
      }
      returnVal = node.item;
    }
    size -= 1;

    return returnVal;
  }

  /**
   * Time Complexity: O(N)
   * @param item
   * @param position
   */
  @Override
  public void insert(LochNess item, int position) {
    if (position < 0 || position > size) {
      throw new IllegalArgumentException("Index out of range");
    }

    StuffNode node = first;
    /* Find the element at position - 1 */
    for (int i = 0; i < position - 1; ++i) {
      node = node.next;
    }

    /* Insert the new node */
    StuffNode next = node.next;
    node.next = new StuffNode(item, next);

    size += 1;
  }

  /**
   *
   * @return
   */
  @Override
  public int size() {
    return size;
  }

  @Override
  public void print() {
    for (StuffNode node = first; node != null; node = node.next) {
      System.out.print(node.item + " ");
    }
  }

  public static void main(String[] args) {
    List61B<String> someList = new SLList<String>("GURA");
    someList.addFirst("elk");

    someList.print();
  }
}