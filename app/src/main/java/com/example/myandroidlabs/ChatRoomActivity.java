package com.example.myandroidlabs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        TextView text = findViewById(R.id.rowMessage);
        receive = findViewById(R.id.receiveButton);
        send = findViewById(R.id.sendButton);
        messages = findViewById(R.id.sendMessage);
        messageList = findViewById(R.id.listView);
        prefs = getSharedPreferences("Message", Context.MODE_PRIVATE);

        messageList.setAdapter( myAdapter = new MyListAdapter());
        messageList.setOnItemClickListener( (parent, view, position, id) -> {
            elements.remove(position);
            myAdapter.notifyDataSetChanged();
        });

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
    }


    public class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return elements.size();
        }

        @Override
        public Object getItem(int position) {
            return messages.getText().toString();
        }

        @Override
        public long getItemId(int position) {
            return (long) position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();

            @SuppressLint("ViewHolder") View newRow = inflater.inflate(R.layout.row_layout, parent, false);

            TextView textRow = newRow.findViewById(R.id.rowMessage);
            textRow.setText( getItem(position).toString()); //fix this and position values

            return newRow;
        }
    }
}