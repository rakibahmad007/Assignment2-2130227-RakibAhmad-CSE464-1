package com.example.a2130227_rakibahmad_assignment2_cse464_1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> arrayItems;
    private ArrayAdapter<String> adapterItems;
    private ListView item_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enable Edge-to-Edge UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        // Initialize ListView and Adapter
        item_list = findViewById(R.id.item_list);
        arrayItems = new ArrayList<>();
        adapterItems = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayItems);
        item_list.setAdapter(adapterItems);

        // Set up the ListView long-press listener for deletion
        setupListViewListener();
    }

    private void setupListViewListener() {
        item_list.setOnItemLongClickListener((adapterView, view, position, id) -> {
            arrayItems.remove(position);
            adapterItems.notifyDataSetChanged();
            return true;
        });
    }

    // Method to add item when the add button is clicked
    public void addItemButton(View view) {
        EditText editTextAddItem = findViewById(R.id.editTextAddItem);
        String itemText = editTextAddItem.getText().toString().trim();

        if (!itemText.isEmpty()) {
            arrayItems.add(itemText);
            adapterItems.notifyDataSetChanged();
            editTextAddItem.setText(""); // Clear the input after adding
        }
    }
}
