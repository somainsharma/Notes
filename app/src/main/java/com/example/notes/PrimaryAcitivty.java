package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class PrimaryAcitivty extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView allnoteslistview;
    private ArrayList<String> title;
    private ArrayAdapter adapter;
    String Title ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary_acitivty);

        allnoteslistview = findViewById(R.id.listviewallnotes);

        title = new ArrayList<>();


        adapter = new ArrayAdapter(PrimaryAcitivty.this, android.R.layout.simple_list_item_1, title);

        refresh2();

        allnoteslistview.setOnItemClickListener(PrimaryAcitivty.this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.add_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //TO acess multiple menu here use switch case statements

        switch(item.getItemId()){
            case R.id.itemadd:

                Intent intent = new Intent(PrimaryAcitivty.this, add_note.class);
                intent.putExtra("CurrentUser", ParseUser.getCurrentUser().getUsername().toString());
                startActivity(intent);

                break;

        }


        return super.onOptionsItemSelected(item);
    }





    public void refresh2(){

        try{

            ParseQuery<ParseObject> first = ParseQuery.getQuery("note");
//============================================
//
//
//            HERE IS THE PROBLEM
//            You didn't use getCurrentUser().getUsername() as ownerusername holds the username
//
//
//============================================

            first.whereEqualTo("ownerusername", ParseUser.getCurrentUser().getUsername());


            first.orderByDescending("createdAt");

            first.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {

                    if(objects.size()>0 && e == null){

                        for (ParseObject noteObjects : objects) {

                             Title = noteObjects.get("Title") + " ";

                            title.add(Title);
                        }
                        allnoteslistview.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        allnoteslistview.setAdapter(adapter);

                    }else if(e != null){
                        Toast.makeText(PrimaryAcitivty.this, e.getMessage() +" ", Toast.LENGTH_SHORT ).show();
                    }
                }
            });



        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    Intent intent = new Intent(PrimaryAcitivty.this, add_note.class);
    intent.putExtra("CurrentTitleTapped",title.get(i));
    startActivity(intent);

    }
}


























//package com.example.notes;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.parse.FindCallback;
//import com.parse.ParseException;
//import com.parse.ParseObject;
//import com.parse.ParseQuery;
//import com.parse.ParseUser;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class PrimaryAcitivty extends AppCompatActivity{
//
//     private ListView allnoteslistview;
//     private ArrayList<String> title;
//     private ArrayAdapter adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_primary_acitivty);
//
//        allnoteslistview = findViewById(R.id.listviewallnotes);
//
//        title = new ArrayList<>();
//
//
//        adapter = new ArrayAdapter(PrimaryAcitivty.this, android.R.layout.simple_list_item_1, title);
//
//        refresh2();
//
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        MenuInflater inflator = getMenuInflater();
//        inflator.inflate(R.menu.add_menu,menu);
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        //TO acess multiple menu here use switch case statements
//
//        if(item.getItemId() == R.id.itemadd){
//
//            Intent intent = new Intent(PrimaryAcitivty.this, add_note.class);
//            intent.putExtra("CurrentUser", ParseUser.getCurrentUser().getUsername().toString());
//            startActivity(intent);
//
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//
//    public void refresh1(){
//        try {
//            ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
//            final ParseUser user = ParseUser.getCurrentUser();
//            parseQuery.findInBackground(new FindCallback<ParseUser>() {
//                @Override
//                public void done(List<ParseUser> objects, ParseException e) {
//
//                    if (e == null && objects.size() > 0) {
//
//
//                        for (ParseObject noteObjects : objects) {
//
//                            String Title = noteObjects.get("Title") + " ";
//
//                            if (noteObjects.get("ownerusername").equals(user)) {
//
//                                title.add(Title);
//
//                            }
//
//                        }
//                        allnoteslistview.setAdapter(adapter);
//                        adapter.notifyDataSetChanged();
//                        allnoteslistview.setAdapter(adapter);
//                    }
//                }
//            });
//
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//    }
//
//    public void refresh2(){
//
//        try{
//
//        ParseQuery<ParseObject> first = ParseQuery.getQuery("note");
//
//        first.whereEqualTo("ownerusername", ParseUser.getCurrentUser());
//
//            ArrayList<ParseQuery<ParseObject>> allQueries = new ArrayList<>();
//            allQueries.add(first);
//
//            ParseQuery<ParseObject> myQuery = ParseQuery.or(allQueries);
//            myQuery.orderByDescending("createdAt");
//
//            myQuery.findInBackground(new FindCallback<ParseObject>() {
//                @Override
//                public void done(List<ParseObject> objects, ParseException e) {
//
//                    if(objects.size()>0 && e == null){
//
//                        for (ParseObject noteObjects : objects) {
//
//                            String Title = noteObjects.get("Title") + " ";
//
//                            title.add(Title);
//                        }
//                        allnoteslistview.setAdapter(adapter);
//                            adapter.notifyDataSetChanged();
//                        allnoteslistview.setAdapter(adapter);
//
//                    }else if(e != null){
//                        Toast.makeText(PrimaryAcitivty.this, e.getMessage() +" ", Toast.LENGTH_SHORT ).show();
//                    }
//                }
//            });
//
//
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//}
