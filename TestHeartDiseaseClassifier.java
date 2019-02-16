import java.util.ArrayList;
import java.util.List;

public class TestHeartDiseaseClassifier {
    public static void main(String[] args) {
        Classifier classifier;

        classifier = new Classifier(15);
        List<DataPoint> allData = DataLoader.loadHeartDiseaseData("heart.csv");
        List<DataPoint> training = new ArrayList<>();
        List<DataPoint> test = new ArrayList<>();
        DataLoader.splitDataIntoTrainingAndTest(allData, training, test, .2);

        classifier.addTrainingData(training);

        classifier.test(test);
    }
}