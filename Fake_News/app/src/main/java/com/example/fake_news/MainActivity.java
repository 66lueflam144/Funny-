package com.example.fake_news;

import android.os.Bundle;

import com.example.fake_news.dateHandler.DataHandler;
import com.example.fake_news.ui.Real_ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.fake_news.databinding.ActivityMainBinding;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private EditText urlInput;
    private Real_ViewModel realViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.maintest);
        urlInput = findViewById(R.id.url_input);
        Button fetchButton = findViewById(R.id.parse_button);

        TextView titleShow = findViewById(R.id.title_show);
        TextView authorTextView = findViewById(R.id.author);
        TextView linkTextView = findViewById(R.id.link);
        TextView pubDateTextView = findViewById(R.id.pubDate);
        TextView descriptionView = findViewById(R.id.description_show);



        realViewModel = new Real_ViewModel(titleShow, authorTextView, linkTextView, pubDateTextView, descriptionView);


        DataHandler dataHandler = null;
        try {
            dataHandler = new DataHandler(realViewModel);
        } catch (XmlPullParserException e) {
            throw new RuntimeException(e);
        }

        DataHandler finalDataHandler = dataHandler;
        fetchButton.setOnClickListener(view -> {
            String url = urlInput.getText().toString();
            finalDataHandler.fetchAndHandleData(url);
        });


//
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        setSupportActionBar(binding.toolbar);
//
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//
//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAnchorView(R.id.fab)
//                        .setAction("Action", null).show();
//            }
//        });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
    }
}