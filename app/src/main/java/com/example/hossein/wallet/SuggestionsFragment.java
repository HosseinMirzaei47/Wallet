package com.example.hossein.wallet;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class SuggestionsFragment extends Fragment implements SuggestionsAdapter.ItemClickListener {

    private RecyclerView suggestions;
    private SuggestionsAdapter adapter;
    private MyDataBase dataBase = null;

    private Button done;
    private Button addItem;

    private static final String TAG = "jalil";
    private ArrayList<String> items = new SuggestionsList().getList();

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

        suggestions.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new SuggestionsAdapter(items, getActivity());
        adapter.setClickListener(this);
        suggestions.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View view, int position) {
        String itemName = ((TextView) Objects.requireNonNull(suggestions.findViewHolderForAdapterPosition(position)).itemView.findViewById(R.id.suggestion)).getText().toString();
        dataBase.addData(itemName, 10);
        Log.i(TAG, itemName);
    }

    @Override
    public void onAttach(Context context) {
        dataBase = new MyDataBase(getActivity());
        super.onAttach(context);
    }

    private void findViews(View view) {

        suggestions = view.findViewById(R.id.suggestions);
        done = view.findViewById(R.id.doneButton);
        addItem = view.findViewById(R.id.addItem);

    }
}
