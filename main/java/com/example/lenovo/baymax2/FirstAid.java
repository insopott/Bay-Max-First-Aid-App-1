package com.example.lenovo.baymax2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lenovo.baymax2.databinding.ContactviewBinding;
import com.example.lenovo.baymax2.databinding.FirstaidBinding;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class FirstAid extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static int[] pic={R.drawable.ic_menu_camera,
            R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,
            R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,
            R.drawable.i,R.drawable.j,R.drawable.k,R.drawable.l,
            R.drawable.m,R.drawable.n,R.drawable.o,R.drawable.p,
            R.drawable.q,R.drawable.r,R.drawable.s,R.drawable.t,
            R.drawable.u,R.drawable.v,R.drawable.w,R.drawable.x,
            R.drawable.y,R.drawable.z,R.drawable.za,R.drawable.zb,
            R.drawable.zc,R.drawable.zd,R.drawable.ze,R.drawable.zf,
            R.drawable.zg,R.drawable.zh,R.drawable.zi,R.drawable.zj,
            R.drawable.zk,R.drawable.zl,R.drawable.zm,R.drawable.zn,
    };
    public  int pos=0;
    public int res;
    TextToSpeech tt;
    public static View v;
    FirstaidBinding fa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      // LinearLayout parent= (LinearLayout) findViewById(R.id.x);
       // ViewGroup parent= (ViewGroup) LayoutInflater.from(this).inflate(R.layout.content_first_aid,null);
        setContentView(R.layout.activity_first_aid);





        DBHelper helper=new DBHelper(this,null,null);
        SQLiteDatabase db=helper.getWritableDatabase();
        final ArrayList<Steps> list2=new ArrayList<Steps>();
        String [] colums={"_id","steps","aid_id","pic"};
        int x=getIntent().getExtras().getInt("key");
        Cursor c=db.query("cure",colums,"aid_id="+x,null,null,null,null);



        while (c.moveToNext()){

          list2.add(new Steps(

                  c.getString(c.getColumnIndex("steps")),
                  getResources().getDrawable(pic[c.getInt( c.getColumnIndex("pic") )]),"yes"
          ));
        }//end of while
        //list2.add(new Steps("No",getResources().getDrawable(R.drawable.ic_menu_camera),"yes"));
       // if(list2.size()>0)
         //   Toast.makeText(getApplicationContext(),list2.get(0).getSteps(),Toast.LENGTH_LONG).show();
       // RecyclerView rv= (RecyclerView) findViewById(R.id.list2);
       /*LinearLayoutManager lm=new LinearLayoutManager(this);
        lm.setOrientation(0);
        rv.setLayoutManager(lm);
        rv.setAdapter(new FirstAidAdapter(getApplication().getApplicationContext(),list2));*/
        //FirstaidBinding fa=First//.inflate(getLayoutInflater(),R.layout.firstaid,parent,false);
       // fa.setSteps(list2.get(pos));
       // ViewGroup viewGroup=(ViewGroup)parent.getParent();




       fa=DataBindingUtil.setContentView(this,R.layout.firstaid);
        fa.setSteps(list2.get(pos));//new Steps("Yes",getResources().getDrawable(R.drawable.ic_menu_camera),"Yea"));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Menu menu=navigationView.getMenu();

  tt=new TextToSpeech(FirstAid.this,new TextToSpeech.OnInitListener(){

            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                    res=  tt.setLanguage(Locale.UK);
                }else{
                    Snackbar.make(v, "Can't Play Speech", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });//end of on text;
        this.setTitle(getIntent().getExtras().getString("title"));
        for (int i=0;i<=MainActivity.contactList.size()-1;i++){

            MenuItem m=menu.add(R.id.g,i+1,i+1,null);

            ContactviewBinding contact=ContactviewBinding.inflate(getLayoutInflater());
            contact.setContacts(MainActivity.contactList.get(i));
            m.setActionView(contact.getRoot());
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //v=view;

                if(res==TextToSpeech.LANG_NOT_SUPPORTED){
                    Snackbar.make(view, "Can't ", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{
                    tt.speak(list2.get(pos).getSteps(),TextToSpeech.QUEUE_FLUSH,null);
                }

            }
        });
        FloatingActionButton fab3= (FloatingActionButton) findViewById(R.id.fab2);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos--;
                if(pos>=0){

                    fa.setSteps(list2.get(pos));
                }else{
                    pos=0;
                    Snackbar.make(v, "This is the first step", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            }
        });
        FloatingActionButton fab2= (FloatingActionButton) findViewById(R.id.fab3);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos++;
                if(pos<=list2.size()-1){
                    fa.setSteps(list2.get(pos));
                }else{
                    pos=list2.size()-1;
                    Snackbar.make(v, "This is the last step", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            }
        });




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
        //Toast.makeText(this,"Back is pressed",Toast.LENGTH_LONG).show();

      //  this.pos=0;
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.first_aid, menu);
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
        int id = item.getItemId();

        String number="tel:";
        if(id>=1 && id<=MainActivity.contactList.size()){
            number+=MainActivity.contactList.get(id-1).number;
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(number));
            startActivity(callIntent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
