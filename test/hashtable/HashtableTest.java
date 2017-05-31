package hashtable;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class HashtableTest {
  private Hashtable table;

  @Before
  public void setUp() throws Exception {
    this.table = new Hashtable(3);
  }

  @Test
  public void constructor() {
  }

  @Test
  public void put() throws Exception {
    this.table.put(new Integer(2), "test1");
    this.table.put(new Integer(3), "test2");
    this.table.put(new Integer(5), "test3");
    assertEquals(3, this.table.itemCount);
  }

  @Test
  public void get() throws Exception {
  }

  @Test
  public void remove() throws Exception {
  }

}