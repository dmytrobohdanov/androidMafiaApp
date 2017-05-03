package com.dmytrobohdanov.getmafianumber.Utils.DataBaseUtils;

import android.util.Log;

import com.dmytrobohdanov.getmafianumber.Utils.DataBaseUtils.DataModels.PlayerDataModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import io.reactivex.subjects.ReplaySubject;

public class DatabaseUtils {
    public static void init() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        Query query = ref.orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("ping", "initDb: " + dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public static void addNewUser(PlayerDataModel playerDataModel) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        ref.child(DatabaseConstants.DB_PLAYERS)
                .push()
                .setValue(playerDataModel);
//                .addOnCompleteListener(task -> subject.onNext(null));
    }

    public static void getPlayersList(ReplaySubject<ArrayList<PlayerDataModel>> subject) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child(DatabaseConstants.DB_PLAYERS);
        Query query = ref.orderByKey();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<PlayerDataModel> playerDataModels = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    playerDataModels.add(snapshot.getValue(PlayerDataModel.class));
                }

                subject.onNext(playerDataModels);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
