package edu.grinnell.sortingvisualizer.sorts;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import edu.grinnell.sortingvisualizer.events.*;

public class Sorts {
  // helper methods
  
  private static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
    T temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static <T extends Comparable<T>> void eventSort(T[] arr, List<SortEvent<T>> events) {
    Iterator<SortEvent<T>> iter = events.iterator();

    while (iter.hasNext()) {
      iter.next().apply(arr);
    }
  }

  // sorting algorithms

  /**
   * NOTE: All sorting algorithms share parameters, return values, and pre/postconditions
   * @param arr, the array to be sorted
   * @return a list of sort events associated with the sort
   * 
   * @pre N/A
   * @post 'arr' is modified to be a permutation of itself (before the function was called)
   *       such that for each index 'n' (except for the last), n.compareTo(n+1) <= 0 
   */
  // source: https://en.wikipedia.org/wiki/Selection_sort
  public static <T extends Comparable<T>> List<SortEvent<T>> selectionSort(T[] arr) {
    List<SortEvent<T>> events = new ArrayList<SortEvent<T>>();
    
    int i, j;
    int n = arr.length;

    for (j = 0; j < n - 1; j++) {
      /* find the min element in the unsorted a[j .. n-1] */

      /* assume the min is the first element */
      int iMin = j;
      /* test against elements after j to find the smallest */
      for (i = j + 1; i < n; i++) {
        /* if this element is less, then it is the new minimum */
        events.add(new CompareEvent<T>(new ArrayList<>(List.of(i, iMin))));
        if (arr[i].compareTo(arr[iMin]) < 0) {
          /* found new minimum; remember its index */
          iMin = i;
        }
      }

      if (iMin != j) {
        events.add(new SwapEvent<T>(new ArrayList<>(List.of(j, iMin))));
        swap(arr, j, iMin);
        // swap(arr[j], arr[iMin]);
      }
    }
    
    return events;
  }

  // source: https://www.geeksforgeeks.org/insertion-sort/
  /**
   * NOTE: All sorting algorithms share parameters, return values, and pre/postconditions
   * @param arr, the array to be sorted
   * @return a list of sort events associated with the sort
   * 
   * @pre N/A
   * @post 'arr' is modified to be a permutation of itself (before the function was called)
   *       such that for each index 'n' (except for the last), n.compareTo(n+1) <= 0 
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> insertionSort(T[] arr) {
    List<SortEvent<T>> events = new ArrayList<SortEvent<T>>();
    
    int n = arr.length;
    for (int i = 1; i < n; ++i) {
      T key = arr[i];
      int j = i - 1;

      /*
       * Move elements of arr[0..i-1], that are greater than key, to one position ahead of their
       * current position
       */
      while (j >= 0 && (key.compareTo(arr[j]) < 0)) {
        events.add(new CompareEvent<T>(new ArrayList<>(List.of(j, i))));
        arr[j + 1] = arr[j];
        events.add(new CopyEvent<T>(new ArrayList<>(List.of(j+1)), arr[j]));
        j = j - 1;
      }
      //events.add(new CompareEvent<T>(new ArrayList<>(List.of(j, i)))); // here, 'j' can be -1 (not good), so we aren't including it
      
