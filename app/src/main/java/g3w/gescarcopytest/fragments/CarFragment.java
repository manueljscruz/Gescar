package g3w.gescarcopytest.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


import g3w.gescarcopytest.Adapters.CarAdapter;
import g3w.gescarcopytest.CarDataset;
import g3w.gescarcopytest.ListCar;
import g3w.gescarcopytest.R;

/**
 * Created by Manuel Cruz on 29/10/2017.
 */

public class CarFragment extends android.support.v4.app.Fragment {

    private RecyclerView mRecyclerView;
    private ArrayList<CarDataset> mList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_listcars2);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                CarAdapter adapter = (CarAdapter) mRecyclerView.getAdapter();

                if (mList.size()== llm.findLastCompletelyVisibleItemPosition()+1)
                {
                    ArrayList<CarDataset> listAux = ((ListCar) getActivity()).getSetCarList();

                    for (int i = 0; i < listAux.size(); i++)
                    {
                        adapter.addListItem(listAux.get(i), mList.size());
                    }
                }
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(llm);

        mList = ((ListCar) getActivity()).getSetCarList();
        CarAdapter adapter = new CarAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(adapter);
        return view;
    }


}