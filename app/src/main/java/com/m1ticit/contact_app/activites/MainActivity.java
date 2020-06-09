package com.m1ticit.contact_app.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.m1ticit.contact_app.Adapter.RecyclerAdapter;
import com.m1ticit.contact_app.Interfaces.RecyclerViewClickInterface;
import com.m1ticit.contact_app.R;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;
import com.valdesekamdem.library.mdtoast.MDToast;


import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickInterface {
    final  static  String TAG="MainActvity_TAG";
    TextView actions,add_contact,close_fragment, add_contact_fragment,profession_flag_val,title;
    EditText frist_name, last_name;
    Animation animte_open,animte_exite;
    RelativeLayout conatiner_add_tasks;
    MDToast mdToast;
    public int  pos=0;

    public RecyclerView.Adapter adapter;

    ArrayList<contact> contact_array;
    RecyclerView list_contact_recycleur;

    Boolean delete_confimer=false;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        GetData();
        Swipe_Delete();




    }

    private void Swipe_Delete() {

        ItemTouchHelper helper =new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    //TODO: display Alerte to confimer:



                    int position =viewHolder.getAdapterPosition();

                    contact_array.remove(position);

            }
        });

        //TODO: attaching the helper into the recycleurview
        helper.attachToRecyclerView(list_contact_recycleur);

    }




    private void GetData() {
        profession_list();
        //TODO: init the contact:
        list_contact_recycleur = (RecyclerView) findViewById(R.id.list_contact);
        Init();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        list_contact_recycleur.setLayoutManager(mLayoutManager);
        adapter = new RecyclerAdapter(contact_array,this);
        list_contact_recycleur.setAdapter(adapter);

    }

    private void profession_list() {
        RadioGroup profession_radio_group = (RadioGroup) findViewById(R.id.profession_radio_group);

        profession_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String s="";
                switch (checkedId) {
                    case R.id.manager:
                        //catergoy personnel selected:
                        showToast("manager");
                        getCategory("manager");
                        break;
                    case R.id.dev:
                        //catergoy work selected:
                        showToast("Developer");
                        getCategory("Developer");
                        break;
                    case R.id.frend:
                        //catergoy study selected:
                        showToast("friend");
                        getCategory("friend");
                        break;
                    case R.id.desgner:
                        //catergoy meeting selected:
                        showToast("designer");
                        getCategory("designer");
                        break;
                    case R.id.fam_ly:
                        //catergoy shopping selected:
                        showToast("family");
                        getCategory("family");
                        break;

                }

            }

        });

    }
    public void getCategory(String ms){
        profession_flag_val.setText(ms);
    }


    private void showToast(String msg) {
        mdToast = MDToast.makeText(getApplicationContext(), "You Select "+msg+" As Profession", Toast.LENGTH_SHORT, MDToast.TYPE_SUCCESS);
        mdToast.show();
    }


    private void Init() {
        //list_contact=(RecyclerView)findViewById(R.id.list_contact);
        //TODO: init the wight :
        title=(TextView)findViewById(R.id.title);
        title.setText("Add New Contact");
        add_contact=(TextView)findViewById(R.id.add_contact);
        close_fragment=(TextView)findViewById(R.id.close_fragment);
        actions=(TextView)findViewById(R.id.actions);
        conatiner_add_tasks=(RelativeLayout)findViewById(R.id.conatiner_add_tasks);
        add_contact_fragment=(TextView)findViewById(R.id.add_contact_fragment);
        profession_flag_val=(TextView)findViewById(R.id.category_flag_val);
        frist_name=(EditText)findViewById(R.id.frist_name);
        last_name=(EditText)findViewById(R.id.last_name);

        contact_array=new ArrayList<contact>();

        //TODO: init the animation :
        animte_open= AnimationUtils.loadAnimation(this,R.anim.open_aimation);
        animte_exite= AnimationUtils.loadAnimation(this,R.anim.exicte_animation);


        add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO: starting the animation & open the new contact fragment :
                conatiner_add_tasks.startAnimation(animte_open);
                conatiner_add_tasks.setVisibility(View.VISIBLE);

            }
        });


        close_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conatiner_add_tasks.startAnimation(animte_exite);
                conatiner_add_tasks.setVisibility(View.INVISIBLE);
                frist_name.setText("");
                last_name.setText("");
                profession_flag_val.setText("");
                title.setText("Add New Contact");
            }
        });

        add_contact_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Chacking if the input are not empty :
               if  ((frist_name.getText().toString().equals("")) ||(last_name.getText().toString().equals("")) || (profession_flag_val.getText().toString().equals(""))){
                   mdToast = MDToast.makeText(getApplicationContext(), "You Have Empty Input", Toast.LENGTH_SHORT, MDToast.TYPE_ERROR);
                   mdToast.show();

                }
               else if (!(frist_name.getText().toString().equals("")) &&(!last_name.getText().toString().equals("")) && (!profession_flag_val.getText().toString().equals(""))){
                  // String id=String.valueOf((int)Math.random());
                   //Log.d(TAG, "ID : "+id);
                    if (title.getText().equals("Add New Contact")){
                        contact contact_num_id= new contact(frist_name.getText().toString(), last_name.getText().toString(),profession_flag_val.getText().toString());
                        contact_array.add(contact_num_id);
                        adapter.notifyDataSetChanged();
                        frist_name.setText("");
                        last_name.setText("");
                        profession_flag_val.setText("");
                        contact_num_id.toString();
                        title.setText("Add New Contact");
                    }
                    else if (title.getText().equals("Update Contact")){
                        contact contact_num_id = new contact(frist_name.getText().toString(), last_name.getText().toString(), profession_flag_val.getText().toString());
                        contact_array.set(pos,contact_num_id);
                        adapter.notifyDataSetChanged();
                        frist_name.setText("");
                        last_name.setText("");
                        profession_flag_val.setText("");
                        contact_num_id.toString();
                        title.setText("Add New Contact");

                    }
                    pos=0;


                   conatiner_add_tasks.setAnimation(animte_exite);
                   conatiner_add_tasks.setVisibility(View.INVISIBLE);
               }
            }
        });


    }


    @Override
    public void onItemClick(int position) {


    }




    @Override
    public void onLongItemClick(int position) {

        //Toast.makeText(MainActivity.this, "Position " + position, Toast.LENGTH_LONG).show();
        //TODO: starting the animation & open the new contact fragment :
        pos=position;
        title.setText("Update Contact");
        conatiner_add_tasks.startAnimation(animte_open);
        conatiner_add_tasks.setVisibility(View.VISIBLE);


    }

}
