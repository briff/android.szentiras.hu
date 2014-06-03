package hu.szentiras.android.daily;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import hu.szentiras.android.R;

/**
 * Created by Bertalan on 6/3/2014.
 */
public class ReadingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.daily_reader, container, false);
        return result;
    }
}
