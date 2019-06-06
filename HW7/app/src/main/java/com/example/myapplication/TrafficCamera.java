package com.example.myapplication;

public class TrafficCamera {
        private String Id;
        private String description;
        private String imageUrl;
        private String type;
        private double latitude;
        private double longtiude;

    public TrafficCamera(String Id, String description, String imageUrl, String type, double latitude, double longtiude ) {
        this.imageUrl=imageUrl;
        this.Id = Id;
        this.description = description;
        this.type = type;
        this.latitude=latitude;
        this.longtiude =longtiude;
    }
    public String getId() {
        return Id;
    }

    public String getDescription() {
        return description;
    }
    public String getType() {
        return type;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longtiude; }
    public static class CameraBuilder {
        TrafficCamera newCamera;
        public CameraBuilder() {
            newCamera = new TrafficCamera("","","","",0.0,0.0);
        }
        public CameraBuilder description(String description) {
            newCamera.description = description;
            return this;
        }
        public CameraBuilder cameraImage(String imageUrl,String type) {
            if(type.equals("sdot")){
                newCamera.imageUrl = "http://www.seattle.gov/trafficcams/images/" + imageUrl;
            }else{
                newCamera.imageUrl = "http://images.wsdot.wa.gov/nw/" + imageUrl;
            }
            return this;
        }
        public CameraBuilder Latitude(double latitude) {
            newCamera.latitude = latitude;
            return this;

        }

        public CameraBuilder Longitude(double longitude) {
            newCamera.longtiude = longitude;
            return this;
        }

        public TrafficCamera buildCamera() {
            return newCamera;
        }
    }
}

