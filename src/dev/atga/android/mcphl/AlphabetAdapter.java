package dev.atga.android.mcphl;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
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
    private LayoutInflater inflater;
    int width;
    	
	public AlphabetAdapter(Context context, int textViewResourceId,
			String[] input, int width) {
		super(context, textViewResourceId, input);
		this.layoutResourceId = textViewResourceId;
        this.context = context;
        this.data = create2DimDataArray(input);
        this.width = width;
        
        inflater = LayoutInflater.from(context);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		Log.d("ATGA", "position: " + position);
		if(convertView == null) {
			Log.d("ATGA", "convertView == null : true");
			//LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			//LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(layoutResourceId, parent, false);
		    holder = new ViewHolder();
		    holder.header = (TextView) convertView.findViewById(R.id.header);
			holder.txt = (TextView) convertView.findViewById(R.id.label);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
        
		//load the text from the 2dim array into position
		holder.txt.setText(data[position][1]);
		//load the header if necessary
		if (data[position][0].equals("null")) {
			holder.header.setText("");
			holder.header.setBackgroundColor(0xFF000000); //wtf?!
			holder.header.setHeight(((int) (width*0.2)));
			holder.header.setWidth(((int) (width*0.2)));
		} else {
			holder.header.setText(data[position][0]);
			//holder.header.setBackgroundColor(0xFF00FFFF);
			holder.header.setBackgroundColor(0xFFff0000);
			holder.header.setHeight(((int) (width*0.2)));
			holder.header.setWidth(((int) (width*0.2)));
		}

		return convertView;
	}
	
	static class ViewHolder {
		TextView header;
		TextView txt;
	}
	
    
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
