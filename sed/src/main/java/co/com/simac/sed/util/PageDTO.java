package co.com.simac.sed.util;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PageDTO<E> {

  public static int DEFAULT_PAGE_SIZE = 10;
  public static int DEFAULT_INITIAL_PAGE = 1;

  private int size;
  private int page;
  private long totalItems;
  private int totalPages;
  private List<E> results;

  public PageDTO() {
    this.size = DEFAULT_PAGE_SIZE;
    this.page = DEFAULT_INITIAL_PAGE;
    this.results = Collections.<E> emptyList();
  }

  public PageDTO(int size, int page, long totalItems, int totalPages, List<E> results) {
    super();
    this.size = size;
    this.page = page;
    this.totalItems = totalItems;
    this.totalPages = totalPages;
    this.results = results;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public long getTotalItems() {
    return totalItems;
  }

  public void setTotalItems(long totalItems) {
    this.totalItems = totalItems;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  public List<E> getResults() {
    if (results == null) {
      results = new ArrayList<E>();
    }
    return results;
  }

  public void setResults(List<E> results) {
    this.results = results;
  }

}
