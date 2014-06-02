package hu.szentiras.android;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Bertalan on 5/30/2014.
 */
public class DailyReadingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_reading, container, false);
        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(buildAdapter());

        return view;
    }

    private PagerAdapter buildAdapter() {
        return (new SampleAdapter(getActivity(), getChildFragmentManager()));
    }

    public static class SampleAdapter extends FragmentPagerAdapter {
        Context ctxt = null;

        public SampleAdapter(Context ctxt, FragmentManager mgr) {
            super(mgr);
            this.ctxt = ctxt;
        }

        @Override
        public int getCount() {
            return (10);
        }

        @Override
        public Fragment getItem(int position) {
            return (EditorFragment.newInstance(position));
        }

        @Override
        public String getPageTitle(int position) {
            return (EditorFragment.getTitle(ctxt, position));
        }
    }

    public static class EditorFragment extends Fragment {
        private static final String KEY_POSITION="position";

        static EditorFragment newInstance(int position) {
            EditorFragment frag=new EditorFragment();
            Bundle args=new Bundle();

            args.putInt(KEY_POSITION, position);
            frag.setArguments(args);

            return(frag);
        }

        static String getTitle(Context ctxt, int position) {
            return(String.format(ctxt.getString(R.string.hint), position + 1));
        }

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container,
                                 Bundle savedInstanceState) {
            View result=inflater.inflate(R.layout.editor, container, false);
            EditText editor=(EditText)result.findViewById(R.id.editor);
            int position=getArguments().getInt(KEY_POSITION, -1);

            editor.setHint(getTitle(getActivity(), position));

            return(result);
        }
    }

}
