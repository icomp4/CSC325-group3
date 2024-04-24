package shadyAuto.ScheduleBuilder;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.gson.annotations.SerializedName;

public class Schedule {
    private String id;
    private String docName;
    @SerializedName("name")
    private String name;
    @SerializedName("monday")
    private String monday = " ";
    @SerializedName("tuesday")
    private String tuesday = " ";

    @SerializedName("wednesday")
    private String wednesday = " ";
    @SerializedName("thursday")
    private String thursday = " ";
    @SerializedName("friday")
    private String friday = " ";
    @SerializedName("saturday")
    private String saturday = " ";
    @SerializedName("sunday")
    private String sunday = " ";

//    public Schedule(QueryDocumentSnapshot document) {
//        this.id = document.getId();  // get the document ID
//        docName = document.getString("name");  // assuming there's a name field
//        // initialize other fields similarly
//    }

    public Schedule(QueryDocumentSnapshot document) {
        this.id = document.getId(); // Get the document ID
        this.name = document.getString("name"); // Ensure 'name' matches the field in Firestore
        this.monday = document.getString("monday");
        this.tuesday = document.getString("tuesday");
        this.wednesday = document.getString("wednesday");
        this.thursday = document.getString("thursday");
        this.friday = document.getString("friday");
        this.saturday = document.getString("saturday");
        this.sunday = document.getString("sunday");

        // Debugging output to check what is being retrieved
        System.out.println(this);
    }



    public Schedule(String name, String monday, String tuesday, String wednesday, String thursday,
                    String friday, String saturday, String sunday) {
        this.name = name;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    public String getDocName() {
        return docName;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "name='" + name + '\'' +
                ", monday='" + monday + '\'' +
                ", tuesday='" + tuesday + '\'' +
                ", wednesday='" + wednesday + '\'' +
                ", thursday='" + thursday + '\'' +
                ", friday='" + friday + '\'' +
                ", saturday='" + saturday + '\'' +
                ", sunday='" + sunday + '\'' +
                '}';
    }
}

