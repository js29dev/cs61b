
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
}
