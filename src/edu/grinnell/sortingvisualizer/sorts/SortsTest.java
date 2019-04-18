package edu.grinnell.sortingvisualizer.sorts;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import edu.grinnell.sortingvisualizer.events.SortEvent;
import edu.grinnell.sortingvisualizer.sorts.*;

class SortsTest {
  @Test
  /**
   * Tests all the sort algorithms with randomly generated arrays of varying lengths.
   */
  void testRandom() {
    Random randomizer = new Random();
    int iteration = randomizer.nextInt(10000);

    for (int i = 0; i < iteration; i++) {
      int length = randomizer.nextInt(100);
      Integer[] original = new Integer[length];

      for (int j = 0; j < length; j++)
        original[j] = randomizer.nextInt();

      Set<Integer> initial = new HashSet<Integer>(Arrays.asList(original));

      Integer[] bubbleCopy = original.clone();
      Integer[] selectionCopy = original.clone();
      Integer[] insertionCopy = original.clone();
      Integer[] mergeCopy = original.clone();
      Integer[] quickCopy = original.clone();

      List<SortEvent<Integer>> bubbleEvent = Sorts.bubbleSort(bubbleCopy);
      List<SortEvent<Integer>> selectionEvent = Sorts.selectionSort(selectionCopy);
      List<SortEvent<Integer>> insertionEvent = Sorts.insertionSort(insertionCopy);
      List<SortEvent<Integer>> mergeEvent = Sorts.mergeSort(mergeCopy);
      List<SortEvent<Integer>> quickEvent = Sorts.quickSort(quickCopy);

      assertTrue(isSorted(bubbleCopy));
      assertTrue(isSorted(selectionCopy));
      assertTrue(isSorted(insertionCopy));
      assertTrue(isSorted(mergeCopy));
      assertTrue(isSorted(quickCopy));

      assertEquals(initial, new HashSet<Integer>(Arrays.asList(bubbleCopy)));
      assertEquals(initial, new HashSet<Integer>(Arrays.asList(selectionCopy)));
      assertEquals(initial, new HashSet<Integer>(Arrays.asList(insertionCopy)));
      assertEquals(initial, new HashSet<Integer>(Arrays.asList(mergeCopy)));
      assertEquals(initial, new HashSet<Integer>(Arrays.asList(quickCopy)));

      bubbleCopy = original.clone();
      selectionCopy = original.clone();
      insertionCopy = original.clone();
      mergeCopy = original.clone();
      quickCopy = original.clone();

      Sorts.eventSort(bubbleCopy, bubbleEvent);
      Sorts.eventSort(selectionCopy, selectionEvent);
      Sorts.eventSort(insertionCopy, insertionEvent);
      Sorts.eventSort(mergeCopy, mergeEvent);
      Sorts.eventSort(quickCopy, quickEvent);

      assertTrue(isSorted(bubbleCopy));
      assertTrue(isSorted(selectionCopy));
      assertTrue(isSorted(insertionCopy));
      assertTrue(isSorted(mergeCopy));
      assertTrue(isSorted(quickCopy));

      assertEquals(initial, new HashSet<Integer>(Arrays.asList(bubbleCopy)));
      assertEquals(initial, new HashSet<Integer>(Arrays.asList(selectionCopy)));
      assertEquals(initial, new HashSet<Integer>(Arrays.asList(insertionCopy)));
      assertEquals(initial, new HashSet<Integer>(Arrays.asList(mergeCopy)));
      assertEquals(initial, new HashSet<Integer>(Arrays.asList(quickCopy)));
    } // for
  } // testRandom

  /**
   * Helper method for the tests, returns true if 'arr' is sorted and false otherwise
   */
  public <T extends Comparable<T>> boolean isSorted(T[] arr) {
    for (int i = 1; i < arr.length; i++) {
      if (arr[i - 1].compareTo(arr[i]) > 0)
        return false;
    } // for
    return true;
  } // isSorted
  
