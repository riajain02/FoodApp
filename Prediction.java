import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Prediction
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        String input = "";
        while (!input.equals("quit"))
        {
            input = sc.nextLine();
            System.out.println(checkQuality(input));
        }
    }

    private static String checkQuality(String food)
    {
        try {
            File foodsGood = new File("foods-good.txt");
            File foodsBad = new File("foods-bad.txt");
            Scanner readerGood = new Scanner(foodsGood);
            Scanner readerBad = new Scanner(foodsBad);
            String data;
            String dataMod;
            while (readerGood.hasNextLine()) {
                data = readerGood.nextLine();
                if (data.contains("*"))
                {
                    dataMod = data.substring(0,data.indexOf("*"));
                    if ((food.toLowerCase()).contains(dataMod)) return "true: " + data.substring(data.indexOf("*")+1);
                }
            }
            while (readerBad.hasNextLine()) {
                data = readerBad.nextLine();
                if (data.contains("*"))
                {
                    dataMod = data.substring(0,data.indexOf("*"));
                    if ((food.toLowerCase()).contains(dataMod)) return "false: " + data.substring(data.indexOf("*")+1);
                }
            }
            readerGood.close();
            readerBad.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found.");
            e.printStackTrace();
        }
        return "food not found";
    }
}