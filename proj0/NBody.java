public class NBody {

	private static double inT;
	private static double inDt;
	private static String inDataFilePath;
	private static final String BACKGROUND_IMG = "./images/starfield.jpg";
	private static final int PAUSE_INTERVAL = 10; /* milliseconds */

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
		double radius = in.readDouble();
	
		/* release the file handler */
		in.close();

		return radius;
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

		/* close the file handler */
		in.close();

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
	 * @param radius
	 */
	private static void drawBackground(double radius) {
		StdDraw.setScale(-radius,radius);
		StdDraw.picture(0, 0, BACKGROUND_IMG);
	}

	/**
	 * 
	 */
	private static void drawPlanets(Planet[] planets) {
		for (Planet planet : planets ) {
			planet.draw();
		}
	}

	/**
	 * 
	 * @param xForces
	 * @param yForces
	 */
	private static void updateNetForces(double[] xForces, double[] yForces, Planet[] planets) {
		/* Sanity check */
		assert(xForces != null);
		assert(yForces != null);
		assert(planets != null);

		/* Invarint: index of net force keeps the same as the index of planet */
		int numOfPlanets = planets.length;
		for (int i = 0; i < numOfPlanets; ++i) {
			xForces[i] = planets[i].calcNetForceExertedByX(planets);
			yForces[i] = planets[i].calcNetForceExertedByY(planets);
		}
	}

	/**
	 * 
	 * @param planets
	 * @param xForces
	 * @param yForces
	 * @param second
	 */
	private static void updatePlanets(Planet[] planets, double[] xForces, double[] yForces, double second) {
		/* Sanity check */
		int numOfPlanets = planets.length;
		/* Assumption: index of net force keeps the same as the index of planet */
		for (int i = 0; i < numOfPlanets; ++i) {
			planets[i].update(second, xForces[i], yForces[i]);
		}
	}

	/**
	 * 
	 * @param universe
	 */
	private static void printUniverse(Planet[] universe, double radius) {
		assert(universe != null);
		StdOut.printf("%d\n", universe.length);
		StdOut.printf("%.2e\n", radius);
		for (Planet p : universe) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
		}
	}

	/**
	 * 
	 */
	private static void startSimu() {

		/* First get planets in the current unviverse */
		Planet[] planets = readPlanets(inDataFilePath);
		assert(planets.length > 0);

		/* Get the radius of the universe */
		double radius = readRadius(inDataFilePath);
		assert(radius > 0);

		/* Enable the buffering globally */
		StdDraw.enableDoubleBuffering();

		/* Clear the window */
		StdDraw.clear();

		/* Container storing the net forces */
		double[] xForces = new double[planets.length];
		double[] yForces = new double[planets.length];

		double timer = 0.0;
		while ((inT - timer) >= inDt) {

			/* Calculate the net forces */
			updateNetForces(xForces, yForces, planets);

			/* Update planets status with new forces ? */
			updatePlanets(planets, xForces, yForces, inDt);

			/* draw the background */
			drawBackground(radius);

			/* draw all planets */
			drawPlanets(planets);

			/* show the screen buffer */
			StdDraw.show();
			StdDraw.pause(PAUSE_INTERVAL);

			/* increase timer by interval specified by inDt */
			timer += inDt;
		}

		/* Print the universe as you want */
		printUniverse(planets, radius);
	}

	public static void main(String[] args) {
		/* Get input from the command line arguments */
		getInput(args);

		/* Lets simulate the universe!!! */
		startSimu();
	}
}
