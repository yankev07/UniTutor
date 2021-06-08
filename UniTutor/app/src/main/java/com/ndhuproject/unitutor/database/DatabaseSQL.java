package com.ndhuproject.unitutor.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by kevin on 5/19/18.
 */

public class DatabaseSQL extends SQLiteOpenHelper {

    private static final String DATABASE = "UnitutorDB";

    private static final String PLACESLIST = "placestable";
    private static final String PLACESDETAILS = "detailstable";
    private static final String USERINFO = "userinfotable";

    // Attributes of the placestable
    private static final String ID = "id";
    private static final String NAME = "place";
    // Attributes of the detailstable
    private static final String DETAILS = "details";
    // Attributes of the userinfotable
    private static final String FULLNAME = "fullname";
    private static final String IDNUMBER = "studentid";
    private static final String DEPARTMENT = "department";
    private static final String GENDER = "gender";

    public String administrationBuilding;
    public String artsBuilding;
    public String humanitiesAndSocialSciencesBuilding3;
    public String humanitiesAndSocialSciencesBuilding2;
    public String humanitiesAndSocialSciencesBuilding1;
    public String huaShihEducationBuilding;
    public String informationAndNetworkCenter;
    public String managementBuilding;
    public String studentsActivitiesCenter;
    public String library;
    public String lakesideRestaurant;
    public String scienceAndEngineeringBuilding1;
    public String scienceAndEngineeringBuilding2;
    public String incubationCenter;
    public String scienceAndEngineeringBuilding3;
    public String artsWorkshop;
    public String indigenousStudiesBuilding;
    public String environmentalStudiesBuilding;
    public String guestHouse;
    public String environmentalExpositionCenter;
    public String mainEntranceSouthExit;
    public String facultyQuarters1;
    public String kindergarden;
    public String facultyQuarters2;
    public String communityCenter;
    public String communityCourts1;
    public String dormitory1;
    public String westCommunityHouse;
    public String dormitory2;
    public String dormitory3;
    public String dormitory4;
    public String swimmingPools;
    public String sportsCourts1;
    public String baseballSoccerField;
    public String entranceNorthExit;
    public String athleticField;
    public String indoorsSportsCenter;
    public String sportsCourts2;
    public String ropeCourseField;
    public String dormitory5;
    public String communityCourts2;
    public String eastCommunityHouse;
    public String dormitory6;
    public String dormitory7;
    public String warehouse;
    public String sewageTreatmentPlant;
    public String dongLake;
    public String hwaLake;
    public String hwaLake2;
    public String scenicBridge;


    public DatabaseSQL(Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS placestable" + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS detailstable" + " (" + NAME + " TEXT, " + DETAILS + " TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS userinfotable" + " (" + FULLNAME + " TEXT, " + IDNUMBER + " TEXT, " + GENDER + " TEXT, " + DEPARTMENT + " TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PLACESLIST);
        db.execSQL("DROP TABLE IF EXISTS " + PLACESDETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + USERINFO);
        onCreate(db);
    }

    public ArrayList<String> displayPlaces(){
        ArrayList<String> myArray= new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM " + PLACESLIST, null);
        cur.moveToFirst();
        while(cur.isAfterLast()==false){
            myArray.add(cur.getString(cur.getColumnIndex(NAME)));
            cur.moveToNext();
        }
        return myArray;
    }

    public boolean checkEmptyTable(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM "+ PLACESLIST, null);
        cur.moveToFirst();
        if(cur.getCount() > 0){
            return false;
        }
        return true;
    }