      arr[j + 1] = key;
      events.add(new CopyEvent<T>(new ArrayList<>(List.of(j+1)), key));
    }
    
    return events;
  }

  // source: https://www.geeksforgeeks.org/merge-sort/
  // see documentation for "merge"
  @SuppressWarnings("unchecked")
  private static <T extends Comparable<T>> void merge(T[] arr, int l, int m, int r, List<SortEvent<T>> events) {
    // Find sizes of two subarrays to be merged
    int n1 = m - l + 1;
    int n2 = r - m;

    /* Create temp arrays */
    T[] L = (T[]) Array.newInstance(arr[0].getClass(), n1);
    T[] R = (T[]) Array.newInstance(arr[0].getClass(), n2);

    /* Copy data to temp arrays */
    for (int i = 0; i < n1; ++i) {
      //events.add(new CopyEvent<T>(new ArrayList<>(List.of(i)), arr[l + i]));
      L[i] = arr[l + i];
    }
    for (int j = 0; j < n2; ++j) {
      //events.add(new CopyEvent<T>(new ArrayList<>(List.of(j)), arr[m + 1 + j]));
      R[j] = arr[m + 1 + j];
    }


    /* Merge the temp arrays */

    // Initial indexes of first and second subarrays
    int i = 0, j = 0;

    // Initial index of merged subarry array
    int k = l;
    while (i < n1 && j < n2) {
      if (L[i].compareTo(R[j]) <= 0) {
        events.add(new CopyEvent<T>(new ArrayList<>(List.of(k)), L[i]));
        arr[k] = L[i];
        i++;
      } else {
        events.add(new CopyEvent<T>(new ArrayList<>(List.of(k)), R[j]));
        arr[k] = R[j];
        j++;
      }
      k++;
    }

    /* Copy remaining elements of L[] if any */
    while (i < n1) {
      events.add(new CopyEvent<T>(new ArrayList<>(List.of(k)), L[i]));
      arr[k] = L[i];
      i++;
      k++;
    }

    /* Copy remaining elements of R[] if any */
    while (j < n2) {
      events.add(new CopyEvent<T>(new ArrayList<>(List.of(k)), R[j]));
      arr[k] = R[j];
      j++;
      k++;
    }
  }

  // helper method for mergeSort
  private static <T extends Comparable<T>> void mergeSortHelper(T[] arr, int l, int r, List<SortEvent<T>> events) {
    if (l < r) {
      // Find the middle point
      int m = (l + r) / 2;

      // Sort first and second halves
      mergeSortHelper(arr, l, m, events);
      mergeSortHelper(arr, m + 1, r, events);

      // Merge the sorted halves
      merge(arr, l, m, r, events);
    }
  }

  /**
   * NOTE: All sorting algorithms share parameters, return values, and pre/postconditions
   * @param arr, the array to be sorted
   * @return a list of sort events associated with the sort
   * 
   * @pre N/A
   * @post 'arr' is modified to be a permutation of itself (before the function was called)
   *       such that for each index 'n' (except for the last), n.compareTo(n+1) <= 0 
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> mergeSort(T[] arr) {
    List<SortEvent<T>> events = new ArrayList<SortEvent<T>>();
    mergeSortHelper(arr, 0, arr.length - 1, events);
    return events;
  }

  // source: https://www.geeksforgeeks.org/quick-sort/
  // see documentation for quickSort
  private static <T extends Comparable<T>> int partition(T[] arr, int low, int high, List<SortEvent<T>> events) {
    T pivot = arr[high];
    int i = (low - 1); // index of smaller element
    for (int j = low; j < high; j++) {
      // If current element is smaller than or
      // equal to pivot
      
      events.add(new CompareEvent<T>(new ArrayList<>(List.of(j, high))));
      if (arr[j].compareTo(pivot) <= 0) {
        i++;
        events.add(new SwapEvent<T>(new ArrayList<>(List.of(i, j))));
        swap(arr, i, j);
      }
    }

    // swap arr[i+1] and arr[high] (or pivot)
    events.add(new SwapEvent<T>(new ArrayList<>(List.of(i + 1, high))));
    swap(arr, i + 1, high);

    return i + 1;
  }

  // helper method for quickSort
  private static <T extends Comparable<T>> void quickSortHelper(T[] arr, int low, int high, List<SortEvent<T>> events) {
    if (low < high) {
      /*
       * pi is partitioning index, arr[pi] is now at right place
       */
      int pi = partition(arr, low, high, events);

      // Recursively sort elements before
      // partition and after partition
      quickSortHelper(arr, low, pi - 1, events);
      quickSortHelper(arr, pi + 1, high, events);
    }
  }

  /**
   * NOTE: All sorting algorithms share parameters, return values, and pre/postconditions
   * @param arr, the array to be sorted
   * @return a list of sort events associated with the sort
   * 
   * @pre N/A
   * @post 'arr' is modified to be a permutation of itself (before the function was called)
   *       such that for each index 'n' (except for the last), n.compareTo(n+1) <= 0 
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> quickSort(T[] arr) {
    List<SortEvent<T>> events = new ArrayList<SortEvent<T>>();
    quickSortHelper(arr, 0, arr.length - 1, events);
    
    return events;
  }

  // source: https://www.geeksforgeeks.org/bubble-sort/
  /**
   * NOTE: All sorting algorithms share parameters, return values, and pre/postconditions
   * @param arr, the array to be sorted
   * @return a list of sort events associated with the sort
   * 
   * @pre N/A
   * @post 'arr' is modified to be a permutation of itself (before the function was called)
   *       such that for each index 'n' (except for the last), n.compareTo(n+1) <= 0 
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> bubbleSort(T[] arr) {
    List<SortEvent<T>> events = new ArrayList<SortEvent<T>>();
        
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        events.add(new CompareEvent<T>(new ArrayList<>(List.of(j + 1, j))));
        if (arr[j + 1].compareTo(arr[j]) < 0) {
          swap(arr, j + 1, j);
          events.add(new SwapEvent<T>(new ArrayList<>(List.of(j + 1, j))));
          // SwapEvent swap = new SwapEvent(new List<Integer> (j+1, i))
        }
      }
    }
    return events;
  }
}
