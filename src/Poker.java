/**
 * Created by lenovo on 2016/10/9.
 */
public class Poker {
    String color;
    String point;
    int number;

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void equal(Poker poker){
        color = poker.getColor();
        point = poker.getPoint();
        number = poker.getNumber();
    }
}