    public void emptyInfoTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ USERINFO);
        db.close();
    }


    public void saveUserInfo(String firstName, String lastName, String studentID, String gender, String department){
        emptyInfoTable();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FULLNAME,firstName + " " + lastName);
        values.put(IDNUMBER,studentID);
        values.put(GENDER,gender);
        values.put(DEPARTMENT,department);
        db.insert(USERINFO, null, values);
    }


    public String displayPlaceDetails(String placeName){
        String whereClause = NAME + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.query(PLACESDETAILS, new String[]{DETAILS}, whereClause, new String[]{placeName}, null, null, null);
        cur.moveToFirst();
        return cur.getString(cur.getColumnIndex(DETAILS));
    }


    public ArrayList<String> fetchUserInfo() {
        ArrayList<String> myArray= new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM "+ USERINFO, null);
        cur.moveToFirst();
        while(cur.isAfterLast()==false){
            myArray.add(cur.getString(cur.getColumnIndex(FULLNAME)));
            myArray.add(cur.getString(cur.getColumnIndex(IDNUMBER)));
            myArray.add(cur.getString(cur.getColumnIndex(GENDER)));
            myArray.add(cur.getString(cur.getColumnIndex(DEPARTMENT)));
            cur.moveToNext();
        }
        return myArray;
    }



    public void addPlacesInDatabase(){

        if(checkEmptyTable()){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(NAME,"Administration Building");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Arts Building");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Humanities and Social Sciences Building III");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Humanities and Social Sciences Building I");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Humanities and Social Sciences Building II");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Hua-Shih Education Building");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Information and Network Center");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Management Building");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Students Activities Center");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Library");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Lakeside Restaurant");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Science and Engineering Building I");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Science and Engineering Building II");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Incubation Center");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Science and Engineering Building III");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Arts Workshop");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Indigenous Studies Building");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Environmental Studies Building");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Guest House");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Environmental Exposition Center");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Main Entrance(with Security Office)/South Exit");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Faculty Quarters I");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Kindergarden");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Faculty Quarters II");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Community Center");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Community Courts I");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Dormitory I");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"West Community House");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Dormitory II");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Dormitory III");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Dormitory IV");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Swimming Pools");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Sports Courts I");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Baseball/Soccer Field");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Entrance/North Exit");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Athletic Field");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Indoors Sports Center");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Sports Courts II");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Rope Course Field");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Dormitory V");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Community Courts II");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"East Community House");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Dormitory VI");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Dormitory VII");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Warehouse");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Sewage Treatment Plant");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Dong Lake");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Hwa Lake");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Hwa Lake II");
            db.insert(PLACESLIST, null, values);
            values.put(NAME,"Scenic Bridge");
            db.insert(PLACESLIST, null, values);
            db.close();
            initializeDetailsData();
            addPlacesDetails();
        }
    }


    public void addPlacesDetails(){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,"Administration Building");
        values.put(DETAILS, administrationBuilding);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Arts Building");
        values.put(DETAILS, artsBuilding);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Humanities and Social Sciences Building III");
        values.put(DETAILS, humanitiesAndSocialSciencesBuilding3);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Humanities and Social Sciences Building I");
        values.put(DETAILS, humanitiesAndSocialSciencesBuilding1);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Humanities and Social Sciences Building II");
        values.put(DETAILS, humanitiesAndSocialSciencesBuilding2);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Hua-Shih Education Building");
        values.put(DETAILS, huaShihEducationBuilding);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Information and Network Center");
        values.put(DETAILS, informationAndNetworkCenter);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Management Building");
        values.put(DETAILS, managementBuilding);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Students Activities Center");
        values.put(DETAILS, studentsActivitiesCenter);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Library");
        values.put(DETAILS, library);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Lakeside Restaurant");
        values.put(DETAILS, lakesideRestaurant);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Science and Engineering Building I");
        values.put(DETAILS, scienceAndEngineeringBuilding1);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Science and Engineering Building II");
        values.put(DETAILS, scienceAndEngineeringBuilding2);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Incubation Center");
        values.put(DETAILS, incubationCenter);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Science and Engineering Building III");
        values.put(DETAILS, scienceAndEngineeringBuilding3);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Arts Workshop");
        values.put(DETAILS, artsWorkshop);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Indigenous Studies Building");
        values.put(DETAILS, indigenousStudiesBuilding);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Environmental Studies Building");
        values.put(DETAILS, environmentalStudiesBuilding);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Guest House");
        values.put(DETAILS, guestHouse);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Environmental Exposition Center");
        values.put(DETAILS, environmentalExpositionCenter);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Main Entrance(with Security Office)/South Exit");
        values.put(DETAILS, mainEntranceSouthExit);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Faculty Quarters I");
        values.put(DETAILS, facultyQuarters1);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Kindergarden");
        values.put(DETAILS, kindergarden);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Faculty Quarters II");
        values.put(DETAILS, facultyQuarters2);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Community Center");
        values.put(DETAILS, communityCenter);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Community Courts I");
        values.put(DETAILS, communityCourts1);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Dormitory I");
        values.put(DETAILS, dormitory1);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"West Community House");
        values.put(DETAILS, westCommunityHouse);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Dormitory II");
        values.put(DETAILS, dormitory2);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Dormitory III");
        values.put(DETAILS, dormitory3);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Dormitory IV");
        values.put(DETAILS, dormitory4);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Swimming Pools");
        values.put(DETAILS, swimmingPools);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Sports Courts I");
        values.put(DETAILS, sportsCourts1);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Baseball/Soccer Field");
        values.put(DETAILS, baseballSoccerField);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Entrance/North Exit");
        values.put(DETAILS, entranceNorthExit);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Athletic Field");
        values.put(DETAILS, athleticField);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Indoors Sports Center");
        values.put(DETAILS, indoorsSportsCenter);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Sports Courts II");
        values.put(DETAILS, sportsCourts2);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Rope Course Field");
        values.put(DETAILS, ropeCourseField);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Dormitory V");
        values.put(DETAILS, dormitory5);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Community Courts II");
        values.put(DETAILS, communityCourts2);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"East Community House");
        values.put(DETAILS, eastCommunityHouse);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Dormitory VI");
        values.put(DETAILS, dormitory6);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Dormitory VII");
        values.put(DETAILS, dormitory7);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Warehouse");
        values.put(DETAILS, warehouse);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Sewage Treatment Plant");
        values.put(DETAILS, sewageTreatmentPlant);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Dong Lake");
        values.put(DETAILS, dongLake);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Hwa Lake");
        values.put(DETAILS, hwaLake);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Hwa Lake II");
        values.put(DETAILS, hwaLake2);
        db.insert(PLACESDETAILS, null, values);
        values.put(NAME,"Scenic Bridge");
        values.put(DETAILS, scenicBridge);
        db.insert(PLACESDETAILS, null, values);
    }


    public void initializeDetailsData(){

        administrationBuilding = ("This building contains most offices that deal with student affairs, it is also where you can locate the school’s President. At the entrance, you can find the Dong Hwa University post office, and bank ATMs.");
        artsBuilding = ("This is where students majoring in Art can be found, a lot of pieces of different forms of art can be located here.");
        humanitiesAndSocialSciencesBuilding3 = ("This is the newer of the Humanities and Social Sciences Buildings, the English Language Center can be located in this building. At the first floor, self-study rooms are sometimes available and opened 24 hours.");
        humanitiesAndSocialSciencesBuilding2 = ("Right next to Humanities and Social Sciences Building I is Humanities and Social Sciences Building II and this is where Chinese classes are mostly taken at, the Chinese language center can be located on the second floor of this building.");
        humanitiesAndSocialSciencesBuilding1 = ("Located in between the Humanities and Social Sciences Building III and II is Humanities and Social Sciences Building I, more language, and humanities related classes are normally taken here.");
        huaShihEducationBuilding = ("One of the newer buildings in the school is the Hua-Shih Education Building.\n" + "Many general courses and education department courses are taken in this building.");
        informationAndNetworkCenter = ("This building has computer labs allowing you to use the computers for your convenience, in case you find yourself on campus and in need of using a computer. A printing service is also provided here (100 free pages per academic year for each student).");
        managementBuilding = ("Right next to the student activity center is the Management building. This is where most Business and Finance majoring students take their classes.");
        studentsActivitiesCenter = ("This is a place where a lot of clubs are coordinated, there is a galore of music clubs, dance clubs, skateboarding club and so many more. This place also has an auditorium where musical performances are hosted.");
        library = ("The tallest building on campus, located somewhat in the center of the school is the library, you can find a galore of books and exhibitions can be observed here all throughout the year. Group study rooms are available, but it requires at least 3 students to book a room.");
        lakesideRestaurant = ("Located next to the lake is a cafeteria that offers a buffet with a wide range of Taiwanese foods, lunch box service and also has two more restaurants to cater for student's lunch needs. Especially when Zhixue Street seems too far away.");
        scienceAndEngineeringBuilding1 = ("This is where Math, Physics, and Chemistry majoring students take their classes.");
        scienceAndEngineeringBuilding2 = ("This is where Computer Science, Electrical Engineering, Opto-electric etc... majoring students take their classes.");
        incubationCenter = ("This building has a center that is dedicated to facilitating local business growth.");
        scienceAndEngineeringBuilding3 = ("This is where the department of Life Sciences can be located and this is where students majoring in this department take their classes.");
        artsWorkshop = ("This is where many forms of art are created, namely metalwork, sculpturing, woodwork, stoneware etc.");
        indigenousStudiesBuilding = ("This is where the College of Indigenous Studies is located. All Departments and classes are under Indigenous Studies are located in this building. There are also interesting sculptures and indigenous artefacts on display.");
        environmentalStudiesBuilding = ("This is where all the Departments and classes under environmental studies are held.");
        guestHouse = ("Accommodation for people who visit the university.");
        environmentalExpositionCenter = ("This is where the Environmental Studies Departments hold their exhibitions.");
        mainEntranceSouthExit = ("This is the Main Gate of the university facing Da Xue Road (University Road). It is also where the campus security office is located.");
        facultyQuarters1 = ("This is where school faculty that live at the university reside.");
        kindergarden = ("This is the kindergarden for the Dong Hwa community.");
        facultyQuarters2 = ("This is where school faculty that live at the university reside.");
        communityCenter = ("The community centre houses a laundry and a study area for students and faculty.");
        communityCourts1 = ("This is the living area for non-faculty members of the school.");
        dormitory1 = ("This is the Masters/PhD dorms single room dorm located in the West side of the university.");
        westCommunityHouse = ("This is where Family Mart convenient store, Bakery and other restaurants are located for the West side of the school. There is also dancing halls with mirrors for student dance clubs to use.");
        dormitory2 = ("This is the female dormitory located in the West side of the university.");
        dormitory3 = ("This is the mixed dormitory located in the West side of the university.");
        dormitory4 = ("This is the male dormitory located in the West side of the university");
        swimmingPools = ("This is the area where all swimming lessons take place. The swimming pool is also available as a place for exercise for students and faculty.");
        sportsCourts1 = ("The largest and most popular sports courts in NDHU. With many volleyball and basketball courts, it's the ideal place to meet students that are passionate about sports.");
        baseballSoccerField = ("This field is composed by two baseball fields and a soccer field that is no longer in use. Baseball games are occasionally played there on weekends, and its also the venue for some baseball competitions.");
        entranceNorthExit = ("This is the NDHU back gate, commonly called zhixue gate. It is facing the zhixue street, where many shops, bookstores and restaurants are located. Zhixue street is the place where students often eat and buy essentials.");
        athleticField = ("The athletic field is where the NDHU soccer team trains. Everyday, students can be seen running and exercising there.");
        indoorsSportsCenter = ("The indoors sports center is where the indoors volleyball and basketball courts are located. It is a huge sports complex, where competitions and official games are occasionally played.");
        sportsCourts2 = ("This is where the tennis courts are located. Physical education of tennis classes are also taught there.");
        ropeCourseField = ("The rope course field is occasionally used for classes, to teach students how to overcome their fears.");
        dormitory5 = ("The student's dormitory 5 is a graduate dormitory which offers a 2 people's room. It's also one of the dormitories that remain opened during the Winter break.");
        communityCourts2 = ("Connunity courts 2 is composed by volleyball and basketball courts. It's usually where freshmen and students who live in the neighboring dormitories play.");
        eastCommunityHouse = ("East Community House is a complex, with a student's food court, a convenience store, a study lounge and a smoking area. It also provides seats which are often used by students to chat overnight.");
        dormitory6 = ("Dormitory 6 is the Female freshmen's dormitory. It provides a 4 people's room. It is often closed during the winter break.");
        dormitory7 = ("Dormitory 7 is the Female freshmen's dormitory. It provides a 4 people's room. It is often closed during the winter break.");
        warehouse = ("Warehouse is where abandonned bicycles are stored and restored for the auction sale. It is also where bicycles which don't have the NDHU sticker are collected and stored.");
        sewageTreatmentPlant = ("Dong Hwa University's sewage treatment plant.");
        dongLake = ("The largest of the three lakes in NDHU. Dong lake is where thw kayaking class takes place. It faces the lakeside restaurants and provides a beautiful view to those who are sitting in there.");
        hwaLake = ("Hwa lake is also one of the three lakes in NDHU, it is the second largest. It is located behind the management building.");
        hwaLake2 = ("Hwa lake 2 is the smallest of the three lakes in Dong Hwa University.");
        scenicBridge = ("This is the perfect place to take pictures to keep souvenir from your visit in NDHU. Many tourits enjoy taking pictures there while watching the beautiful scenery.");

    }
}
