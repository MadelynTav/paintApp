package nyc.apprentice.madelyntavarez.paintapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import butterknife.Bind;
import butterknife.BindDimen;

public class MainActivity extends AppCompatActivity {


    ImageButton currBrushSize;
    ImageButton currColor;
    ImageButton erase;
    float smallBrush;
    float mediumBrush;
    float largeBrush;
    private CustomView customView;
    Dialog colorDialog;
    Context context=this;
    Dialog brushDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        currBrushSize= (ImageButton) findViewById(R.id.brush_button);
        currColor = (ImageButton) findViewById(R.id.color_button);
        customView = (CustomView) findViewById(R.id.drawing_view);
        erase = (ImageButton) findViewById(R.id.erase);
        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);

    }

    @Override
    protected void onResume() {
        super.onResume();

        currBrushSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.setErase(false);
                brushDialog = new Dialog(context);
                brushDialog.setContentView(R.layout.brush_size_picker);
                brushDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                brushDialog.show();
            }
        });

        currColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.setErase(false);
                colorDialog = new Dialog(context);
                colorDialog.setContentView(R.layout.color_picker);
                colorDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                colorDialog.show();
            }
        });

        erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.setErase(true);
                brushDialog = new Dialog(context);
                brushDialog.setContentView(R.layout.brush_size_picker);
                brushDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                brushDialog.show();
            }
        });
    }

    public void changeBrushSize(View v){
        float tag= Integer.parseInt(String.valueOf(v.getTag()));
        customView.setBrushSize(tag);

        if (!customView.isErase()) {
            customView.setLastBrushSize(tag);
            Log.i("size","UP");
        }

        brushDialog.dismiss();
    }

    public void changeBrushColor(View v){
        String color=v.getTag().toString();
        int color1= Color.parseColor(color);
        customView.setPaintColor(color1);
        colorDialog.dismiss();


    }
}
