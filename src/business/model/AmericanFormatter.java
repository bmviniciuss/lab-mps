package business.model;

public class AmericanFormatter implements DateVisitor{
    @Override
    public void format(Date date) {
        System.out.println(date.getMonth() + "/" + date.getDay() + "/" + date.getYear());
    }
}