  /**
   * Tests all sorting algorithms with a singleton list.
   */
  @Test
  void testSingleton() {
    String[] arr = new String[] { "A" };
    String[] sorted = arr.clone();
    
    String[] clone = arr.clone();
    List<SortEvent<String>> events = Sorts.selectionSort(arr);
    Sorts.eventSort(clone, events);
    Assert.assertArrayEquals(arr, sorted);
    Assert.assertArrayEquals(clone, arr);
    
    events = Sorts.mergeSort(arr);
    Sorts.eventSort(clone, events);
    Assert.assertArrayEquals(arr, sorted);
    Assert.assertArrayEquals(clone, arr);
    
    events = Sorts.quickSort(arr);
    Sorts.eventSort(clone, events);
    Assert.assertArrayEquals(arr, sorted);
    Assert.assertArrayEquals(clone, arr);
    
    events = Sorts.bubbleSort(arr);
    Sorts.eventSort(clone, events);
    Assert.assertArrayEquals(arr, sorted);
    Assert.assertArrayEquals(clone, arr);
    
    events = Sorts.insertionSort(arr);
    Sorts.eventSort(clone, events);
    Assert.assertArrayEquals(arr, sorted);
    Assert.assertArrayEquals(clone, arr);
  }

  
 
  /**
   * Tests all sorting algorithms with an array that is already sorted.
   */
  @Test
  void testSorted() {
    String[] arr = new String[] { "apples", "apples2", "carrots", "orangutan" };
    String[] sorted = arr.clone();
    
    String[] clone = arr.clone();
    List<SortEvent<String>> events = Sorts.selectionSort(arr);
    Sorts.eventSort(clone, events);
    Assert.assertArrayEquals(arr, sorted);
    Assert.assertArrayEquals(clone, arr);
    
    events = Sorts.mergeSort(arr);
    Sorts.eventSort(clone, events);
    Assert.assertArrayEquals(arr, sorted);
    Assert.assertArrayEquals(clone, arr);
    
    events = Sorts.quickSort(arr);
    Sorts.eventSort(clone, events);
    Assert.assertArrayEquals(arr, sorted);
    Assert.assertArrayEquals(clone, arr);
    
    events = Sorts.bubbleSort(arr);
    Sorts.eventSort(clone, events);
    Assert.assertArrayEquals(arr, sorted);
    Assert.assertArrayEquals(clone, arr);
    
    events = Sorts.insertionSort(arr);
    Sorts.eventSort(clone, events);
    Assert.assertArrayEquals(arr, sorted);
    Assert.assertArrayEquals(clone, arr);
  }
  
  /**
   * Tests all sorting algorithms with an array that is sorted in reverse.
   */
  @Test
  void testReverse() {
    String[] arr = new String[] { "Zoo", "Xi", "Manual", "Daleville", "Chesterfield", "Boston", "Amazon" };
    String[] sorted = new String[] { "Amazon", "Boston", "Chesterfield", "Daleville", "Manual", "Xi", "Zoo" };   
    
    String[] clone = arr.clone();
    List<SortEvent<String>> events = Sorts.selectionSort(arr);
    Sorts.eventSort(clone, events);
    Assert.assertArrayEquals(arr, sorted);  
    Assert.assertArrayEquals(clone, arr);
    
    arr = new String[] { "Zoo", "Xi", "Manual", "Daleville", "Chesterfield", "Boston", "Amazon" };   
    clone = arr.clone();
    events = Sorts.mergeSort(arr);
    Sorts.eventSort(clone, events);
    Assert.assertArrayEquals(arr, sorted);  
    Assert.assertArrayEquals(clone, arr);
    
    arr = new String[] { "Zoo", "Xi", "Manual", "Daleville", "Chesterfield", "Boston", "Amazon" };    
    clone = arr.clone();
    events = Sorts.quickSort(arr);
    Sorts.eventSort(clone, events);
    Assert.assertArrayEquals(arr, sorted);  
    Assert.assertArrayEquals(clone, arr);
    
    arr = new String[] { "Zoo", "Xi", "Manual", "Daleville", "Chesterfield", "Boston", "Amazon" };  
    clone = arr.clone();
    events = Sorts.insertionSort(arr);
    Sorts.eventSort(clone, events);
    Assert.assertArrayEquals(arr, sorted);  
    Assert.assertArrayEquals(clone, arr);
    
    arr = new String[] { "Zoo", "Xi", "Manual", "Daleville", "Chesterfield", "Boston", "Amazon" };  
    clone = arr.clone();
    events = Sorts.bubbleSort(arr);
    Sorts.eventSort(clone, events);
    Assert.assertArrayEquals(arr, sorted);  
    Assert.assertArrayEquals(clone, arr);
  }
  
  
}