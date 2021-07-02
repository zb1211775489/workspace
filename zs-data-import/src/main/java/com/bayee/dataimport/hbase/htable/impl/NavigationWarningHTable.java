package com.bayee.dataimport.hbase.htable.impl;

import com.bayee.dataimport.hbase.htable.HTableInterface;
import java.io.UnsupportedEncodingException;

/**
 * @ClassName WaterStationHTable
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/3/29
 **/
public class NavigationWarningHTable implements HTableInterface {
    private String id;
    private String elasticsearch_id;
    private String title;
    private String pub_time;
    private String data_source;
    private String files;
    private String content;
    private String create_date;
    private String creator;
    private String update_date;
    private String updator;
    private String valid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getElasticsearch_id() {
        return elasticsearch_id;
    }

    public void setElasticsearch_id(String elasticsearch_id) {
        this.elasticsearch_id = elasticsearch_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPub_time() {
        return pub_time;
    }

    public void setPub_time(String pub_time) {
        this.pub_time = pub_time;
    }

    public String getData_source() {
        return data_source;
    }

    public void setData_source(String data_source) {
        this.data_source = data_source;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "NavigationWarningHTable{" +
                "id='" + id + '\'' +
                ", elasticsearch_id='" + elasticsearch_id + '\'' +
                ", title='" + title + '\'' +
                ", pub_time='" + pub_time + '\'' +
                ", data_source='" + data_source + '\'' +
                ", files='" + files + '\'' +
                ", content='" + content + '\'' +
                ", create_date='" + create_date + '\'' +
                ", creator='" + creator + '\'' +
                ", update_date='" + update_date + '\'' +
                ", updator='" + updator + '\'' +
                ", valid='" + valid + '\'' +
                '}';
    }

    @Override
    public byte[] rowKey() {
        byte[] rowKey = null;
        try {
            rowKey = id.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rowKey;
    }
}
