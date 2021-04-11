import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Prediction
{
    public static void main(String args[])
    {
	try {
        	File ingredients = new File("ingredients.txt");
        	Scanner ing = new Scanner(ingredients);
        	ArrayList<String> output = new ArrayList<String>();
        	String text;
        	while(ing.hasNextLine()) {
            	text = ing.nextLine();
            	output.add(text + ": " + checkQuality(text));
        	}
        	for (int i = 0; i < output.size(); i++)
        	{
			System.out.println(output.get(i));
        	}
	}
	catch (FileNotFoundException e) {}
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
                    if ((food.toLowerCase()).contains(dataMod)) return "Great choice! " + data.substring(data.indexOf("*")+1);
                }
            }
            while (readerBad.hasNextLine()) {
                data = readerBad.nextLine();
                if (data.contains("*"))
                {
                    dataMod = data.substring(0,data.indexOf("*"));
                    if ((food.toLowerCase()).contains(dataMod)) return "There might be some better options... " + data.substring(data.indexOf("*")+1);
                }
            }
            readerGood.close();
            readerBad.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found.");
            e.printStackTrace();
        }
        return "This food does not seem to be particularly bad. But, it might be worth doing some independent research on it, just to be sure!";
    }
}
