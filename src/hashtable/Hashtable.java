package hashtable;

import java.util.LinkedList;

/**
 * Created by Jonathan on 31.05.17.
 */
public class Hashtable implements hashtable.Map {
  private LinkedList<Item>[] table;
  public int itemCount = 0;

  Hashtable(int size) {
    this.table = new LinkedList[size];
  }

  @Override
  public Object put(Object key, Object value) {
    final Item newItem = new Item(key, value);
    final int index = this.hash(key);
    Item returnValue = null;
    if (this.table[index] == null) {
      this.table[index] = new LinkedList();
    } else {
      for (Item item : this.table[index]) {
        if (item.key == newItem.key) {
          returnValue = item;
          this.table[index].remove(item);
        }
      }
    }
    this.table[index].add(newItem);
    itemCount++;
    return returnValue;
  }

  @Override
  public Object get(Object key) {
    return null;
  }

  @Override
  public Object remove(Object key) {
    return null;
  }

  private int hash(Object key) {
    return this.table.length % (int)key;
  }
}
