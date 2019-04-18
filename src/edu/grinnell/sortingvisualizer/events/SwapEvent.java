package edu.grinnell.sortingvisualizer.events;

import java.util.List;

public class SwapEvent<T extends Comparable<T>> implements SortEvent<T> {

  // Fields
  
  private List<Integer> affectedIndices;
  
  // Constructor
  // initializes fields
  public SwapEvent (List<Integer> affectedIndices) {
    this.affectedIndices = affectedIndices;
  }
  
  // Methods
  /**
   * Applies the sort event to the list (swap).
   * 
   * @param arr, the array to apply the event to
   * @pre arr must contain the indices in 'affectedIndices'
   * @post the array values at the given affected indices are swapped
   */
  @Override
  public void apply(T[] arr) {
    T temp = arr[affectedIndices.get(0)];
    arr[affectedIndices.get(0)] = arr[affectedIndices.get(1)];
    arr[affectedIndices.get(1)] = temp;
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
   * Always returns true because swap events are emphasized.
   */
  @Override
  public boolean isEmphasized() {
    return true;
  }
}
