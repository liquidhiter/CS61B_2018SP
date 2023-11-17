public class Planet {

    /* Constant */
    private static final double GRAVITYCONSTANT = 6.67E-11;

    /* class members */
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    /*===================================== Constructors =====================================*/
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /*===================================== calcDistance =====================================*/
    /**
     * 
     * @param xPos
     * @return
     */
    private double calxDistance(double xPos) {
        return xxPos >= xPos ? xxPos - xPos : xPos - xxPos;
    }

    /**
     * 
     * @param yPos
     * @return
     */
    private double calyDistance(double yPos) {
        return yyPos >= yPos ? yyPos - yPos : yPos - yyPos;
    }

    /**
     * 
     * @param val
     * @return
     */
    private double calSquare(double val) {
        return val *= val;
    }

    /**
     * 
     * @param p
     * @return
     */
    public double calcDistance(Planet p) {
        assert(p != null);
        double xDist = calxDistance(p.xxPos);
        double yDist = calyDistance(p.yyPos);

        return Math.sqrt(calSquare(xDist) + calSquare(yDist));
    }

    /*===================================== calcForceExertedBy =====================================*/
    /**
     * 
     * @param p
     * @return
     * @invariant: always return non-negative value
     */
    public double calcForceExertedBy(Planet p) {
        assert(p != null);
        assert(p != this);
        
        double dist = calcDistance(p);
        return GRAVITYCONSTANT * this.mass * p.mass / (dist * dist);
    }

    /*===================================== calcForceExertedByX and calcForceExertedByY =====================================*/
    /**
     * 
     * @param p
     * @return
     */
    public double calcForceExertedByX(Planet p) {
        assert(p != null);
        assert(p != this);

        double forceExertedBy = calcForceExertedBy(p);
        /* Duplicated calculation of the distance? */
        double dist = calcDistance(p);

        return forceExertedBy * (p.xxPos - this.xxPos) / dist;
    }

    /**
     * 
     * @param p
     * @return
     */
    public double calcForceExertedByY(Planet p) {
        assert(p != null);
        assert(p != this);

        double forceExertedBy = calcForceExertedBy(p);
        /* Duplicated calculation of the distance? */
        double dist = calcDistance(p);

        return forceExertedBy * (p.yyPos - this.yyPos) / dist;
    }

    /*===================================== calcNetForceExertedByX  and calcNetForceExertedByY =====================================*/
    public double calcNetForceExertedByX(Planet[] planets) {
        double netForxtX = 0.0;
        for (Planet p : planets) {

            /*Planets cannot exert gravitational forces on themselves!*/
            if (p.equals(this)) {
                continue;
            }
            netForxtX += calcForceExertedByX(p);
        }
        return netForxtX;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double netForxtY = 0.0;
        for (Planet p : planets) {

            /*Planets cannot exert gravitational forces on themselves!*/
            if (p.equals(this)) {
                continue;
            }
            netForxtY += calcForceExertedByY(p);
        }
        return netForxtY;
    }

    /*==================================== update ===================================*/
    public void update(double seconds, double extForceX, double extForceY) {
        // Sanity check of the input
        assert(seconds > 0) : "Given time should be a non-negative number!";

        // Calculate accerlation speed
        double accNetX = extForceX / mass;
        double accNetY = extForceY / mass;

        // Calculate new speed
        xxVel += (seconds * accNetX);
        yyVel += (seconds * accNetY);

        // Calculate new positions
        xxPos += (xxVel * seconds);
        yyPos += (yyVel * seconds);
    }
}
