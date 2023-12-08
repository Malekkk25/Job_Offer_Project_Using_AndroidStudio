/*package com.example.miniprojet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.miniprojet.adapter.CandidateAdapter;
import com.example.miniprojet.adapter.MyAdapter;
import com.example.miniprojet.entites.Job;
import com.example.miniprojet.entites.Post;
import com.example.miniprojet.entites.User;
import com.google.android.material.navigation.NavigationView;
import com.google.api.Usage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class listCandidatesActivity extends AppCompatActivity {
    private EditText name, email, contact, pass, confPass, exp, proExp, skills, education, summary,pdf;
    private String idJob = "";
    private DatabaseReference database;

    private FirebaseAuth authProfile;
    private List<Post> appliedJobsList;
    private List<User> UsersList;

    RecyclerView Condi_recyclerView;

    private CandidateAdapter candidateAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_candidates);

        Condi_recyclerView = findViewById(R.id.list_recycler_view);
        Intent i = getIntent();



        appliedJobsList = new ArrayList<>();
        UsersList = new ArrayList<>();

        idJob = i.getStringExtra("idjob");




        DatabaseReference historyRef = FirebaseDatabase.getInstance().getReference("Post");

        historyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                appliedJobsList.clear();
                UsersList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Post post = snapshot.getValue(Post.class);
                    if (post != null && post.getIdJob().equals(idJob)) {
                        appliedJobsList.add(post);
                    }
                }

                // Assuming you have a method to get idJob from appliedJobsList
                List<String> idUserList = getIdUserList(appliedJobsList);

                DatabaseReference UsersRef = FirebaseDatabase.getInstance().getReference("Users");

                UsersRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            User user = snapshot.getValue(User.class);
                            if (user != null && idUserList.contains(String.valueOf(user.getIdUser()))) {
                                name.setText(user.getFullName());
                                email.setText(user.getEmail());
                                contact.setText(user.getContact());
                                exp.setText(String.valueOf(user.getExperiences()));
                                proExp.setText(user.getSpecialization());
                                skills.setText(user.getSkills());
                                education.setText(user.getEducation());
                                summary.setText(user.getSummary());
                            }
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle error
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });
    }
    private List<String> getIdUserList(List<Post> appliedJobsList) {
        List<String> idJobList = new ArrayList<>();
        for (Post post : appliedJobsList) {
            idJobList.add(post.getIdJob());
        }
        return idJobList;
    }*/