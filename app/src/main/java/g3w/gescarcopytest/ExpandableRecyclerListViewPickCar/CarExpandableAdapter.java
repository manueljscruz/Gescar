package g3w.gescarcopytest.ExpandableRecyclerListViewPickCar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

import g3w.gescarcopytest.R;

/**
 * Created by Manuel Cruz on 15/08/2017.
 */

public class CarExpandableAdapter extends ExpandableRecyclerAdapter<CarParentViewHolder,CarChildViewHolder> {

    private LayoutInflater inflater;

    public interface OnRecyclerViewItemClickListener
    {

    }

    public CarExpandableAdapter(Context context, List<ParentObject> parentItemList)
    {
        super(context, parentItemList);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public CarParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.list_item_car_parent, viewGroup, false);
        return new CarParentViewHolder(view);
    }

    @Override
    public CarChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.list_item_car_child, viewGroup, false);
        CarChildViewHolder carChildViewHolder = new CarChildViewHolder(view);
        return carChildViewHolder;
    }

    @Override
    public void onBindParentViewHolder(CarParentViewHolder carParentViewHolder, int i, Object parentObject) {
        CarParent title = (CarParent) parentObject;
        carParentViewHolder.mCarTitleTextView.setText(title.getTitle());
    }

    @Override
    public void onBindChildViewHolder(CarChildViewHolder carChildViewHolder, int i, Object childObject) {
        CarChild title = (CarChild) childObject;
        carChildViewHolder.mSelectOptionTrackText.setText(title.getOption1());
        carChildViewHolder.mSelectOptionDetailsText.setText(title.getOption2());
    }


}
