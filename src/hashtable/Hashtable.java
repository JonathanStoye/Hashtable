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
    Object returnValue = null;
    if (this.get(key) != null) {
      returnValue = this.remove(key);
    } else {
      this.table[index] = new LinkedList();
    }
    this.table[index].add(newItem);
    itemCount++;
    return returnValue;
  }

  @Override
  public Object get(Object key) {
    final int index = this.hash(key);
    if (this.table[index] != null) {
      for (Item item : this.table[index]) {
        if (key.equals(item.key)) {
          return item;
        }
      }
    }
    return null;
  }

  @Override
  public Object remove(Object key) {
    final int index = this.hash(key);
    Item item = (Item)this.get(key);
    if (item != null) {
      this.table[index].remove(item);
      itemCount--;
      return item;
    }
    return null;
  }

  private int hash(Object key) {
    return (int)key % this.table.length;
  }
}
