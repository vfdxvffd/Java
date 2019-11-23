public class Exercise7 {
        final static double PI = Math.PI;      //circular constant

        public static double Area(double r){       //The area of circles
            return PI*Math.pow(r,2);
        }

        public static double Area(int width, int length){    //The area of rectangles
            return width*length;
        }

        public static double Area(double r, double h){         //The area of cylinders
            return PI*Math.pow(r,2)*h;
        }
    public static void main(String[] args) {
        System.out.println("The area of a circle with" +
                " a radius of 20.0 is "+String.format("%.2f",Area(20.0)));
        System.out.println("The area of a rectangle with" +
                " a length of 10 and a width of 20 is "+String.format("%.2f",Area(20,10)));
        System.out.println("The area of a cylinder with" +
                " a radius of 10.0 and a height of 15.0 is "+String.format("%.2f",Area(10.0,15.0)));
        }
}
