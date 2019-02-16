import java.util.List;

public class TestClassifier {
    public static void main(String[] args) {
        Classifier classifier;
        String prediction = "";

        classifier = new Classifier(5);
        List<DataPoint> training = DataLoader.loadMNistData("mnist_train.csv");
        List<DataPoint> test = DataLoader.loadMNistData("mnist_test.csv");
        classifier.addTrainingData(training);

        classifier.test(test);
    }
}