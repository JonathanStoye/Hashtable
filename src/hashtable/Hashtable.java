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
    } else if(this.table[index] == null) {
      this.table[index] = new LinkedList();
    }
    this.table[index].add(newItem);
    this.itemCount++;
    if (this.itemCount > this.table.length * 3) {
      this.increase();
    }
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
      this.itemCount--;
      return item;
    }
    return null;
  }

  protected int hash(Object key) {
    return (int)key % this.table.length;
  }

  protected void increase() {
    final LinkedList<Item>[] previousTable = this.table;
    this.table = new LinkedList[this.itemCount * 2];
    this.itemCount = 0;
    System.out.println(table.length);
    for (LinkedList<Item> items : previousTable) {
      for (Item item : items) {
        System.out.println(this.hash(item.key));
        this.put(item.key, item.value);
      }
    }
  }
}
