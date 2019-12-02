package com.mbp.sudoku.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mbp.sudoku.MainActivity;
import com.mbp.sudoku.R;
import com.mbp.sudoku.util.DataBaseHelper;
import com.mbp.sudoku.util.MapUtil;

public class GameFailureActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.failure_layout);
        Intent intent = getIntent();
        //关卡编号
        int level = intent.getIntExtra("level",1);

        //再次挑战按钮
        Button button_challenge_again = findViewById(R.id.challenge_again);
        button_challenge_again.setOnClickListener(view -> {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(GameFailureActivity.this,"ShuDu.db",null,1);
            SQLiteDatabase database = dataBaseHelper.getWritableDatabase();
            database.delete("tb_game_speed","level = ?",new String[]{String.valueOf(level)});
            Intent intent1 = new Intent(GameFailureActivity.this, GameActivity.class);
            intent1.putExtra("level",level);
            intent1.putExtra("gameType","new");
            startActivity(intent1);
        });

        //返回主界面按钮
        Button button_return_main = findViewById(R.id.return_main);
        button_return_main.setOnClickListener(view -> {
            Intent intent2 = new Intent(GameFailureActivity.this, MainActivity.class);
            startActivity(intent2);
        });



    }
}
