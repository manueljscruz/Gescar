package g3w.gescarcopytest.ExpandableRecyclerListViewPickCar;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import g3w.gescarcopytest.R;

/**
 * Created by Manuel Cruz on 15/08/2017.
 */

public class CarParentViewHolder extends ParentViewHolder {


    public TextView mCarTitleTextView;

    public CarParentViewHolder(View itemView)
    {
        super(itemView);
        mCarTitleTextView = (TextView) itemView.findViewById(R.id.tv_parentTitle);
    }


}
