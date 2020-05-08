package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class Old_Notes extends AppCompatActivity {

    private EditText edttitle;
    private EditText edtcontext;
    private String TitleO;
    String contextfromparse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old__notes);

        edttitle = findViewById(R.id.edtTitleON);
        edtcontext = findViewById(R.id.edtContON);
        TitleO = getIntent().getStringExtra("CurrentTitleTapped");

        edttitle.setText(TitleO);
        //method1();
    }

    private void method1(){

        final ParseQuery<ParseObject> query = ParseQuery.getQuery("note");

        query.whereEqualTo("Title", TitleO);
        query.whereEqualTo("ownerusername", ParseUser.getCurrentUser().getUsername());

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                final ParseObject note = new ParseObject("note");
                String str = note.getObjectId();
                query.getInBackground(str , new GetCallback<ParseObject>() {
                    public void done(ParseObject object, ParseException e) {
                        if (e == null) {
                           edtcontext.setText(note.getString("context"));
                        } else {
                            // something went wrong
                        }
                    }
                });
            }
        });


//        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("note");
//
//        query1.whereEqualTo("Title", TitleO);
//        query1.whereEqualTo("ownerusername", ParseUser.getCurrentUser().getUsername());
//
//        query1.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> objects, ParseException e) {
//                if(e== null){
//                    for (ParseObject noteObject : objects) {
//                       contextfromparse  = noteObject.get("Context" + "");
//                    }
//                }
//            }
//        });
    }
}
