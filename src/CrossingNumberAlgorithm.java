import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CrossingNumberAlgorithm {
    public static boolean PointInPolygon(Double PointX, Double PointY, ArrayList<Double> ArrayOfCoordinates){

        Integer NumberOfIntersections = 0;


        Double det0;
        Double detU;
        Double detV;

        Double PointXEnd = -10000000.0;
        Double PointYEnd = -10000001.0;

        Double u;
        Double v;

        for (int i = 0; i < ArrayOfCoordinates.size() - 2; i = i + 2) {

            det0 = (ArrayOfCoordinates.get(i + 2) - ArrayOfCoordinates.get(i)) * (PointY - PointYEnd) - (ArrayOfCoordinates.get(i + 3) - ArrayOfCoordinates.get(i + 1)) * (PointX - PointXEnd);
            detU = (PointX - ArrayOfCoordinates.get(i)) * (PointY - PointYEnd) - (PointY - ArrayOfCoordinates.get(i + 1)) * (PointX - PointXEnd);
            detV = (ArrayOfCoordinates.get(i + 2) - ArrayOfCoordinates.get(i)) * (PointY - ArrayOfCoordinates.get(i + 1)) - (ArrayOfCoordinates.get(i + 3) - ArrayOfCoordinates.get(i + 1)) * (PointX - ArrayOfCoordinates.get(i));

            u = detU / det0;
            v = detV / det0;

            if (u > 0 && u < 1 && v > 0 && v < 1) {
                NumberOfIntersections += 1;
            }

        }

        det0 = (ArrayOfCoordinates.get(0) - ArrayOfCoordinates.get(ArrayOfCoordinates.size() - 2)) * (PointY - PointYEnd) - (ArrayOfCoordinates.get(1) - ArrayOfCoordinates.get(ArrayOfCoordinates.size() - 1)) * (PointX - PointXEnd);
        detU = (PointX - ArrayOfCoordinates.get(ArrayOfCoordinates.size() - 2)) * (PointY - PointYEnd) - (PointY - ArrayOfCoordinates.get(ArrayOfCoordinates.size() - 1)) * (PointX - PointXEnd);
        detV = (ArrayOfCoordinates.get(0) - ArrayOfCoordinates.get(ArrayOfCoordinates.size() - 2)) * (PointY - ArrayOfCoordinates.get(ArrayOfCoordinates.size() - 1)) - (ArrayOfCoordinates.get(1) - ArrayOfCoordinates.get(ArrayOfCoordinates.size() - 1)) * (PointX - ArrayOfCoordinates.get(ArrayOfCoordinates.size() - 2));

        u = detU / det0;
        v = detV / det0;

        if (u > 0 && u < 1 && v > 0 && v < 1) {
            NumberOfIntersections += 1;
        }

        return NumberOfIntersections % 2 == 1;
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        Scanner input = new Scanner(new File("input.txt"));
        PrintWriter output = new PrintWriter("output.txt", "UTF-8");

        try {
            String Coordinates = input.nextLine();
            String Point = input.nextLine();

            //Parsing 1st line and entering values in ArrayList

            while (Coordinates.contains("(")) {
                Coordinates = Coordinates.replace("(", "");
            }

            while (Coordinates.contains(")")) {
                Coordinates = Coordinates.replace(")", "");
            }

            Coordinates = Coordinates.replace("{", "");
            Coordinates = Coordinates.replace("}", "");

            ArrayList<Double> ArrayOfCoordinates = new ArrayList<>();

            for (String StringCoordinate : Coordinates.split(",")) {
                Double Coordinate = Double.parseDouble(StringCoordinate);
                ArrayOfCoordinates.add(Coordinate);

            }

            Integer NumberOfIntersections = 0;

            //Parsing point's coordinates

            Point = Point.replace("(", "");
            Point = Point.replace(")", "");

            String[] PointXY = Point.split(",");

            Double PointX = Double.parseDouble(PointXY[0]);
            Double PointY = Double.parseDouble(PointXY[1]);

            Boolean InArea = PointInPolygon(PointX, PointY, ArrayOfCoordinates);

            output.println(InArea ? "Yes" : "No");

            Double MinX = ArrayOfCoordinates.get(0);
            Double MinY = ArrayOfCoordinates.get(1);
            Double MaxX = ArrayOfCoordinates.get(0);
            Double MaxY = ArrayOfCoordinates.get(1);

            DecimalFormat df = new DecimalFormat("#.#");
            df.setRoundingMode(RoundingMode.HALF_UP);
            df.setMinimumFractionDigits(0);


            //Finding min and max X

            for (int i = 0; i < ArrayOfCoordinates.size(); i = i + 2) {
                double x = ArrayOfCoordinates.get(i);

                if (x > MaxX) {
                    MaxX = x;
                }

                if (x < MinX) {
                    MinX = x;
                }
            }

            //Finding min and max Y

            for (int i = 1; i < ArrayOfCoordinates.size(); i = i + 2) {
                double y = ArrayOfCoordinates.get(i);

                if (y > MaxY) {
                    MaxY = y;
                }

                if (y < MinY) {
                    MinY = y;
                }
            }

            Double AverageX = MaxX - MinX;
            Double AverageY = MaxY - MinY;

            Double AreaOfSquare = AverageX * AverageY;

            Double PointsInPolygon = 0.0;
            Double AmountOfPoints = 0.0;

            Random random = new Random();
            Double Area = (AreaOfSquare * PointsInPolygon) / AmountOfPoints;

            while (true) {

                AmountOfPoints += 1;

                Double x = MinX + random.nextDouble() * AverageX;
                Double y = MinY + random.nextDouble() * AverageY;

                if (PointInPolygon(x, y, ArrayOfCoordinates)) {
                    PointsInPolygon += 1;
                }

                Double PreviousArea = Area;
                Area = (AreaOfSquare * PointsInPolygon) / AmountOfPoints;

                if (AmountOfPoints < 20)
                    continue;

                if (Math.abs(PreviousArea - Area) < 0.000001) {
                    break;
                }
            }

            Area = (AreaOfSquare * PointsInPolygon) / AmountOfPoints;

            output.print(df.format(Area));
            output.close();
        }
        catch (Exception e) {
            output.print("No");
            output.close();
        }
    }
}