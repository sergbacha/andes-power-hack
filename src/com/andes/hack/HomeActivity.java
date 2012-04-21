package com.andes.hack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class HomeActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent i = new Intent(this, ModelInfoActivity.class);
        i.putExtra("voteID", "1");
        
    	startActivity(i );
    }
}
