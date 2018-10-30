package tw.idv.holybible.expense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PageFragment extends Fragment {

    private int mPosition;

    public PageFragment() {

    }

    static Fragment newInstance(int position) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPosition = getArguments() != null ? getArguments().getInt("position") : 0;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View page;
        switch (mPosition) {
            default:
            case 0:
                page = inflater.inflate(R.layout.fragment_page1, container, false);
                break;
            case 1:
                page = inflater.inflate(R.layout.fragment_page2, container, false);
                break;
            case 2:
                page = inflater.inflate(R.layout.fragment_page3, container, false);
                break;
            case 3:
                page = inflater.inflate(R.layout.fragment_page4, container, false);
                break;
        }
        return page;
    }
}
