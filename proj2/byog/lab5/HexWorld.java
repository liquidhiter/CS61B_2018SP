package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static class Position {
        protected int xPos;
        protected int yPos;

        public Position(int x, int y) {
            xPos = x;
            yPos = y;
        }
    }

    /**
     *
     * @param line
     * @param startPos
     * @param size
     * @param item
     */
    private void addRow(TETile[] line, int startPos, int size, TETile item) {
        for (int i = 0; i < size; ++i) {
            line[startPos + i] = item;
        }
    }

    private Position calStartPos(Position p) {
        int startXPos = p.xPos - 1;
        int startYPos = p.yPos - 1;
        if (startXPos < 0 || startYPos < 0) {
            throw new RuntimeException("Invalid position: (" + p.xPos + ", " + p.yPos + ")");
        }

        return new Position(startXPos, startYPos);
    }

    /**
     *
     * @param indexOfLine
     * @param numOfLines
     * @return
     */
    private int calSizeStep(int indexOfLine, int numOfLines) {
        int middleLine = (numOfLines - 1) / 2;
        if (indexOfLine < middleLine) {
            return +2;
        } else if (indexOfLine == middleLine) {
            return 0;
        } else {
            return -2;
        }
    }

    /**
     *
     * @param tiles
     * @param p: bottom left-most
     * @param size
     */
    public void addHexagon(TETile[][] tiles, Position p, int size, TETile item) {
        assert(tiles != null && tiles.length >= 4 && tiles[0].length >= 4);
        assert(size >= 2);

        int numOfLines = size << 1;
        for (int i = 0; i < numOfLines; ++i) {
            /* Increment of the size */
            int step = calSizeStep(i, numOfLines);
            /* Calculate the start position in the row */
            Position startPos = calStartPos(p);4                                                                                                                                                                                                                                                                                 g
            /* Add row */
            addRow(tiles[tiles.length - startPos.yPos], startPos.xPos, size, item);
            size += step;
        }

    }

    @Test
    public void testCalSizeStep() {
        assertEquals(2, calSizeStep(0, 4));
        assertEquals(0, calSizeStep(1, 4));
        assertEquals(-2, calSizeStep(2, 4));

        assertEquals(2, calSizeStep(0, 6));
        assertEquals(2, calSizeStep(1, 6));
        assertEquals(0, calSizeStep(2, 6));
        assertEquals(-2, calSizeStep(3, 6));
        assertEquals(-2, calSizeStep(4, 6));
    }


    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
    }
}
