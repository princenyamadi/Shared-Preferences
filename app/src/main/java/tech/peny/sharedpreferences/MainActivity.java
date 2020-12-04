package tech.peny.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("tech.peny.sharedpreferences", Context.MODE_PRIVATE);
//        putting information into shared preferences
        sharedPreferences.edit().putString("username","nick").apply();

//        getting information from shared preferences
        String  username = sharedPreferences.getString("username","");
        Log.i("this is the username ->",username);

//        saving an array of things
        ArrayList<String> friends = new ArrayList<String>();

        friends.add("Fido");
        friends.add("Sarah");
        friends.add("Jones");
//        Serializing an array list
        try {
            sharedPreferences.edit().putString("friends",ObjectSerializer.serialize(friends)).apply();
            Log.i("friends",ObjectSerializer.serialize(friends));


        } catch (Exception e) {
            e.printStackTrace();
        }

//      deserializing an array list
        ArrayList<String> newFriends = new ArrayList<>();

        try {
            newFriends =(ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("new Friends", newFriends.toString());
    }
}