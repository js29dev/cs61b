public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    static final double G = 6.67e-11;

    public Body(double xP, double yP, double xV,
            double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        double dx = b.xxPos - this.xxPos;
        double dy = b.yyPos - this.yyPos;
        return Math.sqrt(dx*dx + dy*dy);
    }

    public double calcForceExertedBy(Body b) {
        return G * (this.mass * b.mass) / Math.pow(this.calcDistance(b), 2);
    }

    public double calcForceExertedByX(Body b) {
        double F = this.calcForceExertedBy(b);
        double dx = b.xxPos - this.xxPos;
        return F * (dx / this.calcDistance(b));
    }

    public double calcForceExertedByY(Body b) {
        double F = this.calcForceExertedBy(b);
        double dy = b.yyPos - this.yyPos;
        return F * (dy / this.calcDistance(b));
    }

    public double calcNetForceExertedByX(Body[] array) {
        double netX = 0;
        for (Body b : array) {
            if (!b.equals(this)) {
                netX += this.calcForceExertedByX(b);
            }
        }
        return netX;
    }

    public double calcNetForceExertedByY(Body[] array) {
        double netY = 0;
        for (Body b : array) {
            if (!b.equals(this)) {
                netY += this.calcForceExertedByY(b);
            }
        }
        return netY;
    }

    public void update(double dt, double fX, double fY) {
        // Calculate the acceleration
        double aNetX = fX / this.mass;
        double aNetY = fY / this.mass;

        // Calculate new velocity
        this.xxVel = this.xxVel + dt*aNetX;
        this.yyVel = this.yyVel + dt*aNetY;

        // Calculate new position
        this.xxPos = xxPos + dt*this.xxVel;
        this.yyPos = yyPos + dt*this.yyVel;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}
