package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.List;

public class CountryListActivity extends AppCompatActivity {

    private DBManager dbManager ;
    private ListView listView ;
    private SimpleCursorAdapter adapter ;
    final String[ ] from = new String[ ]{ DBHelper._ID , DBHelper.SUBJECT , DBHelper.DESC } ;
    final int[ ]  to = new int[] { R.id.id , R.id.title, R.id.desc } ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_emp_list);
        dbManager = new DBManager( this );
        dbManager.open();
        Cursor cursor = dbManager.fetch( LoginActivity.idd );
        listView = findViewById(R.id.list_view) ;
        listView.setEmptyView(findViewById(R.id.empty));
        adapter = new SimpleCursorAdapter( this , R.layout.activity_view_record , cursor ,
                from , to, 0) ;
        adapter.notifyDataSetChanged();
        listView.setAdapter( adapter );

        // Onclicklist for List Item ;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                TextView idTextView = view.findViewById( R.id.id );
                TextView titleTextView = view.findViewById( R.id.title ) ;
                TextView descTextView = view.findViewById( R.id.desc ) ;

                String _id_ = idTextView.getText().toString() ;
                String title = titleTextView.getText().toString() ;
                String desc = descTextView.getText().toString() ;

                Intent modify_intent = new Intent(getApplicationContext() ,
                           ModifyCountryActivity.class ) ;

                modify_intent.putExtra( "title" , title ) ;
                modify_intent.putExtra( "desc" , desc ) ;
                modify_intent.putExtra( "id" , _id_ ) ;

                startActivity( modify_intent );

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

       int id =  item.getItemId() ;
       if( id == R.id.add_record ){
           Intent  add = new Intent(this , AddCountryActivity.class ) ;
           startActivity( add );

       }

        return super.onOptionsItemSelected(item);
    }
}