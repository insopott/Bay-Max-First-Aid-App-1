package com.example.lenovo.baymax2;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.net.Uri;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.lenovo.baymax2.databinding.ContactviewBinding;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        static Context c;
        public static Context getC(){
            return c;
    }
     public static ArrayList<Contacts>contactList=new ArrayList<Contacts>();
    @SuppressWarnings("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        c=getApplication().getApplicationContext();
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Menu menu=navigationView.getMenu();
        RecyclerView rv= (RecyclerView) findViewById(R.id.listv);
        LinearLayoutManager lm=new LinearLayoutManager(this);
        lm.setOrientation(1);
        rv.setLayoutManager(lm);
        ArrayList<Name>list=new ArrayList<Name>();


        //contactList.add(new Contacts("Bourne","+63 9774 585 029",getResources().getDrawable(R.drawable.ic_menu_camera)));
        //contactList.add(new Contacts("Ben","+63 977 855 5692",getResources().getDrawable(R.drawable.ic_menu_camera)));
        contactList.add(new Contacts("Gaoat Hospital","(077)617 1457",getResources().getDrawable(R.drawable.ic_menu_camera)));
        contactList.add(new Contacts("LCGH","(077) 772 8828",getResources().getDrawable(R.drawable.ic_menu_camera)));
        contactList.add(new Contacts("Provincial Hospital","+63 977 855 5692",getResources().getDrawable(R.drawable.ic_menu_camera)));
        contactList.add(new Contacts("Black Nazarene","(077) 773 1757",getResources().getDrawable(R.drawable.ic_menu_camera)));
        //menus

        for (int i=0;i<=contactList.size()-1;i++){

            MenuItem m=menu.add(R.id.g,i+1,i+1,null);

            ContactviewBinding contact=ContactviewBinding.inflate(getLayoutInflater());
            contact.setContacts(contactList.get(i));
            m.setActionView(contact.getRoot());
        }
        list.add(new Name("First",1));
        list.add(new Name("Second",2));
        list.add(new Name("Third",3));
        //crating the database;
       String [] sick= getResources().getStringArray(R.array.sickness);
        ArrayList<ContentValues>tb1=new ArrayList<ContentValues>();
        ArrayList<ContentValues>tb2=new ArrayList<ContentValues>();
        for(int i=0;i<=sick.length-1;i++){
            ContentValues content=new ContentValues();
            content.put("title",sick[i]);
            content.put("key",i+1);
            tb1.add(content);



        }
        ArrayList<TypedArray> array = new ArrayList<TypedArray>();
        try {
            Class<R.array> res = R.array.class;
            Field field;
            int counter = 1;

            do {
                field = res.getField("treatment" + "_" + counter);
                array.add(getResources().obtainTypedArray(field.getInt(null)));
                counter++;
            } while (field != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int [] d={
                R.drawable.ic_menu_camera,
        };

       for (TypedArray item:array){
            ContentValues content=new ContentValues();
            content.put("steps",item.getString(0));
            content.put("aid_id",item.getInt(2,1));

            content.put("pic",item.getInt(1,0));
            tb2.add(content);
        }



        DBHelper helper=new DBHelper(this,tb1,tb2);
        SQLiteDatabase db=helper.getWritableDatabase();
        ArrayList<Name>list2=new ArrayList<Name>();
        String [] colums={"_id","title","key"};
        Cursor c=db.query("sickness",colums,null,null,null,null,null);
        while (c.moveToNext()){
            list2.add(
                    new Name(c.getString( c.getColumnIndex(colums[1]) ),
                            c.getInt( c.getColumnIndex(colums[2]) ) )
            );
        }


        rv.setAdapter(new ListAdapter(list2));




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
               // Handle navigation view item clicks here.
        //Toast.makeText(this,item.getItemId(),Toast.LENGTH_LONG).show();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        int id=item.getItemId();
       // Toast.makeText(getApplicationContext(),""+id,Toast.LENGTH_LONG).show();
        String number="tel:";
        if(id>=1 && id<=contactList.size()){
            number+=contactList.get(id-1).number;
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(number));
            startActivity(callIntent);
        }

        return true;
    }
}
