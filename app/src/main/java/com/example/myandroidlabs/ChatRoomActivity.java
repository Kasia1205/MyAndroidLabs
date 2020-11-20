package com.example.myandroidlabs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ChatRoomActivity extends AppCompatActivity {

    public ArrayList<String> elements = new ArrayList<>();
    public MyListAdapter myAdapter;
    ListView messageList;
    Button receive;
    Button send;
    EditText messages;
    public static final String ITEM_SELECTED = "ITEM";
    public static final String ITEM_POSITION = "POSITION";
    public static final String ITEM_ID = "ID";
    //SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        TextView text = findViewById(R.id.rowMessage);
        receive = findViewById(R.id.receiveButton);
        send = findViewById(R.id.sendButton);
        messages = findViewById(R.id.sendMessage);
        messageList = findViewById(R.id.listView);
        boolean isTablet = findViewById(R.id.fragmentLocation) != null;
       // prefs = getSharedPreferences("Message", Context.MODE_PRIVATE);

        messageList.setAdapter( myAdapter = new MyListAdapter());

        send.setOnClickListener( click -> {
            elements.add(messages.getText().toString());
            myAdapter.notifyDataSetChanged();
        });
        receive.setOnClickListener( click -> {
            elements.add(messages.getText().toString());
            myAdapter.notifyDataSetChanged();
        });

        messageList.setOnItemLongClickListener( (p, b, pos, id) -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Delete Message");
             alertDialog.setMessage("Do you want to delete this message?");
             alertDialog.setPositiveButton("Yes", (click, arg) -> {
                 elements.remove(pos);
                 myAdapter.notifyDataSetChanged();
             });
             alertDialog.setNegativeButton("No", (click, arg) -> {
             });
             alertDialog.create().show();

            return true;
        });
        ArrayAdapter<String> theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, elements);
        messageList.setAdapter(theAdapter);
        messageList.setOnItemClickListener((list, item, position, id) -> {
            //Create a bundle to pass data to the new fragment
            Bundle dataToPass = new Bundle();
            dataToPass.putString(ITEM_SELECTED, elements.get(position) );
            dataToPass.putInt(ITEM_POSITION, position);
            dataToPass.putLong(ITEM_ID, id);

            if(isTablet)
            {
                Fragment_Details fragment = new Fragment_Details(); //add a DetailFragment
                fragment.setArguments( dataToPass ); //pass it a bundle for information
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentLocation, fragment) //Add the fragment in FrameLayout
                        .commit(); //actually load the fragment. Calls onCreate() in DetailFragment
            }
            else //isPhone
            {
                Intent nextActivity = new Intent(ChatRoomActivity.this, EmptyPhoneActivity.class);
                nextActivity.putExtras(dataToPass); //send data to next activity
                startActivity(nextActivity); //make the transition
            }
        });
    }


    public class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return elements.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            if(view==null) view = inflater.inflate(R.layout.row_layout, parent, false);

            TextView textRow = view.findViewById(R.id.rowMessage);
            textRow.setText( getItem(position).toString()); //fix this and position values

            return view;
        }
    }
}