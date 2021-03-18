package com.mofei.excel.entity;

/**
 * @author mofei
 * @version 2020/11/24 17:10
 */
public class Device {
    private String sn ;
    private String csn ;
    private String group ;
    private String activity ;
    private String online ;

    @Override
    public String toString() {
        return "Device{" +
                "sn='" + sn + '\'' +
                ", csn='" + csn + '\'' +
                ", group='" + group + '\'' +
                ", activity='" + activity + '\'' +
                ", online='" + online + '\'' +
                '}';
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getCsn() {
        return csn;
    }

    public void setCsn(String csn) {
        this.csn = csn;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }
}
