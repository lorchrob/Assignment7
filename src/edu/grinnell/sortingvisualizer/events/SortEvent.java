package edu.grinnell.sortingvisualizer.events;

import java.util.List;

// https://stackoverflow.com/questions/52456853/implementing-a-generic-interface-that-extends-from-comparable

// SortEvent<T> interface is the blueprint for the various sort events that occur during
// sorting algorithms. Meant to be used as a log.
public interface SortEvent<T extends Comparable<T>> {
  public void apply(T[] arr);

  public List<Integer> getAffectedIndices();

  public boolean isEmphasized();
}
