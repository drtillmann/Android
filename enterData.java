package org.example.events;

import static org.example.events.Constants.TABLE_NAME;
import static org.example.events.Constants.COURSE;
import static org.example.events.Constants.PUTTS;
import static org.example.events.Constants.PENNALTIES;
import static org.example.events.Constants.SCORE;
import static org.example.events.Constants.PLAYER;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class enterData extends Activity implements OnClickListener {
	
	private EditText course;
	private EditText putts;
	private EditText pennalties;
	private EditText score;
	private EditText player;
	
	private EventsData events;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enter_data);
		
		events = new EventsData(this);
		
		View enter = findViewById(R.id.enter_data_form);
		enter.setOnClickListener(this);
		View exit = findViewById(R.id.exit_button_form);
		exit.setOnClickListener(this);
	}
	
	
	
	public void onClick(View v) {
		
		course = (EditText)findViewById(R.id.course);
		putts = (EditText)findViewById(R.id.putts);
		pennalties = (EditText)findViewById(R.id.pennalties);
		score = (EditText)findViewById(R.id.score);
		player = (EditText)findViewById(R.id.player);
		

		switch (v.getId()){
		case R.id.enter_data_form:
			
		if(MainActivity.mp == null){
				if(this.isFinishing()){
				MainActivity.mp.stop();
				}
			}
		if(MainActivity.mp != null){
			if(this.isFinishing()){
			MainActivity.mp.stop();
			}
		}
		
		if(course.getText().toString().isEmpty() || putts.getText().toString().isEmpty() || 
				pennalties.getText().toString().isEmpty() || score.getText().toString().isEmpty() ||
				player.getText().toString().isEmpty()){
			
			Context context = getApplicationContext();
			CharSequence text = "All Fields Must Be Filled.";
			int duration = 5000;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			
		}
		
		if(course.getText().toString().isEmpty() == false || putts.getText().toString().isEmpty() == false || 
				pennalties.getText().toString().isEmpty() == false|| score.getText().toString().isEmpty() == false ||
				player.getText().toString().isEmpty() == false){
			try{
			
				addEvent(course.getText().toString(), putts.getText().toString(),pennalties.getText().toString(),
						score.getText().toString(), player.getText().toString() );
			
			}finally{
				events.close();
			}
			
			Context context = getApplicationContext();
			CharSequence text = "Information Saved!";
			int duration = 5000;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
		events.close();
			
			
			break;
		case R.id.exit_button_form:
		
			if(MainActivity.mp == null){
				if(this.isFinishing()){
				MainActivity.mp.stop();
				}
			}
			if(MainActivity.mp != null){
				if(this.isFinishing()){
				MainActivity.mp.stop();
				}
			}
			finish();
			break;
		}
		
	}
	
	private void addEvent(String c, String pu, String pe, String s, String player){
    	SQLiteDatabase db = events.getWritableDatabase();
    	ContentValues values = new ContentValues();
    	values.put(COURSE, c);
    	values.put(PUTTS, pu);
    	values.put(PENNALTIES, pe);
    	values.put(SCORE, s);
    	values.put(PLAYER, player);
    	db.insertOrThrow(TABLE_NAME, null, values);
    }
}
