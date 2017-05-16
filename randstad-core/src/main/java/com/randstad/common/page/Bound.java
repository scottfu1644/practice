package com.randstad.common.page;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
public class Bound {

  public static final int NO_ROW_OFFSET = 0;

  public static final int NO_ROW_LIMIT = Integer.MAX_VALUE;

  public static final Bound DEFAULT = new Bound();

  private int offset;

  private int limit;

  public Bound() {
    this.offset = NO_ROW_OFFSET;
    this.limit = NO_ROW_LIMIT;
  }

  public Bound(int offset, int limit) {
    this.offset = offset;
    this.limit = limit;
  }

  public int getOffset() {
    return offset;
  }

  public int getLimit() {
    return limit;
  }

}
