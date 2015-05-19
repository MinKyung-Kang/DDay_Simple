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
	
	// ȭ�鿡 �ʿ��� �͵��� �����մϴ�.
	TextView txtDisplay = null;
	Button   btnCheck  = null;
	
	// ��¥�����ϱ�
	int tYear, tMonth, tDay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		
		// ȭ�鿡 ����� �͵��� �����մϴ�.
		txtDisplay = (TextView)findViewById(R.id.txtPassed);
		btnCheck   = (Button)findViewById(R.id.btnCheck);
		
		// btnCheck�� ������ ��(Ŭ���ڵ鷯) �ؾ� �� ���� ����մϴ�.
		btnCheck.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ���� ���⼭ ...
				// 1. ���� ��¥�� �����Ѵ�.
				// 2. ��¥�� �����ϴ� ȭ���� ��� ���̴�.
				
				// ��̻��� �̰��� �ּ�ó�����ֽð� ���������ֽʽÿ�.
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
	
	
	// calendar ȭ���� ����ִ� Ŭ����
	private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                  int dayOfMonth) {
            
        	// TODO Auto-generated method stub
            
        	// 2. ���õ� ��¥�� DDay�� ����Ѵ�.
			int nPassed = HowLongPassed(year,monthOfYear, dayOfMonth);
        	
            // 3. ȭ��(txtDisplay)�� �������.
			
			if(nPassed > 0){
				txtDisplay.setText("����Ϸκ��� " + nPassed + "�� ���ҽ��ϴ�.");
					
			} else if(nPassed == 0){
				txtDisplay.setText("�����̽ʴϴ� ��ī�帳�ϴ�.");
				
			} else{
			    txtDisplay.setText("������� " + ( -1 * nPassed ) + "�� �������ϴ�.");
				
			}
			
        }
    };
    
    
    // ���� ��¥�� ������ �����մϴ�. 
    // "�ȵ���̵� ��¥"�� ���۸�
    void setCurrentDate(){
    	Calendar calendar =Calendar.getInstance();              
        tYear = calendar.get(Calendar.YEAR);
        tMonth = calendar.get(Calendar.MONTH);
        tDay = calendar.get(Calendar.DAY_OF_MONTH);	
    }
    
    
    // �����ϰ� ������ �󸶳� ���̰� ���°�?
    // ��-year, ��-month, ��-day�� �޾Ƽ� ó����.
    int HowLongPassed(int year, int month, int day){
    	long d;
    	long t;
    	long r;
    	
    	// ���ó�¥
    	Calendar calendar =Calendar.getInstance();              
        
    	// ������
    	Calendar dCalendar =Calendar.getInstance();
        dCalendar.set(year,month, day);
        
        // �ʴ����� ����ϰ� - �Ѵ�.
        t=calendar.getTimeInMillis();            
        d=dCalendar.getTimeInMillis();           
        r=(d-t)/(24*60*60*1000);                 
         
    	return (int)r;
    }
	
}
