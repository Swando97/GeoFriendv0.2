package com.geofriend.geofriend;

import java.util.ArrayList;
import java.util.List;

public class LandmarkMapAdapter {

    public static ArrayList<LandMark> landmarks = new ArrayList<LandMark>();

    public LandmarkMapAdapter(){

    }


    //Put these into database and be able to access them
    public void loadLandmarks(){
        landmarks.add(0, new LandMark(1, "Juniper Park", 50.66107816, -120.2600196, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(1, new LandMark(2, "Juniper Dog Park", 50.66165625, -120.26141435, "A dog park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(2, new LandMark(3, "Juniper Roundabout", 50.661075, -120.262175, "A roundabout in Juniper Ridge.",R.drawable.image5));
        landmarks.add(3, new LandMark(4, "Kamloops Downtown", 52.675940, -124.360050, "Testing... description", R.drawable.image2));
        landmarks.add(4, new LandMark(5, "Google", 37.422, -122.084, "Testing ...", R.drawable.image1));
        landmarks.add(5, new LandMark(6, "Juniper Terrace Sign", 50.661299864433, -120.26571575592384, "Juniper Terrace sign on Qu'Appelle Blvd.",R.drawable.image6));

        //TRU LANDMARKS

        landmarks.add(6, new LandMark(7, "TRU Residence and Conference Centre",50.673744200166574, -120.36742125670973, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(7, new LandMark(8, "Student Union",50.67306511025329,-120.3659860168475, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(8, new LandMark(9, "Arts & Education",50.67308843269605,-120.3647585945219, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(9, new LandMark(10, "Horticulture",50.67308843269605,-120.3647585945219, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(10, new LandMark(11, "STaR Centre",50.67314879661173,-120.36748619968999, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(11, new LandMark(12, "Radio CFBX",50.672911456223126,-120.36739744428374, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(12, new LandMark(13, "Campus Activity Centre",50.67271252838339,-120.3660271474017, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(13, new LandMark(14, "Grand Hall",50.67262677339092,-120.36572029661237, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(14, new LandMark(15, "TRU Bookstore",50.67251908739977,-120.36635402706865, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(15, new LandMark(16, "International Building",50.672509483925914,-120.36425636880844, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(16, new LandMark(17, "Research Greenhouses",50.671977174016675,-120.37124423347721, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(17, new LandMark(18, "Trades & Technology",50.67164516323322,-120.3685772417573, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(18, new LandMark(19, "Industrial Training & Technology Centre",50.67222137879659,-120.36909245606684, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(19, new LandMark(20, "Legacy Square Apartments",50.6722913473477,-120.36328871840358, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(20, new LandMark(21, "Old Main",50.67124180813051,-120.36343808725802, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(21, new LandMark(22, "Student Street",50.67121855843365,-120.36282610219945, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(22, new LandMark(23, "TRU Printshop",50.670682236191006,-120.36280330342282, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(23, new LandMark(24, "Actors Workshop Theatre",50.6715738366722,-120.36336522621144, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(24, new LandMark(25, "Clocktower",50.670473995317074,-120.36106657308568, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(25, new LandMark(26, "Alumni Theatre",50.67032185140069,-120.36107327860822, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(26, new LandMark(27, "Cariboo Child Care Facility",50.67127271305015,-120.36648216951284, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(27, new LandMark(28, "Faculty Association",50.671311203018206,-120.36589857262601, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(28, new LandMark(29, "Childcare",50.670992050281896,-120.36578946370827, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(29, new LandMark(30, "Institutional Planning & Analysis",50.670411705627245,-120.36071676123532, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(30, new LandMark(31, "Aboriginal Cultural Centre \"The Gathering Place\"",50.670543450135774,-120.36534759509954, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(31, new LandMark(32, "Omega \"Student Newspaper\"",50.67066669466389,-120.36553400862607, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(32, new LandMark(33, "Human Resources",50.67053665042826,-120.3646207164565, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(33, new LandMark(34, "Brown Family House of Learning",50.67204276159273,-120.36522153127584, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(34, new LandMark(35, "Research Centre",50.67040320596886,-120.36543744910153, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(35, new LandMark(36, "Irving K. Barber Centre",50.671735083668246,-120.36565068471822, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(36, new LandMark(37, "BC Centre for Open Learning",50.669883023945324,-120.36492246497068, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(37, new LandMark(38, "TRU Gymnasium",50.66958638161595,-120.36395284641179, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(38, new LandMark(39, "Ken Lepin Science Building",50.669181789056246,-120.36276194560918, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(39, new LandMark(40, "Nursing and Population Health Building",50.66969432904156,-120.36175209391507, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(40, new LandMark(41, "McGill On-Campus Residence",50.66837429348177,-120.36317232358846, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(41, new LandMark(42, "Tournament Capital Centre",50.66844226589624,-120.36587026401604, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(42, new LandMark(43, "TCC Indoor Stadium",50.66901469733846,-120.36549709831604, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(43, new LandMark(44, "Hillside Stadium",50.669696807128936,-120.36708170049145, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(44, new LandMark(45, "Animal Health Technology",50.67099104693188,-120.37092112743797, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(45, new LandMark(46, "Library",50.670316177593,-120.36164336645545, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(46, new LandMark(47, "Culinary Arts Training Centre",50.669967689627256,-120.36281549179496, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(47, new LandMark(48, "TRU Meat Store",50.66966509797827,-120.36303006851615, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(48, new LandMark(49, "TRU Transit Loop",50.67101314576379,-120.36840789759101, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(49, new LandMark(50, "Foundation & Alumni",50.67063406435922,-120.36083065712394, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(50, new LandMark(51, "TRU Warehouse",50.672373053865186,-120.37059658014716, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(51, new LandMark(52, "Horticulture Gardens",50.67239005246715,-120.36713384830894, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(52, new LandMark(53, "Campus Commons",50.67253647483558,-120.36525809670921, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(53, new LandMark(54, "Convocation Wall",50.67159603809432,-120.36377419175774, "A park in Juniper Ridge.",R.drawable.image5));
        landmarks.add(54, new LandMark(55, "Campus Courts",50.67114893860972,-120.36400702930285, "A park in Juniper Ridge.",R.drawable.image5));

    }



}
