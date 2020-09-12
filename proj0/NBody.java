
public class NBody {

    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int N = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String fileName) {
        In in = new In(fileName);
        int N = in.readInt();
        Body[] bodies = new Body[N];
        int i = 0;
        double radius = in.readDouble();

        while (i < N) {

            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String file = in.readString();

            bodies[i] = new Body(xP, yP, xV, yV, m, file);

            i += 1;
        }
        return bodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);
        int numberOfBodies = bodies.length;

        double t = 0;
        double[] Fx = new double[numberOfBodies];
        double[] Fy = new double[numberOfBodies];

        StdDraw.enableDoubleBuffering();

        while (t < T) {
            StdDraw.setScale(-radius, radius);
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (int i = 0; i < numberOfBodies; i++) {
                Fx[i] = bodies[i].calcNetForceExertedByX(bodies);
                Fy[i] = bodies[i].calcNetForceExertedByY(bodies);
            }

            for (int i = 0; i < numberOfBodies; i++) {
                bodies[i].update(dt, Fx[i], Fy[i]);
                bodies[i].draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }

    }
}
