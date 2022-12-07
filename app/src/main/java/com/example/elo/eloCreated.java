package com.example.elo;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.adapter.adminEloProfileAdapter;
import com.example.elo.adapter.deleteIconsAdapter;
import com.example.elo.adapter.eloProfileAdapter;
import com.example.elo.adapter.tagAdapter;
import com.example.elo.model.DeleteIcons;
import com.example.elo.model.EloProfile;
import com.example.elo.model.tagCategory;

import java.util.ArrayList;
import java.util.List;

public class eloCreated extends AppCompatActivity {
    final Context context = this;
    RecyclerView eloRecycler, iconsRecycler;
    static deleteIconsAdapter deleteIconsAdapter;
    static adminEloProfileAdapter eloAdapter;
    static List<EloProfile> eloList = new ArrayList<>();
    static List<EloProfile> allEloList = new ArrayList<>();
    static List<DeleteIcons> iconsList = new ArrayList<>();

    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elo_created);

        eloList.clear();
        eloList.add(new EloProfile(1, "Java для начинающих",
                "Курс Java для\nJunior-разработчиков",
                "Курс Java для Junior-разработчиков\nОтлично подойдет для развития навыков работы с backend'ом на Java, в первую очередь для работы с сервером",
                "Java для начинающих", 2, "65%"));
        eloList.add(new EloProfile(2, "C# для начинающих",
                "Курс по C#\nдля начинающих",
                "Этот курс поможет освоить C# так, чтобы быть в нём, как рыба в воде, а также подтянуть знания в области ООП",
                "C# для начинающих", 2, "65%"));
        eloList.add(new EloProfile(3, "SQL for juniors",
                "SQL для самых маленьких\nи не только",
                "Азы работы с базами данных, все важные аспекты написания и обработки запросов, особенности работы с PostgreSQL",
                "SQL for juniors", 6, "65%"));

        allEloList.addAll(eloList);
        setEloRecycler(eloList);

        iconsList.clear();
        iconsList.add(new DeleteIcons(1));
        iconsList.add(new DeleteIcons(2));
        iconsList.add(new DeleteIcons(3));

        setIconsRecycler(iconsList);

        back = findViewById(R.id.backButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setEloRecycler(List<EloProfile> eloList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        eloRecycler = findViewById(R.id.eloRecycler);
        eloRecycler.setHasFixedSize(true);
        eloRecycler.setNestedScrollingEnabled(false);
        eloRecycler.setLayoutManager(layoutManager);
        eloAdapter = new adminEloProfileAdapter(this, eloList);
        eloRecycler.setAdapter(eloAdapter);
    }

    private void setIconsRecycler(List<DeleteIcons> iconsList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        iconsRecycler = findViewById(R.id.deleteRecycler);
        iconsRecycler.setHasFixedSize(true);
        iconsRecycler.setNestedScrollingEnabled(false);
        iconsRecycler.setLayoutManager(layoutManager);
        deleteIconsAdapter = new deleteIconsAdapter(this, iconsList);
        iconsRecycler.setAdapter(deleteIconsAdapter);
    }

    public static void removeElo(int id) {

        List<EloProfile> filter = new ArrayList<>();
        List<DeleteIcons> filter_del = new ArrayList<>();

        for (EloProfile c : eloList) {
            if(c.getId() != id)
                filter.add(c);
        }
        for (DeleteIcons c : iconsList) {
            if (c.getId() != id)
                filter_del.add(c);
        }

        eloList.clear();
        eloList.addAll(filter);
        iconsList.clear();
        iconsList.addAll(filter_del);

        eloAdapter.notifyDataSetChanged();
        deleteIconsAdapter.notifyDataSetChanged();
    }
}
