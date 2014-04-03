package rss;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

public class RSSFeedParser {
  private static final String FEED = "WIEWARM";
  private static final String FEED_ITEM = "BAD";

  private final URL url;

  public RSSFeedParser(String feedUrl) {
    try {
      this.url = new URL(feedUrl);
    }
    catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  public RSSFeedParser(URL url) {
    this.url = url;
  }

  public Feed readFeed() {
    Feed feed = null;
    boolean isFeedHeader = true;

    String feedName = "WIEWARM";
    String createdAt = "";
    
    String name = "";
    String ort = "";

    try {
      // First create a new XMLInputFactory
      XMLInputFactory inputFactory = XMLInputFactory.newInstance();

      // Setup new event reader
      InputStream in = initInputStream();
      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
      
      //read the XML document
      while(eventReader.hasNext()){
        XMLEvent event = eventReader.nextEvent();
        
        if (event.isStartElement()) {
          String localPart = event.asStartElement().getName().getLocalPart();
          
          if(FEED_ITEM.equals(localPart)) {
            if (isFeedHeader) {
              isFeedHeader = false;
              feed = new Feed(feedName, createdAt);
            }
            name = event.asStartElement().getAttributeByName(new QName("name")).getValue();
            ort = event.asStartElement().getAttributeByName(new QName("ort")).getValue();
          }
          else if(FEED.equals(localPart)){
            createdAt = event.asStartElement().getAttributeByName(new QName("createdAt")).getValue();
          }
        }
        else if (event.isEndElement()){
          if(event.asEndElement().getName().getLocalPart().equals(FEED_ITEM)){
            FeedItem feedItem = new FeedItem();
            feedItem.setName(name);
            feedItem.setOrt(ort);
            
            feed.getEntries().add(feedItem);
          }
        }
      }
    }
    catch (XMLStreamException e) {
      e.printStackTrace();
    }

    return feed;
  }

  private InputStream initInputStream() {
    try {
      return url.openStream();
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
