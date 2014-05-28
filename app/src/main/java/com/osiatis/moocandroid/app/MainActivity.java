package com.osiatis.moocandroid.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import java.util.Deque;
import java.util.LinkedList;

public class MainActivity extends Activity {

    private int maxMsgNumber = 10;
    private String userName = null;
    private Deque<String> messages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messages = new LinkedList<String>();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onMsgBtnClick(View view){
        EditText editText = (EditText) findViewById(R.id.editMsgId);
        String text = editText.getText().toString();
        if(userName == null ){
           userName = text;
           TextView welcomeText = (TextView) findViewById(R.id.welcomeText);
           welcomeText.setText( String.format(getResources().getString(R.string.welcomeMsg), userName));
           TextView promptText = (TextView) findViewById(R.id.promptTextId);
           promptText.setText(getResources().getString(R.string.promptText));

        }else{

            messages.addFirst(userName + " : "  + text);
            if(messages.size() > maxMsgNumber){
                messages.removeLast();
            }
            GridView msgGridView = (GridView) findViewById(R.id.msgGridView);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,(LinkedList) messages);
            msgGridView.setAdapter(arrayAdapter);
        }
        //ré-initialise le prompt
        editText.setText("");
        return true;
    }


}
