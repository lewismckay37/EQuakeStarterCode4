package org.me.gcu.equakestartercode;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.me.gcu.equakestartercode.R;







public class MainActivity extends AppCompatActivity implements OnClickListener, nav_menu {
    private ArrayList<String> temp;
    private TextView rawDataDisplay;
    private Button startButton;
    private String result = "";
    private String url1="";
    private String urlSource="http://quakes.bgs.ac.uk/feeds/MhSeismology.xml";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MyTag","in onCreate");
        // Set up the raw links to the graphical components
        rawDataDisplay = (TextView)findViewById(R.id.rawDataDisplay);
        startButton = (Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(this);
        Log.e("MyTag","after startButton");



    }

    public void onClick(View aview)
    {
        Log.e("MyTag","in onClick");
        startProgress();
        Log.e("MyTag","after startProgress");
    }

    public void startProgress()
    {
        // Run network access on a separate thread;
        new Thread(new Task(urlSource)).start();
    } //



    private class Task implements Runnable
    {
        private String url;

        public Task(String aurl)
        {
            url = aurl;
        }
        @Override
        public void run()
        {

            URL aurl;
            URLConnection yc;
            BufferedReader in = null;
            String inputLine = "";


            Log.e("MyTag","in run");

            try
            {
                Log.e("MyTag","in try");
                aurl = new URL(url);
                yc = aurl.openConnection();
                in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                Log.e("MyTag","after ready");
                //
                // Now read the data. Make sure that there are no specific hedrs
                // in the data file that you need to ignore.
                // The useful data that you need is in each of the item entries
                //
                while ((inputLine = in.readLine()) != null)
                {
                    result = result + inputLine;
                    //Log.e("MyTag",inputLine);

                }
                in.close();
            }
            catch (IOException ae)
            {
                Log.e("MyTag", "ioexception in run");
            }

            //
            // Now that you have the xml data you can parse it
            //
            LinkedList <EarthquakeClass> alist = null;
            alist = parseData(result);

            // Write list to Log for testing
            if (alist != null)
            {
                Log.e("MyTag","List not null");
                for (Object o : alist)
                {
                    Log.e("MyTag",o.toString());
                }
            }
            else
            {
                Log.e("MyTag","List is null");
            }
            // Now update the TextView to display raw XML data
            // Probably not the best way to update TextView
            // but we are just getting started !

            MainActivity.this.runOnUiThread(new Runnable()
            {
                public void run() {
                    Log.d("UI thread", "I am the UI thread");
                    //rawDataDisplay.setText(result);
                }
            });
        }

    }


    private LinkedList<EarthquakeClass> parseData(String dataToParse)
    {
        EarthquakeClass earthquake = null;
        LinkedList <EarthquakeClass> alist = null;
        try
        {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput( new StringReader( dataToParse ) );
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                // Found a start tag
                if(eventType == XmlPullParser.START_TAG)
                {
                    // Check which Tag we have
                    if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        alist  = new LinkedList<EarthquakeClass>();
                    }
                    else
                    if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        Log.e("MyTag","Item Start Tag found");
                        earthquake = new EarthquakeClass();
                    }
                    else
                    if (xpp.getName().equalsIgnoreCase("description"))
                    {
                        // Now just get the associated text
                        String temp = xpp.nextText();

                        String[] arrOfStr = temp.split(";");

                                for (String a: arrOfStr) {
                                    String[] tmpStr = a.split(":");
                                    switch (tmpStr[0].trim()) {
                                        case "Location":
                                            String loc = tmpStr[1].trim();
                                            earthquake.setLocation(loc);
                                            Log.e("MyTag","Location is " + loc);
                                    break;
                                        case "Depth":
                                           String[] depthStr = tmpStr[1].trim().split(" ");
                                            String depth = depthStr[0].trim();
                                           float d = Float.parseFloat(depth);
                                            earthquake.setDepth(d);
                                            Log.e("MyTag", "Depth is " + d);
                                   break;
                                        case "Magnitude":
                                            String mag = tmpStr[1].trim();
                                            float m = Float.parseFloat(mag);
                                            earthquake.setMagnitude(m);
                                            Log.e("MyTag", "Magnitude is " + m);
                                            break;







                                    }

                                }


                        //earthquake.setBolt(temp);
                    }
                    else
                    if (xpp.getName().equalsIgnoreCase("pubDate"))
                    {
                        // Now just get the associated text
                        String temp = xpp.nextText();
                        // Do something with text
                        Log.e("MyTag","Origin date is  " + temp);
                        earthquake.setOriginDateTime(temp);
                    }
                    else
                        // Check which Tag we have
                    if (xpp.getName().equalsIgnoreCase("lat"))
                    {
                            // Now just get the associated text
                            float temp = Float.parseFloat(xpp.nextText());
                            // Do something with text
                            Log.e("MyTag","Latitude is " + temp);
                            earthquake.setLatitude(temp);
                    }
                    else
                            // Check which Tag we have
                        if (xpp.getName().equalsIgnoreCase("long"))
                        {
                            // Now just get the associated text
                            float temp = Float.parseFloat(xpp.nextText());
                            // Do something with text
                            Log.e("MyTag","Longitude is " + temp);
                            earthquake.setLongitude(temp);
                        }
                }
                else
                if(eventType == XmlPullParser.END_TAG)
                {
                    if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        Log.e("MyTag","widget is " + earthquake.toString());
                        alist.add(earthquake);
                    }
                    else
                    if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        int size;
                        size = alist.size();
                        Log.e("MyTag","widgetcollection size is " + size);
                    }
                }


                // Get the next event
                eventType = xpp.next();

            } // End of while

            //return alist;
        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag","IO error during parsing");
        }

        Log.e("MyTag","End document");

        return alist;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
       }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_home:
                Intent myintent3 = new Intent(MainActivity.this,  MainActivity.class);
                startActivity(myintent3);
                return false;
            case R.id.action_map:
                Intent myintent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(myintent);
                return false;
            case R.id.action_List:

                Intent myintent1 = new Intent(MainActivity.this, ListActivity.class);
                startActivity(myintent1);
                return false;
            case R.id.action_Search:
                Intent myintent2 = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(myintent2);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
