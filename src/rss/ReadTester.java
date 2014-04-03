package rss;

public class ReadTester {

  public static void main(String[] args) {
    RSSFeedParser parser = new RSSFeedParser("http://www.wiewarm.ch/xmlData.php");
    Feed feed = parser.readFeed();
    System.out.println(feed);
    
    for(FeedItem bad : feed.getEntries()){
      System.out.println(bad);
    }
  }

}
