package sppcw;

public class Driver {
    private String name;
    private int age;
    private int accidents;

    public Driver(String name, int age, int accidents) {
        if(age < 17){
            this.name = "invalid driver";
        } else {
            this.name = name;
        }
        this.age = age;
        this.accidents = accidents;
    }

    public String getName(){
        return name;
    }

    public int riskRating() {
        int rating = 1;
        if (age < 25) {
            rating *= 6;
        }
        if (age >= 75){
            rating *= 3;
        }
        rating += accidents * 3;
        return rating;
    }
}
