package hashtable;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertTrue;

public class HashtableTest {
  private Hashtable table;
  private Item item1 = new Item(1, "test0");
  private Item item2 = new Item(2, "testNotAdded");

  @Before
  public void setUp() throws Exception {
    this.table = new Hashtable(3);
    this.table.put(this.item1.key, this.item1.value);
  }

  @Test
  public void put() throws Exception {
    assertNull(this.table.put(new Integer(2), "test1"));
    assertNull(this.table.put(new Integer(3), "test2"));
    assertNull(this.table.put(new Integer(5), "test3"));
    assertEquals(4, this.table.itemCount);
    Item previousItem = (Item)this.table.put(new Integer(5), "test4");
    assertTrue(previousItem.equals(new Item(new Integer(5), "test3")));
    assertEquals(4, this.table.itemCount);
  }

  @Test
  public void get() throws Exception {
    Item item = (Item)this.table.get(this.item1.key);
    assertTrue(this.item1.equals(item));
    assertNull(this.table.get(new Integer(2)));
  }

  @Test
  public void remove() throws Exception {
    Item removedItem = (Item) this.table.remove(this.item1.key);
    assertTrue(this.item1.equals(removedItem));
    assertEquals(0, this.table.itemCount);
    assertNull(this.table.remove(this.item2.key));
  }

  @Test
  public void hash() {
    assertEquals(0, this.table.hash(3));
    assertEquals(1, this.table.hash(1));
    assertEquals(2, this.table.hash(2));
  }

  @Test
  public void increase() {
    Hashtable table = new Hashtable(1);
    table.put(new Integer(2), "test1");
    table.put(new Integer(3), "test2");
    table.put(new Integer(4), "test3");
    table.put(new Integer(5), "test4");
    assertEquals(4, table.itemCount);
  }
}