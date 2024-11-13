public class Vector2DMath {
  public static double magnitude(double x, double y) {
    return Math.sqrt(x*x + y*y);
  }
  
  public static double[] normal(double x, double y) {
    double length = magnitude(x, y);
    return new double[] {x * 1.0/length, y * 1.0/length};
  }
  
  public static double[] reflect(double[] N, double x, double y) {
      double rx = x - 2.0 * (N[0]*x + N[1]*y)*N[0];
      double ry = y - 2.0 * (N[0]*x + N[1]*y)*N[1];
      return new double[] {rx, ry};
  }
}
