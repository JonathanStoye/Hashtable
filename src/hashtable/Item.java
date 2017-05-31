package hashtable;

public class Item {
  public Object key;
  public Object value;

  Item( Object key, Object value) {
   this.key = key;
   this.value = value;
  }

  public boolean equals(Item o) {
    if (this.key.equals(o.key) && this.value.equals(o.value)) {
      return true;
    }
    return false;
  }
}
