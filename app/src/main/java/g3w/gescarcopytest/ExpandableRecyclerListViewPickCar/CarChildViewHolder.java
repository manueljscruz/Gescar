package g3w.gescarcopytest.ExpandableRecyclerListViewPickCar;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;

import g3w.gescarcopytest.R;

/**
 * Created by Manuel Cruz on 15/08/2017.
 */

public class CarChildViewHolder extends ChildViewHolder {

    public TextView mSelectOptionTrackText;
    public TextView mSelectOptionDetailsText;
    // public Button mSelectOptionTrackButton;
    // public Button mSelectOptionDetailsButton;

    public CarChildViewHolder(View itemView)
    {
        super(itemView);
        mSelectOptionTrackText = (TextView) itemView.findViewById(R.id.tv_option_select1);
        mSelectOptionDetailsText = (TextView) itemView.findViewById(R.id.tv_option_select2);
        // mSelectOptionTrackButton = (Button) itemView.findViewById(R.id.btn_trackvehicle);
        // mSelectOptionDetailsButton = (Button) itemView.findViewById(R.id.btn_details);
    }
}
