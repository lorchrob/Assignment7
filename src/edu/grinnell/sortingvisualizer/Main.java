package edu.grinnell.sortingvisualizer;

import java.util.List;
import edu.grinnell.sortingvisualizer.events.SortEvent;
import edu.grinnell.sortingvisualizer.sorts.Sorts;

// Experiment class
public class Main {

  public static void main(String[] args) {
    String[] arr1 = {"z", "x", "apple", "oranges", "bananas", "cherries"};
    String[] arr2 = {"z", "x", "apple", "oranges", "bananas", "cherries"};
    List<SortEvent<String>> events = Sorts.quickSort(arr1);

    for (String s : arr1) {
      System.out.println(s);
    }

    System.out.println();

    for (String s : arr2) {
      System.out.println(s);
    }
    
    System.out.println();
    Sorts.eventSort(arr2, events);

    for (String s : arr2) {
      System.out.println(s);
    }
  }
}
