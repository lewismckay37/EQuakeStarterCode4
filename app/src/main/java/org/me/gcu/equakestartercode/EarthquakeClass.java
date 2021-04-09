package org.me.gcu.equakestartercode;

public class EarthquakeClass {



    private float magnitude;
    private float depth;
    private String originDateTime;
    private String location;
    private float latitude;
    private float longitude;

    private String nut;

    public EarthquakeClass()
    {

        magnitude = 0;
        depth = 0;
        originDateTime = "";
        location = "";
        latitude = 0;
        longitude = 0;
    }

    public EarthquakeClass( float aMagnitude,float aDepth,String aOriginDateTime,String aLocation, float aLatitude, float aLongitude)
    {
        magnitude = aMagnitude;
        depth = aDepth;
        originDateTime= aOriginDateTime;
        location = aLocation;
        latitude = aLatitude;
        longitude = aLongitude;
    }

    public float getMagnitude()
    {
        return magnitude;
    }

    public void setMagnitude(float aMagnitude) { magnitude = aMagnitude;}

    public float getDepth()
    {
        return depth;
    }

    public void setDepth(float aDepth) { depth = aDepth;}

    public String getOriginDateTime()
    {
        return originDateTime;
    }

    public void setOriginDateTime(String aOriginDateTime) {originDateTime = aOriginDateTime;}

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String aLocation) {location = aLocation;}

    public float getLatitude()
    {
        return latitude;
    }

    public void setLatitude(float aLatitude) { latitude = aLatitude;}

    public float getLongitude()
    {
        return longitude;
    }

    public void setLongitude(float aLongitude) { longitude = aLongitude;}

    public String toString()
    {
        String temp;

        temp = location + " " + magnitude + " " + originDateTime + " " + depth + " " + latitude + " " + longitude;

        return temp;
    }

} // End of class
