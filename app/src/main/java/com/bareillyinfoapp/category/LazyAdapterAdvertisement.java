package com.bareillyinfoapp.category;
import java.util.ArrayList;

import com.bareillyinfoapp.category.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class LazyAdapterAdvertisement extends BaseAdapter 
{
	 private Activity activity;
	    private ArrayList<Advertisement> data = null;
	    private static LayoutInflater inflater=null;
	    public ImageLoader imageLoader; 
	 
	    public LazyAdapterAdvertisement(Activity a, ArrayList<Advertisement> d) 
	    {
	        activity = a;	        
	        if(data !=null)
	        {
		        data.clear();
		    }	        
	        data=d;	        
	        notifyDataSetChanged();        
	        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        imageLoader=new ImageLoader(activity.getApplicationContext());	       
	    }
	 
	    
	    public int getCount() 
	    {
	        return data.size();
	    }
	 	  
	    
	    public Object getItem(int position) 
	    {
	        return position;
	    }
	 
	    
	    public long getItemId(int position) 
	    {
	        return position;
	    }
	 
	    
	    public View getView(int position, View convertView, ViewGroup parent) 
	    {
	        View vi=convertView;	       
	        notifyDataSetChanged();	        
	        if(convertView==null)
	        
	       // vi = inflater.inflate(R.layout.list_view_row_item, null);
	        vi = inflater.inflate(R.layout.advertise_list_view, null);	        
	        TextView advertisement_name  = (TextView)vi.findViewById(R.id.ad_name);	      
	        TextView description = (TextView)vi.findViewById(R.id.ad_dec); 
	        TextView mobile_no = (TextView)vi.findViewById(R.id.mobile); 
	        TextView email = (TextView)vi.findViewById(R.id.email); 	        
	        TextView address = (TextView)vi.findViewById(R.id.address);
	        ImageView thumb_image=(ImageView)vi.findViewById(R.id.imageView1); 	 
	        
	        Advertisement p = data.get(position);	        
     
	        
	        //for alternate color on list view
	        if(position % 2 == 0)
	        {
	                vi.setBackgroundColor(Color.parseColor("#FFFFFF"));
	                //vi.setBackgroundResource(R.drawable.list_selector);	                
	        }
	        else
	        {
	                vi.setBackgroundColor(Color.parseColor("#EFEFEF"));
	               // vi.setBackgroundResource(R.drawable.list_selector_alternate);		                
	        }
        
	        
	        
	     //   String bhk_options_str = p.mStakeholderId.replace("0,","").replace(", 0", "") + " BHK";
	        advertisement_name.setText(p.madvertiseName);
	        description.setText(p.mDescription);
	         mobile_no.setText(p.mMobileNo);
	         mobile_no.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View v) {
		            	TextView temp = (TextView)v;
		            	Intent intent = new Intent(Intent.ACTION_DIAL);
		            	intent.setData(Uri.parse("tel:" + temp.getText()));
		            	v.getContext().startActivity(intent);
		            }
		        });
	         email.setText(p.mEmail);
	        address.setText(p.mAddress);    
	       
	        //imageLoader.DisplayImage("http://192.168.1.2/www.yellowsheet.com/images/" + p.mphoto, thumb_image);
	        imageLoader.DisplayImage("http://www.smartcityinfo.in/adminbly/images/" + p.mphoto, thumb_image);
	        return vi;
	    }
}