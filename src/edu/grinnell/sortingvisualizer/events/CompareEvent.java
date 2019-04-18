package edu.grinnell.sortingvisualizer.events;

import java.util.List;

public class CompareEvent<T extends Comparable<T>> implements SortEvent<T> {
  // Fields
  private List<Integer> affectedIndices;

  // Constructor
  // initializes fields
  public CompareEvent(List<Integer> affectedIndices) {
    this.affectedIndices = affectedIndices;
  }

  // Methods
  @Override
  /**
   * Applies the sort to the list (compare) (in this case, does nothing).
   * 
   * @param arr, the array to apply the event to
   * @pre arr must contain the indices in 'affectedIndices'
   * @post nothing happens
   */
  public void apply(T[] arr) {
    // applying a compare event does nothing to the list
  }

  @Override
  /**
   * Getter method.
   * 
   * @return affectedIndices, a List<Integer>
   */
  public List<Integer> getAffectedIndices() {
    return affectedIndices;
  }

  /**
   * Always returns false because compare events are not emphasized.
   */
  @Override
  public boolean isEmphasized() {
    // compare events are not emphasized
    return false;
  }
}
