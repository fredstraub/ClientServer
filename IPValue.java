import java.io.Serializable;

public class IPValue implements Serializable{

    private int one;
    private int two;
    private int three;
    private int four;

    public IPValue(int first, int second, int third, int fourth) {
        one = first;
        two = second;
        three = third;
        four = fourth;
    }

    public int getOne() {
        return one;
    }

    public int getTwo() {
        return two;
    }

    public int getThree() {
        return three;
    }

    public int getFour() {
        return four;
    }

    public void SetOne(int first) {
        one = first;
    }

    public void setTwo(int second) {
        two = second;
    }

    public void setThree(int third) {
        three = third;
    }
    public void setFour(int fourth) {
        four = fourth;
    }
}
