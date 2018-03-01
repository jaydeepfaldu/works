package com.myutility;

import java.util.ArrayList;

import com.example.waterco.R;
import com.example.waterco.TimerActivity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class CustomAdaper extends BaseAdapter implements OnClickListener{

	
	 private Activity activity;
     private ArrayList data;
     private static LayoutInflater inflater=null;
     
     TimerModel tempValues=null;
     int i=0;
	
     public CustomAdaper(Activity a, ArrayList d) {
         
         /********** Take passed values **********/
          activity = a;
          data=d;
          
       
          /***********  Layout inflator to call external xml layout () ***********/
           inflater = ( LayoutInflater )activity.
                                       getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       
  }
	
	
	@Override
	public int getCount() {
		 if(data.size()<=0)
             return 1;
         return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	
	
	 public static class ViewHolder{
         
         public TextView tn;
         public TextView tt;
         public TextView td;         
         public CheckBox ta;
  
     }
	
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
        ViewHolder holder;
         
        if(convertView==null){
             
            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.timerlislayout, null);
             
            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.tn = (TextView) vi.findViewById(R.id.timername);
            holder.tt=(TextView)vi.findViewById(R.id.timertime);
            holder.td=(TextView)vi.findViewById(R.id.timerdays);
            holder.ta =(CheckBox)vi.findViewById(R.id.timercheck);
             
           /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
        }
        else 
            holder=(ViewHolder)vi.getTag();
         
        if(data.size()<=0)
        {
            holder.tn.setText("No Data");
             
        }
        else
        {
            /***** Get each Model object from Arraylist ********/
            tempValues=null;
            tempValues = ( TimerModel ) data.get( position );
             
            /************  Set Model values in Holder elements ***********/
             holder.tn.setTag(tempValues.getTimerId());
             holder.tn.setText( tempValues.getTimerName());
             holder.tt.setText( tempValues.getTimerTime());
             holder.td.setText( tempValues.getTimerDays());
             holder.ta.setChecked(tempValues.getTimerActive());
             
             /* holder.image.setImageResource(
                          res.getIdentifier(
                          "com.androidexample.customlistview:drawable/"+tempValues.getImage()
                          ,null,null)); */
              
             /******** Set Item Click Listner for LayoutInflater for each row *******/

             
             vi.setOnClickListener(new OnItemClickListener( position ));
             
             
        }
        return vi;
		
	}
	
	
	 @Override
     public void onClick(View v) {
             Log.v("CustomAdapter", "=====Row button clicked=====");
     }
	 
	  private class OnItemClickListener  implements OnClickListener{           
          private int mPosition;
           
          OnItemClickListener(int position){
               mPosition = position;
          }
           
          @Override
          public void onClick(View arg0) {

     
        	  TimerActivity sct = (TimerActivity)activity;

           /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/

              sct.onItemClick(mPosition);
          }               
      }   
	

}
