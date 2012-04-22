/**
 * 
 */
package com.andes.hack;

import com.andes.hack.network.ServiceHelper;
import com.socialize.Socialize;
import com.socialize.SocializeUI;
import com.socialize.entity.Entity;
import com.socialize.entity.Like;
import com.socialize.entity.Share;
import com.socialize.ui.actionbar.ActionBarListener;
import com.socialize.ui.actionbar.ActionBarView;
import com.socialize.ui.actionbar.OnActionBarEventListener;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @author 	Sergio Bernales
 * @date 	Apr 21, 2012
 *
 * Copyright 2012 Locomoti LLC. All rights reserved.
 *
 */
public class ModelInfoActivity extends Activity implements ActionBarListener, OnActionBarEventListener {

	
	int modelID = 1;
	
	ServiceHelper mRestServiceHelper;
	final String baseEntityKey = "http://www.quickvote.com/";
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		
		String id = getIntent().getExtras().getString("voteID");
		
		String entityString = baseEntityKey+id;
		
		// Create an entity object including a name
        // The Entity object is Serializable, so you could also store the whole object in the Intent
        Entity entity = Entity.newInstance(entityString, "Socialize");

        // Wrap your existing view with the action bar.
        // your_layout refers to the resource ID of your current layout.
        View actionBarWrapped = Socialize.getSocializeUI().showActionBar(this, R.layout.layout_model_info, entity, this);

        // Now set the view for your activity to be the wrapped view.
        setContentView(actionBarWrapped);
        
        mRestServiceHelper = new ServiceHelper();
        
        View v = findViewById(R.id.outer);
        
        v.setBackgroundDrawable(R.)
        
        
	}

	/* (non-Javadoc)
	 * @see com.socialize.ui.actionbar.ActionBarListener#onCreate(com.socialize.ui.actionbar.ActionBarView)
	 */
	@Override
	public void onCreate(ActionBarView actionBar) {

		actionBar.setOnActionBarEventListener(this);
		
	}
	
	/* (non-Javadoc)
	 * @see com.socialize.ui.actionbar.OnActionBarEventListener#onPostLike(com.socialize.ui.actionbar.ActionBarView, com.socialize.entity.Like)
	 */
	@Override
	public void onPostLike(ActionBarView arg0, Like arg1) {
		
		ContentValues values = new ContentValues(1);
		
		values.put(Constants.VALUE_PHONE_NUMBER, Constants.modelNumbers[this.modelID]);
		
		mRestServiceHelper.sendSMS(this, values);
	}

	/* (non-Javadoc)
	 * @see com.socialize.ui.actionbar.OnActionBarEventListener#onClick(com.socialize.ui.actionbar.ActionBarView, com.socialize.ui.actionbar.OnActionBarEventListener.ActionBarEvent)
	 */
	@Override
	public boolean onClick(ActionBarView arg0, ActionBarEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.socialize.ui.actionbar.OnActionBarEventListener#onGetEntity(com.socialize.ui.actionbar.ActionBarView, com.socialize.entity.Entity)
	 */
	@Override
	public void onGetEntity(ActionBarView arg0, Entity arg1) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.socialize.ui.actionbar.OnActionBarEventListener#onGetLike(com.socialize.ui.actionbar.ActionBarView, com.socialize.entity.Like)
	 */
	@Override
	public void onGetLike(ActionBarView arg0, Like arg1) {
		
		
		
	}

	/* (non-Javadoc)
	 * @see com.socialize.ui.actionbar.OnActionBarEventListener#onLoad(com.socialize.ui.actionbar.ActionBarView)
	 */
	@Override
	public void onLoad(ActionBarView arg0) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see com.socialize.ui.actionbar.OnActionBarEventListener#onPostShare(com.socialize.ui.actionbar.ActionBarView, com.socialize.entity.Share)
	 */
	@Override
	public void onPostShare(ActionBarView arg0, Share arg1) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.socialize.ui.actionbar.OnActionBarEventListener#onPostUnlike(com.socialize.ui.actionbar.ActionBarView)
	 */
	@Override
	public void onPostUnlike(ActionBarView arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.socialize.ui.actionbar.OnActionBarEventListener#onUpdate(com.socialize.ui.actionbar.ActionBarView)
	 */
	@Override
	public void onUpdate(ActionBarView arg0) {
		
		ContentValues values = new ContentValues(1);
		
		values.put(Constants.VALUE_PHONE_NUMBER, Constants.modelNumbers[this.modelID]);
		
		mRestServiceHelper.sendSMSComment(this, values);
		
	}
}
