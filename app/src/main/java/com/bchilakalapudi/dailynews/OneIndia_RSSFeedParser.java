package com.bchilakalapudi.dailynews;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;


public class OneIndia_RSSFeedParser {
    static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String CHANNEL = "channel";
    static final String LANGUAGE = "language";
    static final String COPYRIGHT = "copyright";
    static final String LINK = "link";
    static final String AUTHOR = "author";
    static final String ITEM = "item";
    static final String PUBDATE = "pubDate";
    static final String GUID = "guid";
    static final String ENCLOSURE="enclosure";

    final URL url;

    public OneIndia_RSSFeedParser(String feedUrl) {
        try {
            this.url = new URL(feedUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<OneIndia_Feed> readFeed() {
        ArrayList<OneIndia_Feed> feedlist = new ArrayList<OneIndia_Feed>();
        try {
            boolean isFeedHeader = true;
            // Set header values intial to the empty string
            String description = "";
            String title = "";
            String link = "";
            String language = "";
            String copyright = "";
            String author = "";
            String guid = "";
            String enclosure_url="";
            String pubDate="";

            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = read();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName()
                            .getLocalPart();
                    switch (localPart) {
                        case ITEM:
                            if (isFeedHeader) {
                                isFeedHeader = false;
                            //    feed = new OneIndia_Feed(title, link, description, language,
                              //          copyright, pubdate);
                            }
                            event = eventReader.nextEvent();
                            break;
                        case TITLE:
                            title = getCharacterData(event, eventReader);
                            break;
                        case DESCRIPTION:
                            description = getCharacterData(event, eventReader);
                            break;
                        case LINK:
                            link = getCharacterData(event, eventReader);
                            break;
                        case GUID:
                            guid = getCharacterData(event, eventReader);
                            break;
                        case LANGUAGE:
                            language = getCharacterData(event, eventReader);
                            break;
                        case AUTHOR:
                            author = getCharacterData(event, eventReader);
                            break;
                        case PUBDATE:
                            pubDate = getCharacterData(event, eventReader);
                            break;
                        case ENCLOSURE:
                          //  enclosure = getCharacterData(event, eventReader);
                            // get attribute of thumbnail Tag
//get thumbnail ppicture link

                                Iterator<Attribute> attribue = event.asStartElement().getAttributes();
                                while(attribue.hasNext()){
                                    Attribute myAttribute = attribue.next();
                                    if(myAttribute.getName().toString().equals("url")){
                                        enclosure_url = myAttribute.getValue();
                                        break;
                                    }
                                }

                            break;
                        case COPYRIGHT:
                            copyright = getCharacterData(event, eventReader);
                            break;
                    }
                } else if (event.isEndElement()) {
                   if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                        OneIndia_Feed message = new OneIndia_Feed();
                       message.setTitle(title);
                       message.setLink(link);
                       message.setGuid(guid);
                       message.setDescription(description);
                       message.setEnclosure_url(enclosure_url);
                       message.setPubDate(pubDate);
                       feedlist.add(message);
                        event = eventReader.nextEvent();
                        continue;
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
       // System.out.println("feedlist:"+feedlist);
        return feedlist;
    }

    private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

    private InputStream read() {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}