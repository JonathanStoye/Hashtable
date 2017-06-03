package hashtable;

import java.util.LinkedList;

/**
 * Created by Jonathan on 31.05.17.
 *
 * Speichert Schlüssel-Wert-Paare in einem Array. Errechnet die Indicies mit Hilfe
 * einer einfach Hashmethode. Vergrößert sich selbständig ab einem gewissen Füllstand
 * und berechnet und sortiert die Elemente dann neu.
 */
public class Hashtable implements hashtable.Map {
  private LinkedList<Item>[] table;
  public int itemCount = 0;

  Hashtable(int size) {
    this.table = new LinkedList[size];
  }

  /**
   * Speichert ein Schlüssel-Wert Paar. Überschreibt ein bereits vorhandenes
   * Objekt mit dem gleichen Schlüssel
   *
   * @param key   der zu speichernde Schluessel
   * @param value der zu speichernde Wert
   * @return
   */
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

  /**
   * Gibt das durch @param key definierte Element zurück
   *
   * @param key Schlüssel des gesuchten Elementes
   * @return null wenn kein Element gefunden wurde, ansonsten das gesuchte Element
   */
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

  /**
   * Entfernt das Object mit dem gegebenen Schlüssel und verringert
   * die Anzahl der Elemente entsprechend
   *
   * @param key der Schluessel ds zu entfernenden Elementes
   * @return null wenn das Element nicht gefunden wurde ansonsten das entfernte Element
   */
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

  /**
   * Einfache Hashmethode mit dem Modulusoperator
   *
   * @param key - wird nach int gecastet
   * @return Hashwert
   */
  protected int hash(Object key) {
    return (int)key % this.table.length;
  }

  /**
   * Vergrößert das zugrundeliegende Array und organisiert die Elemente auf Basis
   * der sich dadurch ändernden Hashmethode neu
   */
  protected void increase() {
    final LinkedList<Item>[] previousTable = this.table;
    this.table = new LinkedList[this.itemCount * 2];
    this.itemCount = 0;
    for (LinkedList<Item> items : previousTable) {
      for (Item item : items) {
        this.put(item.key, item.value);
      }
    }
  }
}
