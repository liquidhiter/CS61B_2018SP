public class NBody {

    private static double inT;
    private static double inDt;
    private static String inDataFilePath;
    public static final String BACKGROUND_IMG = "./images/starfield.jpg";

    /**
     * 
     * @param filePath
     * @return
     */
    public static double readRadius(String filePath) {
        /* Start reading in the given file */
        In in = new In(filePath);

        /* number of planets is not used */
        in.readInt();
        
        /* read radius from the file */
        return in.readDouble();
    }

    /**
     * 
     * @param filePath
     */
    public static Planet[] readPlanets(String filePath) {
        /* Start raeding in the given file */
        In in = new In(filePath);

        /* Skip the first two lines containing planet number and universe radius */
        int numOfPlanets = in.readInt();
        if (numOfPlanets < 0) {
            throw new RuntimeException("Invalid planet number: " + numOfPlanets);
        }
        in.readDouble();

        Planet[] foundPlanets = new Planet[numOfPlanets];
        double xPos = 0.0, yPos = 0.0, xVel = 0.0, yVel = 0.0, mass = 0.0;
        String imgPath = null;
        /*Assumption: given number of planets can be read */
        for (int i = 0; i < numOfPlanets; ++i) {
                /* Non-regex solution */
                xPos = in.readDouble();
                assert(xPos >= 0) : "X position must be non-negative!";
                yPos = in.readDouble();
                assert(yPos >= 0) : "Y position must be non-negative!";
                xVel = in.readDouble();
                yVel = in.readDouble();
                mass = in.readDouble();
                assert(mass > 0) : "Planet needs to have a non-zero mass!";
                imgPath = in.readString();

                /* NOW it's time to create the planet object */
                Planet newPlanet = new Planet(xPos, yPos, xVel, yVel, mass, imgPath);
                foundPlanets[i] = newPlanet;
        }

        return foundPlanets;
    }

    /**
     * 
     * @param args
     */
    private static void getInput(String[] args) {
        assert(args.length == 3);

        // Get all the required parameters
        double T = Double.parseDouble(args[0]);
        if (T < 0) {
            throw new RuntimeException("");
        }

        double dt = Double.parseDouble(args[1]);
        if (dt < 0) {
            throw new RuntimeException("");
        }

        String filePath = args[2];

        // Safe enough to assign all values
        inT = T;
        inDt = dt;
        inDataFilePath = filePath;
    }

    /**
     * 
     */
    private static void drawBackground() {
        /* Get the radius of the universe */
        double radius = readRadius(inDataFilePath);
        assert(radius > 0);
        StdDraw.setScale(-radius,radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, BACKGROUND_IMG);

        /* Shows the drawing to the screen, and waits 2000 milliseconds. */
        StdDraw.show();
        StdDraw.pause(3000);	
    }

    /**
     * 
     */
    private static void drawPlanets() {
        Planet[] planets = readPlanets(inDataFilePath);
        assert(planets.length > 0);

        for (Planet planet : planets ) {
            planet.draw();
        }
    }

    public static void main(String[] args) {
        /* Get input from the command line arguments */
        getInput(args);

        /* Draw the background */
        drawBackground();

        /* Draw planets */
        drawPlanets();
    }
}
