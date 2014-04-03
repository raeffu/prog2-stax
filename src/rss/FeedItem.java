package rss;

public class FeedItem {
  private String name;
  private String ort;
  
//  public FeedItem(String name, String ort){
//    this.name = name;
//    this.ort = ort;
//  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOrt() {
    return ort;
  }

  public void setOrt(String ort) {
    this.ort = ort;
  }

  public String toString(){
    return "Bad: " + name + ", Ort: " + ort;
  }
}
