package Models;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MyDataProvider { // класс в котором хранятся функции о базе данных
// данный класс служит поставщиком данных из SQLite
    private DbHelper db;
    private Context context;

    public MyDataProvider (Context context) { // конструктор
        this.db = new DbHelper(context);
        this.context = context;
    }
    public static final String TABLE_USERS = "users"; // название таблиц
    public static final String TABLE_RESTAURANT = "restaurant";
    public static final String TABLE_NOTIFICATIONS = "notifications";

    public static final String KEY_USER_ID = "_id"; // название столбцов в базе данных для каждой таблицы
    public static final String KEY_RESTAURANT_ID = "_id";
    public static final String KEY_NOTIFICATION_ID = "_id";

    public static final String KEY_USER_USERNAME = "username";
    public static final String KEY_USER_PASSWORD = "password";
    public static final String KEY_USER_FIRST_NAME = "firstname";
    public static final String KEY_USER_LAST_NAME = "lastname";
    public static final String KEY_USER_PHONENUMB = "phonenumb";
    public static final String KEY_USER_MAIL = "mail";

    public static final String KEY_RESTAURANT_NAME = "name";
    public static final String KEY_RESTAURANT_LOCATION = "location";
    public static final String KEY_RESTAURANT_KITCHEN = "kitchen";
    public static final String KEY_RESTAURANT_TIMETABLE = "timetable";
    public static final String KEY_RESTAURANT_RATING = "rating";
    public static final String KEY_RESTAURANT_PHONE = "phone_of_restaurant";

    public static final String KEY_NOTIFICATION_TITLE = "title";
    public static final String KEY_NOTIFICATION_USER = "user";
    public static final String KEY_NOTIFICATION_RESTAURANT_NAME = "restaurant_name";
    public static final String KEY_NOTIFICATION_RESTAURANT_LOCATION = "restaurant_location";
    public static final String KEY_NOTIFICATION_RESTAURANT_RATING = "restaurant_rating";
    public static final String KEY_NOTIFICATION_RESTAURANT_KITCHEN = "restaurant_kitchen";
    public static final String KEY_NOTIFICATION_RESTAURANT_PHONE_NUMBER = "restaurant_phone";
    public static final String KEY_NOTIFICATION_DATE = "date_reserve";
    public static final String KEY_NOTIFICATION_TIME = "time_reserve";
    public static final String KEY_NOTIFICATION_NUMBER = "number_of_people_reserve";

    public static final String LOGGED_ID_KEY = "LOGGED_ID_KEY";

    public User getUser(int userid){
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM " +
                TABLE_USERS + " WHERE " + KEY_USER_ID + "=" +
                userid, null);
        /* SELECT * FROM TABLE_USERS
        *  WHERE _id=userid - достать пользователя по ID*/
        if(c.moveToFirst()){
            User user = getUserFromCursor(c);

            return user;
        }
        return null;
    } // получение пользователя по ID

    public void addUser(User user){
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();

        sqLiteDatabase.beginTransaction();

        String sql = "INSERT INTO " + TABLE_USERS + "(" +
                KEY_USER_USERNAME + ", " + KEY_USER_PASSWORD + ", " +
                KEY_USER_FIRST_NAME + ", " + KEY_USER_LAST_NAME + ", " +
                KEY_USER_PHONENUMB + ", " + KEY_USER_MAIL +
                ") VALUES ('" + user.getUsername() + "', '" +
                user.getPasswd() + "', '" + user.getFirstname() + "', '" +
                user.getLastname() + "', '" + user.getPhonenmb() + "', '" +
                user.getMail() + "')"; // добавить в таблицу пользователей (имя, пароль...) значения (имя пользователя, пароль пользователя...)

        sqLiteDatabase.execSQL(sql);
        String sqlMaxId = "SELECT MAX(" + KEY_USER_ID + ") FROM " + TABLE_USERS;
        int maxId = 0;
        Cursor cursor = sqLiteDatabase.rawQuery(sqlMaxId, null);
        if (cursor.moveToFirst()){
            maxId = cursor.getInt(0);
        }

        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();
        user.setId(maxId);
    } // добавление пользователя

    public void updateUser(User user){
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();

        String sql = "UPDATE " + TABLE_USERS + " SET " +
                KEY_USER_USERNAME + "='" + user.getUsername() +
                "', " + KEY_USER_PASSWORD + "='" + user.getPasswd() +
                "', " + KEY_USER_FIRST_NAME + "='" + user.getFirstname() +
                "', " + KEY_USER_PHONENUMB + "='" + user.getPhonenmb() +
                "', " + KEY_USER_MAIL + "='" + user.getMail() + "' WHERE " +
                KEY_USER_ID + "=" + user.getId(); // обновить таблицу пользователей

        sqLiteDatabase.execSQL(sql);
    } // обновить таблицу пользователей

    private User getUserFromCursor(Cursor c){
        User user = new User();

        int id = c.getInt(c.getColumnIndex(KEY_USER_ID));
        String username = c.getString(c.getColumnIndex(KEY_USER_USERNAME));
        String passwd = c.getString(c.getColumnIndex(KEY_USER_PASSWORD));
        String firstname = c.getString(c.getColumnIndex(KEY_USER_FIRST_NAME));
        String lastname = c.getString(c.getColumnIndex(KEY_USER_LAST_NAME));
        String phonenumb = c.getString(c.getColumnIndex(KEY_USER_PHONENUMB));
        String mail = c.getString(c.getColumnIndex(KEY_USER_MAIL));

        user.setId(id);
        user.setUsername(username);
        user.setPasswd(passwd);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPhonenmb(phonenumb);
        user.setMail(mail);

        return user;
    } // установка данных пользователя

    private User currentUser;

    public User getLoggedInUser(){
        SharedPreferences pref = this.context.getSharedPreferences(LOGGED_ID_KEY, 0);

        int userid = pref.getInt(LOGGED_ID_KEY, -1);
        if (userid != -1) {
            if (currentUser == null || userid != currentUser.getId()) {
                User user = getUser(userid);
                currentUser = user;
            }
        }
        return currentUser;
    } // получить зарегестрированного пользователя

    public void setLoggedInUser(User user){
        SharedPreferences pref = this.context.getSharedPreferences(LOGGED_ID_KEY, 0);
        SharedPreferences.Editor editor = pref.edit();

        if (user != null){
            editor.putInt(LOGGED_ID_KEY, user.getId());
        } else {
            editor.putInt(LOGGED_ID_KEY, -1);
        }
        editor.commit();

        this.currentUser = user;
    } // установить зарегестрированного пользователя

    public User getUser(String username, String passwd){
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM " +
                TABLE_USERS + " WHERE " + KEY_USER_USERNAME + "='" +
                username + "' AND "+ KEY_USER_PASSWORD + "='" +
                passwd + "'", null); // выбрать всех пользователей у которых имя совпадает с username и пароль совпадает с passwd
        if (c.moveToFirst()){
            User user = getUserFromCursor(c);
            return user;
        }
        return null;
    } // получить пользователя по имени и паролю

    public User getUser (String username){
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM " +
                TABLE_USERS + " WHERE " + KEY_USER_USERNAME + "='" +
                username + "'", null);
        // вывести всех пользователей у которых имя совпадает с username

        if (c.moveToFirst()){
            User user = getUserFromCursor(c);

            return user;
        }
        return null;
    } // получить пользователя по имени



    public Restaurant getRestaurant(int restaurantID){
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM " +
                TABLE_RESTAURANT + " WHERE " + KEY_RESTAURANT_ID + "=" +
                restaurantID, null);
        /* SELECT * FROM TABLE_RESTAURANT
         *  WHERE _id=restaurantID - достать ресторан по ID*/
        if (c.moveToFirst()){
            Restaurant restaurant = getRestaurantFromCursor(c);

            return restaurant;
        }
        return null;
    } // получить ресторан по ID

    public ArrayList<Restaurant> getRestaurants (){
        ArrayList<Restaurant> result = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM " +
                TABLE_RESTAURANT + " ORDER BY " + KEY_RESTAURANT_ID, null);
        // достать все рестораны из таблицы ресторанов в порядке по ID

        while(c.moveToNext()){
            Restaurant restaurant = getRestaurantFromCursor(c);

            result.add(restaurant);
        }
        return result;
    } // достать список ресторанов

    private Restaurant getRestaurantFromCursor(Cursor c){
        Restaurant restaurant = new Restaurant();

        int id = c.getInt(c.getColumnIndex(KEY_RESTAURANT_ID));
        String name = c.getString(c.getColumnIndex(KEY_RESTAURANT_NAME));
        String location = c.getString(c.getColumnIndex(KEY_RESTAURANT_LOCATION));
        String kitchen = c.getString(c.getColumnIndex(KEY_RESTAURANT_KITCHEN));
        String timetable = c.getString(c.getColumnIndex(KEY_RESTAURANT_TIMETABLE));
        String rating = c.getString(c.getColumnIndex(KEY_RESTAURANT_RATING));
        String phoneofrestaurant = c.getString(c.getColumnIndex(KEY_RESTAURANT_PHONE));

        restaurant.setId(id);
        restaurant.setName(name);
        restaurant.setLocation(location);
        restaurant.setKitchen(kitchen);
        restaurant.setTimetable(timetable);
        restaurant.setRating(rating);
        restaurant.setPhoneofrestaurant(phoneofrestaurant);

        return restaurant;
    } // установить данные ресторана



    private Notification getNotificationFromCursor(Cursor c){
        Notification notification = new Notification();

        int id = c.getInt(c.getColumnIndex(KEY_NOTIFICATION_ID));
        String title = c.getString(c.getColumnIndex(KEY_NOTIFICATION_TITLE));
        int user = c.getInt(c.getColumnIndex(KEY_NOTIFICATION_USER));
        String name = c.getString(c.getColumnIndex(KEY_NOTIFICATION_RESTAURANT_NAME));
        String location = c.getString(c.getColumnIndex(KEY_NOTIFICATION_RESTAURANT_LOCATION));
        String kitchen = c.getString(c.getColumnIndex(KEY_NOTIFICATION_RESTAURANT_KITCHEN));
        String rating = c.getString(c.getColumnIndex(KEY_NOTIFICATION_RESTAURANT_RATING));
        String phone = c.getString(c.getColumnIndex(KEY_NOTIFICATION_RESTAURANT_PHONE_NUMBER));
        String date = c.getString(c.getColumnIndex(KEY_NOTIFICATION_DATE));
        String time = c.getString(c.getColumnIndex(KEY_NOTIFICATION_TIME));
        String number = c.getString(c.getColumnIndex(KEY_NOTIFICATION_NUMBER));

        notification.setId(id);
        notification.setTitle(title);
        notification.setUserid(user);
        notification.setRestaurantname(name);
        notification.setRestaurantlocation(location);
        notification.setRestaurantrating(rating);
        notification.setRestaurantkitchen(kitchen);
        notification.setDate(date);
        notification.setTime(time);
        notification.setNumber(number);
        notification.setRestaurantphone(phone);

        return notification;
    } // установить данные резерва (в уведомлении)

    public void addNotification (Notification notification){
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();

        sqLiteDatabase.beginTransaction();

        String sql = "INSERT INTO " + TABLE_NOTIFICATIONS + "(" +
                KEY_NOTIFICATION_TITLE + ", " + KEY_NOTIFICATION_RESTAURANT_NAME
                + ", " + KEY_NOTIFICATION_USER + ", " + KEY_NOTIFICATION_RESTAURANT_RATING + ", " +
                KEY_NOTIFICATION_RESTAURANT_PHONE_NUMBER + ", " +
                KEY_NOTIFICATION_DATE + ", " + KEY_NOTIFICATION_TIME + ", " + KEY_NOTIFICATION_NUMBER
                + ", " + KEY_NOTIFICATION_RESTAURANT_KITCHEN + ", " + KEY_NOTIFICATION_RESTAURANT_LOCATION
                + ") VALUES ('" +
                notification.getTitle() + "', '" + notification.getRestaurantname() + "', '"
                + notification.getUserid() + "', '" + notification.getRestaurantrating() + "', '" +
                notification.getRestaurantphone() + "', '"
                + notification.getDate() + "', '" + notification.getTime() + "', '" + notification.getNumber() + "', '"
                + notification.getRestaurantkitchen() + "', '" + notification.getRestaurantlocation() + "')";
        // добавить в таблицу об уведомлениях (заголовок, название ресторана...) значения (...)

        sqLiteDatabase.execSQL(sql);
        String sqlMaxId = "SELECT MAX(" + KEY_NOTIFICATION_ID + ") FROM " + TABLE_NOTIFICATIONS;
        int maxId = 0;
        Cursor cursor = sqLiteDatabase.rawQuery(sqlMaxId, null);
        if (cursor.moveToFirst()){
            maxId = cursor.getInt(0);
        }

        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();
        notification.setId(maxId);
    } // добавить резерв (уведомление)

    public ArrayList<Notification> getUserNotifications(User user) {
        ArrayList<Notification> result = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM " +
                TABLE_NOTIFICATIONS + " WHERE " + KEY_NOTIFICATION_USER + "=" + user.getId() + " ORDER BY " + KEY_USER_ID + " DESC", null);
        // выбрать те уведомелния, которые принадлежат user

        while (c.moveToNext()) {
            Notification notification = getNotificationFromCursor(c);

            result.add(notification);
        }
        return result;
    } // распознать уведомления по конкретному пользователю (у каждого уведомления сохранен в таблице ID пользователя который создал это уведомление)

    public Notification getNotification(int notificationID){
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM " +
                TABLE_NOTIFICATIONS + " WHERE " + KEY_NOTIFICATION_ID + "=" +
                notificationID, null);
        // выбрать все уведомления где notificationID = _id

        if (c.moveToFirst()){
            Notification notification = getNotificationFromCursor(c);


            return notification;
        }
        return null;
    } // получить уведомление по ID

    public void deleteNotification (Notification notification){
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();

        sqLiteDatabase.beginTransaction();

        String sql = "DELETE FROM " + TABLE_NOTIFICATIONS + " WHERE " +
                 notification.getId() + "=" + KEY_NOTIFICATION_ID;
        // удалить уведомления где notification.getId() = _id

        sqLiteDatabase.execSQL(sql);
        String sqlMaxId = "SELECT MAX(" + KEY_NOTIFICATION_ID + ") FROM " + TABLE_NOTIFICATIONS;
        int maxId = 0;
        Cursor cursor = sqLiteDatabase.rawQuery(sqlMaxId, null);
        if (cursor.moveToFirst()){
            maxId = cursor.getInt(0);
        }

        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();
        notification.setId(maxId);
    } // удалить уведомление из базы данных
}
