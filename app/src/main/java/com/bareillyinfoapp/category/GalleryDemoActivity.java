package com.bareillyinfoapp.category;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class GalleryDemoActivity extends Activity {

    private ImageView selectedImageView;

    private ImageView leftArrowImageView;

    private ImageView rightArrowImageView;

    private Gallery gallery;

    private int selectedImagePosition = 0;

    private List<Drawable> drawables;

    private GalleryImageAdapter galImageAdapter;
    float startXValue = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        getDrawablesList();
        setupUI();
    }

    private void setupUI() {

        selectedImageView = (ImageView) findViewById(R.id.selected_imageview);
        leftArrowImageView = (ImageView) findViewById(R.id.left_arrow_imageview);
        rightArrowImageView = (ImageView) findViewById(R.id.right_arrow_imageview);
        gallery = (Gallery) findViewById(R.id.gallery);

        selectedImageView.setOnTouchListener(new View.OnTouchListener() {
            float nPicturePositionM = 0;
            public boolean onTouch(final View view, final MotionEvent event) {

                float endXValue = 0;
                float x1 = event.getAxisValue(MotionEvent.AXIS_X);
                int action = MotionEventCompat.getActionMasked(event);
                switch (action) {
                    case (MotionEvent.ACTION_DOWN):
                        startXValue = event.getAxisValue(MotionEvent.AXIS_X);

                        return true;

                    case (MotionEvent.ACTION_UP):
                        endXValue = event.getAxisValue(MotionEvent.AXIS_X);
                        if (endXValue > startXValue) {
                            if (endXValue - startXValue > 100) {
                                System.out.println("Left-Right");
                                if (selectedImagePosition > 0) {
                                    --selectedImagePosition;

                                }

                                gallery.setSelection(selectedImagePosition, false);

                            }
                        }else {
                            if (startXValue -endXValue> 100) {
                                System.out.println("Right-Left");

                                if (selectedImagePosition < drawables.size() - 1) {
                                    ++selectedImagePosition;

                                }

                                gallery.setSelection(selectedImagePosition, false);
                            }
                        }
                        return true;


                    default:
                        return true;
                }
            }
        });

        leftArrowImageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (selectedImagePosition > 0) {
                    --selectedImagePosition;

                }

                gallery.setSelection(selectedImagePosition, false);
            }
        });

        rightArrowImageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (selectedImagePosition < drawables.size() - 1) {
                    ++selectedImagePosition;

                }

                gallery.setSelection(selectedImagePosition, false);

            }
        });

        gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                selectedImagePosition = pos;

                if (selectedImagePosition > 0 && selectedImagePosition < drawables.size() - 1) {

                    leftArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_left_enabled));
                    rightArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_right_enabled));

                } else if (selectedImagePosition == 0) {

                    leftArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_left_disabled));

                } else if (selectedImagePosition == drawables.size() - 1) {

                    rightArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_right_disabled));
                }

                changeBorderForSelectedImage(selectedImagePosition);
                setSelectedImage(selectedImagePosition);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });

        galImageAdapter = new GalleryImageAdapter(this, drawables);

        gallery.setAdapter(galImageAdapter);

        if (drawables.size() > 0) {

            gallery.setSelection(selectedImagePosition, false);

        }

        if (drawables.size() == 1) {

            rightArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_right_disabled));
        }

    }

    private void changeBorderForSelectedImage(int selectedItemPos) {

        int count = gallery.getChildCount();

        for (int i = 0; i < count; i++) {

            ImageView imageView = (ImageView) gallery.getChildAt(i);
            imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.image_border));
            imageView.setPadding(3, 3, 3, 3);

        }

        ImageView imageView = (ImageView) gallery.getSelectedView();
        imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.selected_image_border));
        imageView.setPadding(3, 3, 3, 3);
    }

    private void getDrawablesList() {

        drawables = new ArrayList<Drawable>();
        drawables.add(getResources().getDrawable(R.drawable.natureimage1));
        drawables.add(getResources().getDrawable(R.drawable.natureimage2));
        drawables.add(getResources().getDrawable(R.drawable.natureimage3));
        drawables.add(getResources().getDrawable(R.drawable.natureimage4));
        drawables.add(getResources().getDrawable(R.drawable.natureimage5));
        drawables.add(getResources().getDrawable(R.drawable.natureimage6));
        drawables.add(getResources().getDrawable(R.drawable.natureimage7));
        drawables.add(getResources().getDrawable(R.drawable.natureimage8));
        drawables.add(getResources().getDrawable(R.drawable.natureimage9));
        drawables.add(getResources().getDrawable(R.drawable.natureimage10));
        drawables.add(getResources().getDrawable(R.drawable.natureimage11));
        drawables.add(getResources().getDrawable(R.drawable.natureimage12));
        drawables.add(getResources().getDrawable(R.drawable.natureimage13));
        drawables.add(getResources().getDrawable(R.drawable.natureimage14));
        drawables.add(getResources().getDrawable(R.drawable.natureimage15));

    }

    private void setSelectedImage(int selectedImagePosition) {

        BitmapDrawable bd = (BitmapDrawable) drawables.get(selectedImagePosition);
        Bitmap b = Bitmap.createScaledBitmap(bd.getBitmap(), (int) (bd.getIntrinsicHeight() * 0.9), (int) (bd.getIntrinsicWidth() * 0.7), false);
        selectedImageView.setImageBitmap(b);
        selectedImageView.setScaleType(ScaleType.FIT_XY);

    }
}