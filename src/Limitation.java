public class Limitation {
    int smaller, bigger;

    public Limitation(int i1, int i2){
        smaller = i1;
        bigger = i2;
    }

    @Override
    public String toString() {
        return "Limitation{" +
                "smaller=" + smaller +
                ", bigger=" + bigger +
                '}';
    }
}
