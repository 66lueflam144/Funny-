package com.example.crazylightning;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Witch extends AppCompatActivity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_i_can_see);

        findViewById(R.id.btn_normal_dialog).setOnClickListener(v -> showNormalDialog());
        findViewById(R.id.btn_single_choice_dialog).setOnClickListener(v -> showSingleChoiceDialog());
        findViewById(R.id.btn_multi_choice_dialog).setOnClickListener(v -> showMultiChoiceDialog());
        findViewById(R.id.btn_semi_custom_dialog).setOnClickListener(v -> showSemiCustomDialog());
        findViewById(R.id.btn_custom_dialog).setOnClickListener(v -> showCustomDialog());

        Button btnCircularProgressDialog = findViewById(R.id.btn_circular_progress_dialog);
        Button btnHorizontalProgressDialog = findViewById(R.id.btn_horizontal_progress_dialog);
        Button btnBottomSheetDialog = findViewById(R.id.btn_bottom_sheet_dialog);


        // 圆形进度条对话框
        btnCircularProgressDialog.setOnClickListener(v -> showCircularProgressDialog());

        // 水平进度条对话框
        btnHorizontalProgressDialog.setOnClickListener(v -> showHorizontalProgressDialog());

        // Bottom Sheet Dialog
        btnBottomSheetDialog.setOnClickListener(v -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Witch.this);
            View bottomSheetView = getLayoutInflater().inflate(R.layout.show_you_real_bottom, null);

            // 在 bottomSheetView 中找到 APT 按钮
            Button APT = bottomSheetView.findViewById(R.id.apt);
            APT.setOnClickListener(vv -> {
                bottomSheetDialog.dismiss();  // 点击按钮时关闭对话框
            });

            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.setCancelable(true);
            bottomSheetDialog.show();
        });

    }

    // 圆形进度条对话框
    private void showCircularProgressDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_circular, null);

        builder.setView(dialogView);
        builder.setCancelable(true);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // 水平进度条对话框
    private void showHorizontalProgressDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_horizontal, null);

        ProgressBar progressBar = dialogView.findViewById(R.id.progress_horizontal);

        builder.setView(dialogView);
        builder.setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.show();

        new Thread(() -> {
            for (int progress = 0; progress <= 100; progress++) {
                try {
                    Thread.sleep(50);
                    progressBar.setProgress(progress);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            dialog.dismiss();
        }).start();
    }

    // 普通对话框
    private void showNormalDialog() {
        new AlertDialog.Builder(this)
                .setTitle("普通对话框")
                .setMessage("这是一个普通对话框")
                .setPositiveButton("YES", (dialog, which) -> Toast.makeText(Witch.this, "KEEP MOVING...", Toast.LENGTH_SHORT).show())
                .setNegativeButton("NO", (dialog, which) -> dialog.dismiss())
                .show();
    }

    // 单选对话框
    private void showSingleChoiceDialog() {
        final String[] items = {"Witch", "Impurities", "Scratches"};
        new AlertDialog.Builder(this)
                .setTitle("单选对话框")
                .setSingleChoiceItems(items, -1, (dialog, which) -> Toast.makeText(Witch.this, "You are: " + items[which], Toast.LENGTH_SHORT).show())
                .setPositiveButton("YES", (dialog, which) -> dialog.dismiss())
                .show();
    }

    // 多选对话框
    private void showMultiChoiceDialog() {
        final String[] items = {"SHOW", "YOU", "REAL-ME"};
        final boolean[] checkedItems = {false, false, false};

        new AlertDialog.Builder(this)
                .setTitle("多选对话框")
                .setMultiChoiceItems(items, checkedItems, (dialog, which, isChecked) -> checkedItems[which] = isChecked)
                .setPositiveButton("YES", (dialog, which) -> {
                    StringBuilder result = new StringBuilder();
                    for (int i = 0; i < checkedItems.length; i++) {
                        if (checkedItems[i]) {
                            result.append(items[i]).append(" ");
                        }
                    }
                    Toast.makeText(Witch.this, "YOUR CHOICE: " + result, Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("NO!!!", (dialog, which) -> dialog.dismiss())
                .show();
    }

    // 半自定义对话框
    private void showSemiCustomDialog() {
        new AlertDialog.Builder(this)
                .setTitle("半自定义对话框")
                .setMessage("IMPURITIES, is it magic or tragic?")
                .setPositiveButton("0x60064", (dialog, which) -> Toast.makeText(Witch.this, "track tick", Toast.LENGTH_SHORT).show())
                .setNegativeButton("Down to EARTH", (dialog, which) -> dialog.dismiss())
                .show();
    }

    // 自定义对话框
    private void showCustomDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_show_can_you_see, null);

        new AlertDialog.Builder(this)
                .setView(dialogView)
                .setPositiveButton("SUREly NOT", (dialog, which) -> Toast.makeText(Witch.this, "ONE drop into heart", Toast.LENGTH_SHORT).show())
                .setNegativeButton("CERTAINly yes", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
