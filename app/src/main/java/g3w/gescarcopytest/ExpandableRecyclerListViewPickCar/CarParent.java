package g3w.gescarcopytest.ExpandableRecyclerListViewPickCar;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

/**
 * Created by Manuel Cruz on 15/08/2017.
 */

public class CarParent implements ParentObject {
    private List<Object> mChildrenList;
    private String title;

    // Construtor
    public CarParent(String title) {
        this.title = title;
    }

    // Métodos Get e Set
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public List<Object> getChildObjectList() {
        return mChildrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        mChildrenList = list;
    }
}
