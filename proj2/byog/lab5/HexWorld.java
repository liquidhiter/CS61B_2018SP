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

    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    private static final Random RANDOM = new Random();

    private static final TETile[] tilesSupported = {Tileset.UNLOCKED_DOOR, Tileset.FLOWER, Tileset.FLOOR,
                                                    Tileset.GRASS, Tileset.WALL, Tileset.LOCKED_DOOR,
                                                    Tileset.MOUNTAIN, Tileset.PLAYER,
                                                    Tileset.SAND, Tileset.TREE, Tileset.WATER};


    private static class Position {
        protected int xPos;
        protected int yPos;

        public Position(int x, int y) {
            xPos = x;
            yPos = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (!(obj instanceof Position)) {
                return false;
            }

            Position other = (Position) obj;
            return other.xPos == this.xPos && other.yPos == this.yPos;

        }
    }

    /**
     * Add number of TETiles starting from the given position to the row array
     * @param tiles
     * @param p
     * @param size
     * @param item
     */
    private static void addRow(TETile[][] tiles, Position p, int size, TETile item) {
        for (int i = 0; i < size; ++i) {
            /*Only add the new tile if it hasn't been added before*/
            if (tiles[p.xPos + i][p.yPos].equals(Tileset.NOTHING)) {
                tiles[p.xPos + i][p.yPos] = item;
            }
        }
    }

    /**
     * Calculate the start position of the given TETile
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

    /**
     *                   ###
     *                  #####
     *                 #######      --> middle
     *                 #######      --> middle
     *                  #####
     *                   ###
     * Return the length of middle line from a horizontal view
     * @param size
     * @return
     */
    private static int calHorizontalMiddleLength(int size) {
        int middleLength = size;
        for (int i = 0; i < size - 1; ++i) {
            middleLength += 2;
        }

        /* By observation */
        assert(middleLength == ((size << 1) + (size - 2)));

        return middleLength;
    }

    /**
     *                   ###
     *                  #####
     *                 #######
     *                 #######
     *                  #####
     *                   ###
     *                   |
     *                   middle
     *
     * Return the minimum width needed to accommodate given number of hexagons (only support 19 for now)
     * @param size
     * @return
     */
    private static int calVerticalMiddleLength(int size) {
        int middleLength = 2;
        for (int i = 0; i < size - 1; ++i) {
            middleLength += 2;
        }

        return middleLength;
    }

    private static int calWidthNeeded(int size) {
        int middleLength = calHorizontalMiddleLength(size);
        /* For example: 7 -> 3 -> 7 -> 3 -> 7, when size is 3 */
        return ((size + middleLength) << 1) + middleLength;
    }


    /**
     * Return the minimum height needed to accommodate given number of hexagons (only support 19 for now)
     * @param size
     * @return
     */
    private static int calHeightNeeded(int size) {
        /*TODO: 5 is the number of hexagons in the middle layer when printing 19 hexagons */
        return calVerticalMiddleLength(size) * 5;
    }

    /**
     *
     * @param size
     * @param width
     * @return
     */
    private static int calBottomXPos(int size, int width) {
        return (width >> 1) - (size >> 1);
    }

    /**
     *
     * @param size
     * @param height
     * @return
     */
    private static int calBottomYPos(int size, int height) {
        return (height - calHeightNeeded(size)) >> 1;
    }

    /**
     *
     * @param bottomXPos
     * @param size
     * @return
     */
    private static int calLeftMostXPos(int bottomXPos, int size) {
        int middleLength = calHorizontalMiddleLength(size);
        return bottomXPos - (middleLength + size + ((middleLength - size) >> 1));
    }

    /**
     *
     * @param bottomXPos
     * @param size
     * @return
     */
    private static int calRightMostXPos(int bottomXPos, int size) {
        return calLeftMostXPos(bottomXPos, size) + calWidthNeeded(size);
    }

    /**
     *
     * @param bottomYPos
     * @param size
     * @return
     */
    private static int calTopMostYPos(int bottomYPos, int size) {
        return bottomYPos + calHeightNeeded(size);
    }

    /**
     *
     * @return
     */
    public static TETile getRandTile() {
        return tilesSupported[RANDOM.nextInt(1024) % tilesSupported.length];
    }

    /**
     *
     * @return
     */
    private static Position calPosOfLeftNeighbour(int currXPos, int currYPos, int size) {
        int xPos = currXPos - (((calHorizontalMiddleLength(size) - size) >> 1) + size);
        int yPos = currYPos + size;
        return new Position(xPos, yPos);
    }

    /**
     *
     * @return
     */
    private static Position calPosOfRightNeighbour(int currXPos, int currYPos, int size) {
        int xPos = currXPos + size + ((calHorizontalMiddleLength(size) - size) >> 1);
        int yPos = currYPos + size;
        return new Position(xPos, yPos);
    }

    /**
     *
     * @return
     */
    private static Position calPosOfTopNeighbour(int currXPos, int currYPos, int size) {
        int yPos = currYPos + calVerticalMiddleLength(size);
        return new Position(currXPos, yPos);
    }


    /**
     * Recursively draw each hexagon by following the ways:
     * 1> draw itself specified by the position (xPos, yPos)
     * 2> draw the left neighbour
     * 3> draw the right neighbour
     * 4> draw the top neighbour
     *
     * TODO: Optimization the recursive function call of adding hexagon at the same position
     *       NOTE: hexagons are not drawn twice but only the recursive function calls are redundant
     *
     *       Some helper functions are called for several times, which indicates some values are commonly-used.
     *       Why not adding them as the member variable of class? i.e. class HexWorld
     *
     * @param xPos
     * @param yPos
     * @param size
     * @param xPosLeftBoundary
     * @param xPosRightBoundary
     * @param yPosTopBoundary
     */
    private static void addHexagonsRecursively(TETile[][] hexagons, int xPos, int yPos, int size, int xPosLeftBoundary, int xPosRightBoundary, int yPosTopBoundary, int heightTopOffset, int widthLeftOffset, int widthRightOffset) {
        if (xPos - widthLeftOffset < xPosLeftBoundary || xPos + widthRightOffset > xPosRightBoundary || yPos + heightTopOffset > yPosTopBoundary) {
            return;
        }

        /*Special case: the left-most and right most hexagon can be placed at the top-most, but this is not wanted*/
        if (yPos + heightTopOffset == yPosTopBoundary && (xPos - widthLeftOffset == xPosLeftBoundary || xPos + widthRightOffset == xPosRightBoundary)) {
            return;
        }

        System.out.println("LOG: xPos = " + xPos + ", yPos = " + yPos + ", xPosLeftBoundary = " + xPosLeftBoundary + ", xPosRightBoundary = " + xPosRightBoundary + ", yPosTopBoundary = " + yPosTopBoundary );

        /*Get the random tile*/
        TETile tileType = getRandTile();

        /*Draw the hexagon at the position of (xPos, yPos)*/
        addHexagon(hexagons, new Position(xPos, yPos), size, tileType);

        /*Calculate the position of neighbours*/
        Position left = calPosOfLeftNeighbour(xPos, yPos, size);
        Position right = calPosOfRightNeighbour(xPos, yPos, size);
        Position top = calPosOfTopNeighbour(xPos, yPos, size);

        /* Add left, right and top neighbour hexagons */
        addHexagonsRecursively(hexagons, left.xPos, left.yPos, size, xPosLeftBoundary, xPosRightBoundary, yPosTopBoundary, heightTopOffset, widthLeftOffset, widthRightOffset);
        addHexagonsRecursively(hexagons, right.xPos, right.yPos, size, xPosLeftBoundary, xPosRightBoundary, yPosTopBoundary, heightTopOffset, widthLeftOffset, widthRightOffset);
        addHexagonsRecursively(hexagons, top.xPos, top.yPos, size, xPosLeftBoundary, xPosRightBoundary, yPosTopBoundary, heightTopOffset, widthLeftOffset, widthRightOffset);
    }

    /**
     *
     * @param hexagons
     * @param xPos
     * @param yPos
     * @param size
     */
    public static void drawHexagons(TETile[][] hexagons, int xPos, int yPos, int size) {
        /* Calculate the xPos of left-most hexagons, right-most hexagons,
         * the yPos of the top-most hexagons
         */
        int xPosLeftMost = calLeftMostXPos(xPos, size);
        int xPostRightMost = calRightMostXPos(xPos, size);
        int yPostTopMost = calTopMostYPos(yPos, size);
        int heightTopOffset = calVerticalMiddleLength(size);
        int widthLeftOffset = (calHorizontalMiddleLength(size) - size) >> 1;
        int widthRightOffset = size + widthLeftOffset;

        /* Add all 19 hexagons */
        addHexagonsRecursively(hexagons, xPos, yPos, size, xPosLeftMost, xPostRightMost, yPostTopMost, heightTopOffset, widthLeftOffset, widthRightOffset);

    }

    /**
     * NOTE: fixed pattern is followed for now
     *
     *       from bottom to top: 1->2 ->3->2->3->2->3 ->2->1
     *       totally: 1 + 2 + (3 + 2 + 3 + 2 + 3) + 2 + 1 = 19
     *
     *       from left to right: 3->4->5->4->3
     *       totally: 19
     *
     * Procedures of tesselating hexagons:
     * 1> calculate the minimum width and height needed to accommodate 19 hexagons (optional with assuming a very large grid)
     * 2> calculate the (xpos, ypos) of the bottom-most hexagon
     * 3> draw hexagons from the bottom one
     *    - draw its neighbour at the left (not exceed the width)
     *    - draw its neighbour at the right (not exceed the width)
     *    - draw its neighbour at the top (not exceed the height)
     *
     * How to check the condition of `not exceed the width / height` when the given grid is larger than needed?
     * - SOLUTION: always calculate the minimum width and height, thus, number of hexagons is not hard-coded
     *
     * TODO: add support for drawing different numbers of hexagons
     *
     * @param size
     */
    public static void tesselateHexagons(TETile[][] hexagons, int size, int width, int height) {

        /*Calculate the minimum width and height*/
        int widthMinimum = calWidthNeeded(size);
        int heightMinimum = calHeightNeeded(size);

        System.out.println("LOG: width minimum = " + widthMinimum + ", height minimum = " + heightMinimum);

        if (width <= widthMinimum) {
            throw new IllegalArgumentException("Given width is too small to accommodate all hexagons!");
        }

        if (height <= heightMinimum) {
            throw new IllegalArgumentException("Given heigth is too small to accommodate all hexagons!");
        }

        /*Calculate the xpos of the bottom-most hexagon: scale to the given width*/
        int xPos = calBottomXPos(size, width);
        int yPos = calBottomYPos(size, height);

        /*Draw hexagon from the bottom to the top*/
        drawHexagons(hexagons, xPos, yPos, size);
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

//         addHexagon(hexagons, new Position(5, 5), 5, Tileset.FLOWER);

        /*Draw a tesselation of hexagons*/
        tesselateHexagons(hexagons, 6, WIDTH, HEIGHT);

        // draws the world to the screen
        ter.renderFrame(hexagons);
    }
}
