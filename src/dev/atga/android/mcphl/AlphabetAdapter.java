package dev.atga.android.mcphl;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/*
 * Needs as input an array of strings in alphabetical order.
 */

public class AlphabetAdapter extends ArrayAdapter<String> {
	
	Context context; 
    int layoutResourceId;    
    String[][] data = null;
	
	public AlphabetAdapter(Context context, int textViewResourceId,
			String[] input) {
		super(context, textViewResourceId, input);
		this.layoutResourceId = textViewResourceId;
        this.context = context;
        this.data = create2DimDataArray(input);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView header;
	    TextView txt;
		View row = convertView;
		//ViewHolder holder;
						
		//if(row == null) {
		
			//LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(layoutResourceId, parent, false);
		    //holder = new ViewHolder();
		    //holder.header = (TextView) row.findViewById(R.id.header);
		    header = (TextView) row.findViewById(R.id.header);
			//holder.txt = (TextView) row.findViewById(R.id.label);
			txt = (TextView) row.findViewById(R.id.label);
			//row.setTag(holder);
		//}
		//else {
		//	holder = (ViewHolder) row.getTag();
		//}
        
		//load the text from the 2dim array into position
		txt.setText(data[position][1]);
		
		if (data[position][0].equals("null")) {
			
			header.setText("");
			header.setBackgroundColor(0xFF000000); //wtf?!
		}
		
		else {
			Log.d("ATGA","HEADER");
			header.setText(data[position][0]);
			header.setBackgroundColor(0xFF00FFFF);
		}
		

		return row;
	}
	
	//buffer for efficiency
	//static class ViewHolder {
	//static TextView header;
    //static TextView txt;
	//}
	
    
    //create 2dim data array(1st column is header, 2nd column is data)
    private String[][] create2DimDataArray(String[] oneDimData) {
    	String temp[][] = new String[oneDimData.length][2];
    	
    	for(int position = 0; position < oneDimData.length; position++) {
    		if(position == 0) {
    			String current = oneDimData[position];
    			temp[0][0] = ((Character)current.charAt(0)).toString();
    			temp[0][1] = current;
    		}
    		else {
    			String current = oneDimData[position];
    			String prev = oneDimData[position-1];
    			if(current.charAt(0) != prev.charAt(0)) {
    				temp[position][0] = ((Character)current.charAt(0)).toString();
    				temp[position][1] = current;
    			}
    			else{
    				temp[position][0] = "null";
    				temp[position][1] = current;
    			}
    		}
    	}
    	return temp;
    }
}
