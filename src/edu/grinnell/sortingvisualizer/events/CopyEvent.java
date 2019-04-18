package edu.grinnell.sortingvisualizer.events;

import java.util.List;

public class CopyEvent<T extends Comparable<T>> implements SortEvent<T> {

  // Fields
  
  List<Integer> affectedIndices;
  T copyValue;
  
  // Constructor
  // initializes fields
  public CopyEvent(List<Integer> affectedIndices, T copyValue) {
    this.affectedIndices = affectedIndices;
    this.copyValue = copyValue;
  }
  
  // Methods
  
  /**
   * Applies the sort event to the list (copy).
   * 
   * @param arr, the array to apply the event to
   * @pre arr must contain the indices in 'affectedIndices'
   * @post copyValue is copied into the array at the affected index
   */
  @Override
  public void apply(T[] arr) {
    arr[affectedIndices.get(0)] = copyValue;
  }

  /**
   * Getter method.
   * 
   * @return affectedIndices, a List<Integer>
   */
  @Override
  public List<Integer> getAffectedIndices() {
    return affectedIndices;
  }

  /**
   * Always returns true because copy events are emphasized.
   */
  @Override
  public boolean isEmphasized() {
    return true;
  }

}
