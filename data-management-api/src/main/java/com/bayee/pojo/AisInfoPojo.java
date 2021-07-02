package com.bayee.pojo;

/**
 * @ClassName AisInfoPojo
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/3/15
 **/
public class AisInfoPojo {
    private String callsign;
    private String color;
    private double courseOverGround;
    private int delayState;
    private String destination;
    private String eta;
    private int headDirector;
    private int iState;
    private int imo;
    private double lat;
    private int length;
    private double lon;
    private long mmsi;
    private String name;
    private String navigationStatus;
    private boolean netMark;
    private int source;
    private double speedOverGround;
    private long time;
    private int trueHeadOverGround;
    private String type;
    private int typeValue;
    private long updateDate;
    private int width;

    @Override
    public String toString() {
        return "AisInfoPojo{" +
                "callsign='" + callsign + '\'' +
                ", color='" + color + '\'' +
                ", courseOverGround=" + courseOverGround +
                ", delayState=" + delayState +
                ", destination='" + destination + '\'' +
                ", eta='" + eta + '\'' +
                ", headDirector=" + headDirector +
                ", iState=" + iState +
                ", imo=" + imo +
                ", lat=" + lat +
                ", length=" + length +
                ", lon=" + lon +
                ", mmsi=" + mmsi +
                ", name='" + name + '\'' +
                ", navigationStatus='" + navigationStatus + '\'' +
                ", netMark=" + netMark +
                ", source=" + source +
                ", speedOverGround=" + speedOverGround +
                ", time=" + time +
                ", trueHeadOverGround=" + trueHeadOverGround +
                ", type='" + type + '\'' +
                ", typeValue=" + typeValue +
                ", updateDate=" + updateDate +
                ", width=" + width +
                '}';
    }

    public String getCallsign() {
        return callsign;
    }

    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getCourseOverGround() {
        return courseOverGround;
    }

    public void setCourseOverGround(double courseOverGround) {
        this.courseOverGround = courseOverGround;
    }

    public int getDelayState() {
        return delayState;
    }

    public void setDelayState(int delayState) {
        this.delayState = delayState;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public int getHeadDirector() {
        return headDirector;
    }

    public void setHeadDirector(int headDirector) {
        this.headDirector = headDirector;
    }

    public int getiState() {
        return iState;
    }

    public void setiState(int iState) {
        this.iState = iState;
    }

    public int getImo() {
        return imo;
    }

    public void setImo(int imo) {
        this.imo = imo;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public long getMmsi() {
        return mmsi;
    }

    public void setMmsi(long mmsi) {
        this.mmsi = mmsi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNavigationStatus() {
        return navigationStatus;
    }

    public void setNavigationStatus(String navigationStatus) {
        this.navigationStatus = navigationStatus;
    }

    public boolean isNetMark() {
        return netMark;
    }

    public void setNetMark(boolean netMark) {
        this.netMark = netMark;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public double getSpeedOverGround() {
        return speedOverGround;
    }

    public void setSpeedOverGround(double speedOverGround) {
        this.speedOverGround = speedOverGround;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getTrueHeadOverGround() {
        return trueHeadOverGround;
    }

    public void setTrueHeadOverGround(int trueHeadOverGround) {
        this.trueHeadOverGround = trueHeadOverGround;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(int typeValue) {
        this.typeValue = typeValue;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
