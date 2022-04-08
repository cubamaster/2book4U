package Models;

public class Notification { // класс уведомлений
    private int id;
    private String title;
    private int userid;
    private String date;
    private String time;
    private String number;
    private String restaurantname;
    private String restaurantlocation;
    private String restaurantkitchen;
    private String restaurantrating;
    private String restaurantphone;

    // элеметы уведомления

    public String getRestaurantphone() {
        return restaurantphone;
    }

    public void setRestaurantphone(String restaurantphone) {
        this.restaurantphone = restaurantphone;
    }

    public String getRestaurantname() {
        return restaurantname;
    }

    public void setRestaurantname(String restaurantname) {
        this.restaurantname = restaurantname;
    }

    public String getRestaurantlocation() {
        return restaurantlocation;
    }

    public void setRestaurantlocation(String restaurantlocation) {
        this.restaurantlocation = restaurantlocation;
    }

    public String getRestaurantkitchen() {
        return restaurantkitchen;
    }

    public void setRestaurantkitchen(String restaurantkitchen) {
        this.restaurantkitchen = restaurantkitchen;
    }

    public String getRestaurantrating() {
        return restaurantrating;
    }

    public void setRestaurantrating(String restaurantrating) {
        this.restaurantrating = restaurantrating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Notification() {}

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
