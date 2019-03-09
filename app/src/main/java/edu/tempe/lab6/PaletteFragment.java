package edu.tempe.lab6;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

public class PaletteFragment extends Fragment {
    ColorFragmentInterface colorListener;
    private Spinner color_spinner;

    public interface ColorFragmentInterface {
        void fragmentClicked(int id);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_palette,container,false);

        Resources res = getResources();
        String[] colors = res.getStringArray(R.array.color_array_en);
        String[] colorsText = res.getStringArray(R.array.color_array);

        color_spinner = v.findViewById(R.id.color_spinner);
        ColorAdapter adapter = new ColorAdapter(PaletteFragment.super.getContext(), colors, colorsText);
        color_spinner.setAdapter(adapter);

        color_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i != 0) {
                    colorListener.fragmentClicked(i);
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof ColorFragmentInterface) {
            colorListener = (ColorFragmentInterface) context;
        } else {
            throw new RuntimeException(context.toString()
            + " must implement ColorFragmentInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        colorListener = null;
    }
}
