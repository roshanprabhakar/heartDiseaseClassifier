import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DataLoader {

    private static String normalizeLineBreaks(String s) {
        s= s.replace('\u00A0',' '); // remove non-breaking whitespace characters
        s= s.replace('\u2007',' ');
        s= s.replace('\u202F',' ');
        s= s.replace('\uFEFF',' ');

        return s.replace("\r\n", "\n").replace('\r', '\n');
    }

    public static String readFileAsString(String filepath) {
        ClassLoader classLoader = DataLoader.class.getClassLoader();
        File file = new File(classLoader.getResource(filepath).getFile());

        // Read File Content
        String content = "";
        try {
            content = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            System.err.println("FILE NOT FOUND: " + filepath);
            e.printStackTrace();
        }

        return content;
    }

    @SuppressWarnings("Duplicates")
    public static List<DataPoint> loadHeartDiseaseData(String filepath) {
        String data = normalizeLineBreaks(readFileAsString(filepath));
        String[] lines = data.split("\n");

        // create storage for data
        ArrayList<DataPoint> dataset = new ArrayList<>();

        for (int a = 0; a < lines.length; a++) {
            double[] featureVector = new double[13];
            String[] coordinates = lines[a].split(",");
            for (int i = 0; i < 13; i++) {
                featureVector[i] = Double.parseDouble(coordinates[i]);
            }
            dataset.add(new DataPoint(coordinates[13], featureVector));
        }

        return dataset;
    }

    public static void splitDataIntoTrainingAndTest(List<DataPoint> allData, List<DataPoint> emptyTrainingList, List<DataPoint> emptyTestList, double percentTraining) {
        Collections.shuffle(allData);   // This randomizes the order of allData

        // add percentTraining of the elements in allData to emptyTrainingList.
        // add all the rest of the items to emptyTestList

        // NOTE:  percentTraining is between 0 and 1, NOT 0 to 100%.
        // so, e.g., 0.6 represents 60%.

        int endTraining = (int) (allData.size() * percentTraining);
        for (int i = 0; i < endTraining; i++) {
            emptyTrainingList.add(allData.get(i));
        }
        for (int i = endTraining; i < allData.size(); i++) {
            emptyTestList.add(allData.get(i));
        }
    }

    @SuppressWarnings("Duplicates")
    public static List<DataPoint> loadMNistData(String filepath) {
        String data = normalizeLineBreaks(readFileAsString(filepath));
        String[] lines = data.split("\n");

        // create storage for data
        ArrayList<DataPoint> dataset = new ArrayList<>();

        for (int a = 0; a < lines.length; a++) {
             /* TODO:
                For each line in the lines array:
                    split line by , to get all coordinates

                    get the correct label for this data point (the first number in the array).

                    create a 28 x 28 2d array of short

                    fill the 2d array.  First 28 shorts starting at index 1 are row 0, next 28 shorts are row 1, etc.

                    Run the DImage constructor to create a new DImage that's 28 by 28
                    Run the .setPixels method to set its pixels.

                    Run the DataPoint constructor, giving it the correct label and the DImage

                    Add the DataPoint to your dataset list.
         */
        }

        return dataset;
    }
}