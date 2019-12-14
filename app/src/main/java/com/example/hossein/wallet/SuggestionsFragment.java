package com.example.hossein.wallet;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class SuggestionsFragment extends Fragment {

    private RecyclerView suggestions;
    private SuggestionsAdapter adapter;

    private Button done;

    private ArrayList<String> items = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_suggestions, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);

        initItemList();

        doneClicked();

    }

    private void doneClicked() {

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFragmentManager().popBackStack();
                
            }
        });

    }

    private void initItemList() {

        items.add("Food");
        items.add("Groceries");
        items.add("Clothes");
        items.add("Internet");
        items.add("Hanging out");
        items.add("Rainy day");
        items.add("Transportation");

        suggestions.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new SuggestionsAdapter(items, getActivity());
        suggestions.setAdapter(adapter);

    }

    private void findViews(View view) {

        suggestions = view.findViewById(R.id.suggestions);

        done = view.findViewById(R.id.doneButton);

    }

}
