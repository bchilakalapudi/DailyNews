package com.bchilakalapudi.dailynews;

import java.io.Serializable;

public class OneIndia_Feed implements Serializable {
    public String title;
    public String link;
    public String guid;
    public String description;
    public String enclosure_url;
    public String pubDate;

    public OneIndia_Feed(){}
    public OneIndia_Feed(String title, String link, String guid, String description, String enclosure, String pubDate) {
        this.title = title;
        this.link = link;
        this.guid = guid;
        this.description = description;
        this.enclosure_url = enclosure;
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "OneIndia_Feed{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", guid='" + guid + '\'' +
                ", description='" + description + '\'' +
                ", enclosure='" + enclosure_url + '\'' +
                ", pubDate='" + pubDate + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnclosure_url() {
        return enclosure_url;
    }

    public void setEnclosure_url(String enclosure_url) {
        this.enclosure_url = enclosure_url;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}
