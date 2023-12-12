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
    private static void addRow(TETile[][] tiles, Position p, int size, TETile item) {
        for (int i = 0; i < size; ++i) {
            tiles[p.xPos + i][p.yPos] = item;
        }
    }

    /**
     *
     * @param direction
     * @param p
     * @return
     */
    private static Position calStartPos(int direction, Position p) {
        int startXPos = p.xPos;
        int startYPos = p.yPos;

        if (direction > 0) {
            startXPos -= 1;
        } else if (direction < 0) {
            startXPos += 1;
        }
        startYPos += 1;

        if (startXPos < 0 || startYPos < 0) {
            throw new RuntimeException("Invalid position: (" + p.xPos + ", " + p.yPos + ")");
        }

        return new Position(startXPos, startYPos);
    }

    /**
     * Helper function to return whether increase or decrease the number of items in the line
     * @param indexOfLine
     * @param numOfLines
     * @return
     */
    private static int getDirection(int indexOfLine, int numOfLines) {
        int middleLine = (numOfLines - 1) >> 1;
        if (indexOfLine < middleLine) {
            return +1;
        } else if (indexOfLine == middleLine) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     *
     * @param direction
     * @return
     */
    private static int calSizeStep(int direction) {
        if (direction > 0) {
            return +2;
        } else if (direction == 0) {
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
    public static void addHexagon(TETile[][] tiles, Position p, int size, TETile item) {
        assert(tiles != null && tiles.length >= 4 && tiles[0].length >= 4);
        assert(size >= 2);

        int numOfLines = size << 1;
        int direction = 0;
        Position startPos = p;
        for (int i = 0; i < numOfLines; ++i) {
            /* Calculate the start position in the row */
            startPos = calStartPos(direction, startPos);
            /* Add row */
            addRow(tiles, startPos, size, item);
            /* Get the direction to determine increase or decrease of the line */
            direction = getDirection(i, numOfLines);
            /* Increment of the size */
            int step = calSizeStep(direction);
            size += step;
        }
    }

    public static void debug(TERenderer ter, TETile[][] world) {
        // draws the world to the screen
        ter.renderFrame(world);
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] hexagons = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                hexagons[x][y] = Tileset.NOTHING;
            }
        }

        addHexagon(hexagons, new Position(5, 5), 5, Tileset.FLOWER);

        // draws the world to the screen
        ter.renderFrame(hexagons);
    }
}
