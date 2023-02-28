public class Person {
    private int person_id;
    private String name;
    private String surname;
    private String email;
    private double full_cost;

    public Person(int person_id, String name, String surname, String email, double full_cost) {
        this.person_id = person_id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.full_cost = full_cost;
    }

    public double getFull_cost() {
        return full_cost;
    }

    public void setFull_cost(double full_cost) {
        this.full_cost = full_cost;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString(){
        return "Person ID :"+person_id+" Name : "+name+" Surname : "+surname+" Email : "+email +" Full Cost : "+full_cost;
    }
}
