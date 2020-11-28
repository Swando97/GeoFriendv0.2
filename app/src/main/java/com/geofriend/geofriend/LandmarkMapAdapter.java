package com.geofriend.geofriend;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LandmarkMapAdapter {

    public static ArrayList<LandMark> landmarks = new ArrayList<LandMark>();
    public static ArrayList<LandMark> userLandmarks = new ArrayList<LandMark>();

    public LandmarkMapAdapter(){

    }

    public static boolean containsID(Collection<LandMark> c, int id){
        for(LandMark l : c) {
            if(l != null && l.getID() == id){
                return true;
            }
        }
        return false;
    }

    //Pulls Landmarks from Database

    public static void loadLandmarks(){
        for(int i=0; i<DatabaseConnection.mapLandmarks.size();i++){
            landmarks.add(i, DatabaseConnection.mapLandmarks.get(i));
        }
        landmarks.add(54, new LandMark(55, "GeoFence Test",50.660465686907706,-120.26588671471292, "House",R.drawable.stu_union, true));
        landmarks.add(55, new LandMark(56, "GeoFence Test",37.4221,-122.0841, "House",R.drawable.stu_union, true));
    }

    /*
    public static void addLandmarks(){
        landmarks.add(0, new LandMark(1, "Juniper Park", 50.66107816, -120.2600196, "A park in Juniper Ridge where neighbourhood locals go to play hockey, tennis, or have fun on the playground with friends.",R.drawable.juniper_park, true));
        landmarks.add(1, new LandMark(2, "Juniper Dog Park", 50.66165625, -120.26141435, "A dog park in Juniper Ridge that was built to replace a Baseball Diamond. You will often find many neighbourhood locals here playing with their dogs.",R.drawable.juniper_park_dog, true));
        landmarks.add(2, new LandMark(3, "Juniper Roundabout", 50.661075, -120.262175, "A roundabout in Juniper Ridge that was built to replace a 3-Way-Stop. Nobody knows how to use the roundabout but there's been significantly fewer accidents since installation.",R.drawable.juniper_round, true));
        landmarks.add(3, new LandMark(4, "My House", 50.66047354404017,-120.26587107479637, "Built in 1995 with an unfinished basement, it has been finally finished 25 years later. Has a lot of pine trees in the yard.", R.drawable.juniper_1649_cwd, true));
        landmarks.add(3, new LandMark(4, "Juniper Bike Ranch", 50.66242375251574,-120.26137835992478, "The Juniper Ridge Bike Ranch is well known to many around the interior as a great place for people of all ages and skill levels to enjoy themselves.", R.drawable.juniper_bike_ranch, true));
        landmarks.add(4, new LandMark(5, "Juniper Market", 50.66148690676339,-120.26273020803993, "A corner store in Juniper Ridge on the corner of Highland and Qu'Appelle. Currently the only place in Juniper to buy anything.", R.drawable.juniper_market, true));
        landmarks.add(5, new LandMark(6, "Juniper Terrace Sign", 50.661299864433, -120.26571575592384, "Juniper Terrace sign on Qu'Appelle Blvd.",R.drawable.juniper_terrace, true));

        //TRU LANDMARKS

        landmarks.add(6, new LandMark(7, "TRU Residence and Conference Centre",50.673744200166574, -120.36742125670973, "Thompson Rivers University on-campus residence and conference centre.",R.drawable.tru_res, true));
        landmarks.add(7, new LandMark(8, "Student Union",50.67306511025329,-120.3659860168475, "Thompson Rivers University Student Union Building.",R.drawable.stu_union, true));
        landmarks.add(8, new LandMark(9, "Arts & Education",50.67308843269605,-120.3647585945219, "Where you will find 90% of the Arts Faculty offices and where many arts classes are taught.",R.drawable.tru_ae, true));
        landmarks.add(9, new LandMark(10, "Horticulture",50.673219010247934,-120.3675127020705, "Thompson Rivers University Horticulture building and greenhouses.",R.drawable.tru_hortic_building, true));
        landmarks.add(10, new LandMark(11, "STaR Centre",50.67291549862759,-120.36737384617643, "???.",R.drawable.tru_star, true));
        landmarks.add(11, new LandMark(12, "Radio CFBX",50.672729365684205,-120.36724375903921, "Building where the CFBX 92.5 The X radio shows are done.",R.drawable.tru_radio, true));
        landmarks.add(12, new LandMark(13, "Campus Activity Centre",50.67271252838339,-120.3660271474017, "Home to many facilities such as the TRU Student Union, the Bookstore, The Den, and many more!",R.drawable.tru_cac, true));
        landmarks.add(13, new LandMark(14, "Grand Hall",50.67276265802898,-120.3664196997232, "The main conference centre hall in the Campus Activity Centre.",R.drawable.tru_grandhall, true));
        landmarks.add(14, new LandMark(15, "TRU Bookstore",50.67251908739977,-120.36635402706865, "The TRU Bookstore is your place to get TRU textbooks, gear, and supplies.",R.drawable.tru_bookstore, true));
        landmarks.add(15, new LandMark(16, "International Building",50.672509483925914,-120.36425636880844, "A building with large glass windows and an observatory on the roof. This building is home to the School of Business and Economics.",R.drawable.stu_union, true));
        landmarks.add(16, new LandMark(17, "Research Greenhouses",50.671977174016675,-120.37124423347721, "Research Greenhouses up by the TRU Warehouse and Animal Health Technology. The Horticulture department uses these.",R.drawable.stu_union, true));
        landmarks.add(17, new LandMark(18, "Trades & Technology",50.67164516323322,-120.3685772417573, "A building where students gain hands-on experience in the trade of their choice from heavy duty mechanics to electrical engineering.",R.drawable.stu_union, true));
        landmarks.add(18, new LandMark(19, "Industrial Training & Technology Centre",50.67222137879659,-120.36909245606684, "New Trades & Technology building that was built to include extra classrooms for programs previously not offered such as power engineering, HVAC/refrigeration technician and machinist.",R.drawable.stu_union, true));
        landmarks.add(19, new LandMark(20, "Legacy Square Apartments",50.6722913473477,-120.36328871840358, "The Legacy Square Apartment building stands in place of where Lot A used to be. Construction finished in late 2019.",R.drawable.stu_union, true));
        landmarks.add(20, new LandMark(21, "Old Main",50.67124180813051,-120.36343808725802, "Old Main is the main classroom building on campus. Many different subjects are taught in this building from Law studies to Computing Science. You will also find Starbucks, the U&M Market, and the Computer Labs here.",R.drawable.stu_union, true));
        landmarks.add(21, new LandMark(22, "Student Street",50.67121855843365,-120.36282610219945, "A long hallway that spans the entirety of Old Main. Many classrooms can be found here.",R.drawable.stu_union, true));
        landmarks.add(22, new LandMark(23, "TRU Printshop",50.670682236191006,-120.36280330342282, "Need to print an assignment but don't have any paper? Don't worry! The TRU Printshop will take care of everything for you.",R.drawable.stu_union, true));
        landmarks.add(23, new LandMark(24, "Actors Workshop Theatre",50.6715738366722,-120.36336522621144, "A Theatre in Old Main where the Theatre Student's practice, perform, and learn.",R.drawable.stu_union, true));
        landmarks.add(24, new LandMark(25, "Clocktower",50.670473995317074,-120.36106657308568, "A four story building with a huge clock on it.",R.drawable.stu_union, true));
        landmarks.add(25, new LandMark(26, "Alumni Theatre",50.67032185140069,-120.36107327860822, "A big theatre on the second floor of the Clocktower.",R.drawable.stu_union, true));
        landmarks.add(26, new LandMark(27, "Cariboo Child Care Facility",50.67127271305015,-120.36648216951284, "You can leave your children here while you study so you don't have to worry about finding a babysitter.",R.drawable.stu_union, true));
        landmarks.add(27, new LandMark(28, "Faculty Association",50.671311203018206,-120.36589857262601, "???.",R.drawable.stu_union, true));
        landmarks.add(28, new LandMark(29, "Childcare",50.670992050281896,-120.36578946370827, "A place for your children to play at the Cariboo Child Care Facility.",R.drawable.stu_union, true));
        landmarks.add(29, new LandMark(30, "Institutional Planning & Effectiveness",50.670411705627245,-120.36071676123532, "A department on the fourth floor of the Clocktower.",R.drawable.stu_union, true));
        landmarks.add(30, new LandMark(31, "Aboriginal Cultural Centre \"The Gathering Place\"",50.670543450135774,-120.36534759509954, "A place to learn about Aboriginal Culture and History.",R.drawable.stu_union, true));
        landmarks.add(31, new LandMark(32, "Omega \"Student Newspaper\"",50.67066669466389,-120.36553400862607, "???.",R.drawable.stu_union, true));
        landmarks.add(32, new LandMark(33, "Human Resources",50.67053665042826,-120.3646207164565, "Thompson Rivers University Human Resources building and Parking Services.",R.drawable.stu_union, true));
        landmarks.add(33, new LandMark(34, "Brown Family House of Learning",50.67204276159273,-120.36522153127584, "Brown Family House of Learning. You will often find the ground floor lined  from one side to the other with students in line at Tim Horton's.",R.drawable.stu_union, true));
        landmarks.add(34, new LandMark(35, "Research Centre",50.67040320596886,-120.36543744910153, "???.",R.drawable.stu_union, true));
        landmarks.add(35, new LandMark(36, "Irving K. Barber Centre",50.671735083668246,-120.36565068471822, "A large lecture hall on the ground floor of the House of Learning.",R.drawable.stu_union, true));
        landmarks.add(36, new LandMark(37, "BC Centre for Open Learning",50.669883023945324,-120.36492246497068, "The BC Centre for Open Learning is split into two parts: IT Services and TRU Open Learning Support.",R.drawable.stu_union, true));
        landmarks.add(37, new LandMark(38, "TRU Gymnasium",50.66958638161595,-120.36395284641179, "A gymnasium on campus where you can participate in intramural sports and train.",R.drawable.stu_union, true));
        landmarks.add(38, new LandMark(39, "Ken Lepin Science Building",50.669181789056246,-120.36276194560918, "A building dedicated to the study of sciences from Chemistry to Mathematics.",R.drawable.stu_union, true));
        landmarks.add(39, new LandMark(40, "Nursing and Population Health Building",50.66969432904156,-120.36175209391507, "TRU's newest building on campus, construction finished in Summer 2020, is the new location for all nursing and health based sciences.",R.drawable.stu_union, true));
        landmarks.add(40, new LandMark(41, "McGill On-Campus Residence",50.66837429348177,-120.36317232358846, "On-campus residence comprised of three multi-story buildings.",R.drawable.stu_union, true));
        landmarks.add(41, new LandMark(42, "Tournament Capital Centre",50.66844226589624,-120.36587026401604, "Owned by the City of Kamloops, TCC has an aquatic centre and gym that all TRU students can use at a discounted rate.",R.drawable.stu_union, true));
        landmarks.add(42, new LandMark(43, "TCC Indoor Stadium",50.66901469733846,-120.36549709831604, "Owned by the City of Kamloops,.",R.drawable.stu_union, true));
        landmarks.add(43, new LandMark(44, "Hillside Stadium",50.669696807128936,-120.36708170049145, "Owned by the City of Kamloops,.",R.drawable.stu_union, true));
        landmarks.add(44, new LandMark(45, "Animal Health Technology",50.67099104693188,-120.37092112743797, "A park in Juniper Ridge.",R.drawable.stu_union, true));
        landmarks.add(45, new LandMark(46, "Library",50.670316177593,-120.36164336645545, "A park in Juniper Ridge.",R.drawable.stu_union, true));
        landmarks.add(46, new LandMark(47, "Culinary Arts Training Centre",50.669967689627256,-120.36281549179496, "A park in Juniper Ridge.",R.drawable.stu_union, true));
        landmarks.add(47, new LandMark(48, "TRU Meat Store",50.66966509797827,-120.36303006851615, "A park in Juniper Ridge.",R.drawable.stu_union, true));
        landmarks.add(48, new LandMark(49, "TRU Transit Loop",50.67101314576379,-120.36840789759101, "A park in Juniper Ridge.",R.drawable.stu_union, true));
        landmarks.add(49, new LandMark(50, "Foundation & Alumni",50.67063406435922,-120.36083065712394, "A park in Juniper Ridge.",R.drawable.stu_union, true));
        landmarks.add(50, new LandMark(51, "TRU Warehouse",50.672373053865186,-120.37059658014716, "This is the main centre for all shipping and receiving that happens on campus.",R.drawable.stu_union, true));
        landmarks.add(51, new LandMark(52, "Horticulture Gardens",50.67239005246715,-120.36713384830894, "Beautiful gardens maintained by the horticulture department. This is a common destination for people to get married.",R.drawable.stu_union, true));
        landmarks.add(52, new LandMark(53, "Campus Commons",50.67253647483558,-120.36525809670921, "A large circular area in the heart of campus where people do things like study, play catch with friends, or take a nap in the grass.",R.drawable.stu_union, true));
        landmarks.add(53, new LandMark(54, "Campus Courts",50.67114893860972,-120.36400702930285, "Two basketball courts on campus that you will often find people playing on.",R.drawable.stu_union, true));
    }
    */

    public static void loadDiscoveredLandmarks(){
        for(int i=0; i<DatabaseConnection.discoveredLandmarks.size();i++){
            userLandmarks.add(i, DatabaseConnection.discoveredLandmarks.get(i));
        }
    }

}
