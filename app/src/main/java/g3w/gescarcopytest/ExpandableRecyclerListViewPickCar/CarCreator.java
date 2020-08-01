package g3w.gescarcopytest.ExpandableRecyclerListViewPickCar;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manuel Cruz on 15/08/2017.
 */

public class CarCreator {

    static CarCreator _carCreator;
    List<CarParent> _carParents;

    public CarCreator(Context context, ArrayList<String> licensePlates)
    {
        _carParents = new ArrayList<>();
        for(int i = 0; i < licensePlates.size(); i++)
        {
            CarParent title = new CarParent(licensePlates.get(i));
            _carParents.add(title);
        }
    }

    public static CarCreator get(Context context, ArrayList<String> licensePlates)
    {
        if(_carCreator == null)
            _carCreator = new CarCreator(context, licensePlates);
        return _carCreator;
    }

    public List<CarParent> getAll()
    {
        return _carParents;
    }
}
