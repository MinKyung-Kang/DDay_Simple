package com.campandroid.dday;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	// 화면에 필요한 것들을 선언합니다.
	TextView txtDisplay = null;
	Button   btnCheck  = null;
	
	// 날짜설정하기
	int tYear, tMonth, tDay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		
		// 화면에 선언된 것들을 지정합니다.
		txtDisplay = (TextView)findViewById(R.id.txtPassed);
		btnCheck   = (Button)findViewById(R.id.btnCheck);
		
		// btnCheck이 눌렸을 때(클릭핸들러) 해야 할 일을 명령합니다.
		btnCheck.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 나는 여기서 ...
				// 1. 오늘 날짜를 설정한다.
				// 2. 날짜를 선택하는 화면을 띄울 것이다.
				
				// 재미삼이 이것을 주석처리해주시고 돌려보아주십시오.
				setCurrentDate();
				new DatePickerDialog(MainActivity.this, dateSetListener, tYear, tMonth, tDay).show();
				
			}
			
		} );
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	// calendar 화면을 띄워주는 클래스
	private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                  int dayOfMonth) {
            
        	// TODO Auto-generated method stub
            
        	// 2. 선택된 날짜의 DDay를 계산한다.
			int nPassed = HowLongPassed(year,monthOfYear, dayOfMonth);
        	
            // 3. 화면(txtDisplay)에 보여줘라.
			
			if(nPassed > 0){
				txtDisplay.setText("기념일로부터 " + nPassed + "일 남았습니다.");
					
			} else if(nPassed == 0){
				txtDisplay.setText("오늘이십니다 추카드립니다.");
				
			} else{
			    txtDisplay.setText("기념일이 " + ( -1 * nPassed ) + "일 지났습니다.");
				
			}
			
        }
    };
    
    
    // 오늘 날짜의 정보를 저장합니다. 
    // "안드로이드 날짜"로 구글링
    void setCurrentDate(){
    	Calendar calendar =Calendar.getInstance();              
        tYear = calendar.get(Calendar.YEAR);
        tMonth = calendar.get(Calendar.MONTH);
        tDay = calendar.get(Calendar.DAY_OF_MONTH);	
    }
    
    
    // 설정일과 오늘이 얼마나 차이가 나는가?
    // 연-year, 월-month, 일-day을 받아서 처리함.
    int HowLongPassed(int year, int month, int day){
    	long d;
    	long t;
    	long r;
    	
    	// 오늘날짜
    	Calendar calendar =Calendar.getInstance();              
        
    	// 설정일
    	Calendar dCalendar =Calendar.getInstance();
        dCalendar.set(year,month, day);
        
        // 초단위로 계산하고 - 한다.
        t=calendar.getTimeInMillis();            
        d=dCalendar.getTimeInMillis();           
        r=(d-t)/(24*60*60*1000);                 
         
    	return (int)r;
    }
	
}
