package edu.grinnell.sortingvisualizer.rendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import edu.grinnell.sortingvisualizer.audio.NoteIndices;

@SuppressWarnings("serial")
public class ArrayPanel extends JPanel {

    private NoteIndices notes;
    
    /**
     * Constructs a new ArrayPanel that renders the given note indices to
     * the screen.
     * @param notes the indices to render
     * @param width the width of the panel
     * @param height the height of the panel
     */
    public ArrayPanel(NoteIndices notes, int width, int height) {
        this.notes = notes;
        this.setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paintComponent(Graphics g) {
      g.clearRect(0, 0, this.getWidth(), this.getHeight()); // this function pointed out to us by Zixuan 
                                                            // and Grace
      
      int x = 0;
      int width = this.getWidth()/this.notes.getNotes().length;
      int height;
      
        for (Integer note: this.notes.getNotes()) {
          g.setColor(new Color(124, 252, (note * 255) / this.notes.getNotes().length));
          
          height = (note*this.getHeight())/this.notes.getNotes().length;         
          g.drawRect(x, this.getHeight() - height, width, height); // x, y, width, length
          
          if (this.notes.isHighlighted(note)) {
            g.fillRect(x, this.getHeight() - height, width, height); // x, y, width, length
          } // if
          x += this.getWidth()/this.notes.getNotes().length;
        } // for
    }
}