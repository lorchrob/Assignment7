package edu.grinnell.sortingvisualizer.audio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * A collection of indices into a Scale object. These indices are the subject of the various sorting
 * algorithms in the program.
 */
public class NoteIndices {

  // Fields
  private Integer[] indices;
  private boolean[] highlightedIndices;
  
  // Constructor
  /**
   * @param n the size of the scale object that these indices map into
   */
  public NoteIndices(int n) {
    this.indices = new Integer[n];
    this.highlightedIndices = new boolean[n];
    clearAllHighlighted();
  }

  
  // Methods
  
  // source: https://www.programcreek.com/2012/02/java-method-to-shuffle-an-int-array-with-random-order/
  public static Integer[] RandomizeArray(Integer[] array){
    Random rgen = new Random();  // Random number generator         

    for (int i=0; i<array.length; i++) {
        int randomPosition = rgen.nextInt(array.length);
        int temp = array[i];
        array[i] = array[randomPosition];
        array[randomPosition] = temp;
    }

    return array;
}
  
  /**
   * Reinitializes this collection of indices to map into a new scale object of the given size. The
   * collection is also shuffled to provide an initial starting point for the sorting process.
   * 
   * @param n the size of the scale object that these indices map into
   */
  public void initializeAndShuffle(int n) {
    this.indices = new Integer[n];
    this.highlightedIndices = new boolean[n];
    for (int i = 0; i < n; i++) {
      indices[i] = i;
    }
    
    this.indices = RandomizeArray(this.indices);
    clearAllHighlighted();
  }

  /** @return the indices of this NoteIndices object */
  public Integer[] getNotes() {
    return this.indices;
  }

  /**
   * Highlights the given index of the note array
   * 
   * @param index the index to highlight
   */
  public void highlightNote(int index) {
    this.highlightedIndices[index] = true;
  }

  /** @return true if the given index is highlighted */
  public boolean isHighlighted(int index) {
    return this.highlightedIndices[index];
  }

  /** Clears all highlighted indices from this collection */
  public void clearAllHighlighted() {
   for (int i = 0; i < this.highlightedIndices.length; i++) {
     this.highlightedIndices[i] = false;
   }
  }
}
