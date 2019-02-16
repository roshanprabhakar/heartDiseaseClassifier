import java.lang.reflect.Array;
import java.util.*;

public class Classifier {
    private ArrayList<DataPoint> trainingData;
    private int k;

    public Classifier(int k) {
        this.k = k;
        trainingData = new ArrayList<DataPoint>();
    }

    public void addTrainingData(List<DataPoint> points) {
        for (DataPoint p : points) addTrainingData(p);
    }

    public void addTrainingData(DataPoint point) {
        trainingData.add(point);
    }

    //k = 1
    public String classify(double[] featureVector) {
        if (trainingData.size() == 0) return "no training data";

        String[] possibleLabels = new String[k];
        List<DataPoint> removed = new ArrayList<>();

        for (int i = 0; i < possibleLabels.length; i++) {
            DataPoint closest = closest(featureVector);
            possibleLabels[i] = closest.getLabel();
            trainingData.remove(closest);
            removed.add(closest);
        }
        addTrainingData(removed);

        System.out.println(Arrays.toString(possibleLabels));

        return closest(featureVector).getLabel();
    }

    private String mode(String[] labels) {
        int[] counts = new int[2];
        for (int i = 0; i < labels.length; i++) {
            counts[Integer.parseInt(labels[i])]++;
        }
        if (counts[0] > counts[1]) return "0";
        else return "1";
    }

    private DataPoint closest(double[] testVector) {
        DataPoint closest = new DataPoint("not data", new double[14]);
        double minDistance = Double.MAX_VALUE;
        for (DataPoint dp : trainingData) {
            if (distance(testVector, dp.getData()) <= minDistance) {
                closest = dp;
                minDistance = distance(testVector, dp.getData());
            }
        }
        return closest;
    }

    public double distance(double[] d1, double[] d2) {
        double sum = 0;

        for (int i = 0; i < d1.length; i++) {
            sum += (d1[i] - d2[i]) * (d1[i] - d2[i]);
        }

        return Math.sqrt(sum);
    }

    public void test(List<DataPoint> test) {
        ArrayList<DataPoint> correct = new ArrayList<>();
        ArrayList<DataPoint> wrong = new ArrayList<>();

        int i = 0;
        for (DataPoint p : test) {
            String predict = classify(p.getData());
            System.out.print("#" + i + " REAL:\t" + p.getLabel() + " predicted:\t" + predict);
            if (predict.equals(p.getLabel())) {
                correct.add(p);
                System.out.print(" Correct ");
            } else {
                wrong.add(p);
                System.out.print(" WRONG ");
            }

            i++;
            System.out.println(" % correct: " + ((double) correct.size() / i));
        }

        System.out.println(correct.size() + " correct out of " + test.size());
        System.out.println(wrong.size() + " wrong out of " + test.size());
        System.out.println("% Error: " + (double) wrong.size() / test.size());
    }
}
