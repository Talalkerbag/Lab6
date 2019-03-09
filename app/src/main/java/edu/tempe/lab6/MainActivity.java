package edu.tempe.lab6;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements PaletteFragment.ColorFragmentInterface{
    private PaletteFragment palette;
    private CanvasFragment canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Palette & Canvas Fragment");

        palette = new PaletteFragment();
        canvas = new CanvasFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_palette, palette)
                .replace(R.id.container_canvas, canvas)
                .commit();
    }

    @Override
    public void fragmentClicked(int id) {
        Resources res = getResources();
        String[] colors = res.getStringArray(R.array.color_array_en);
        canvas.setBackgroundColor(colors[id]);
    }
}
