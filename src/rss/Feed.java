package rss;

import java.util.ArrayList;
import java.util.List;

public class Feed {
  
  private final String feedName;
  private final String createdAt;
  
  private final List<FeedItem> entries = new ArrayList<FeedItem>();
  
  public Feed(String name, String createdAt){
    this.feedName = name;
    this.createdAt = createdAt;
  }

  public String getName() {
    return feedName;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public List<FeedItem> getEntries() {
    return entries;
  }
  
  public String toString(){
    return "Feed " + feedName + ", created at " + createdAt;
  }
}