package com.action;

public class tempAction {
    public tempAction(String rowId, String action) {
        super();
        this.rowId= rowId;
        this.action = action;
    }
    public tempAction(String rowId, String action,String comment) {
        super();
        this.rowId= rowId;
        this.action = action;
        this.comment = comment;
    }
    
    private String rowId;
    private String action;
    private String comment;
    private String docstatus;

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getRowId() {
        return rowId;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
    
  public void setDocStatus(String docstatus) {
      this.docstatus = docstatus;
  }

  public String getDocStatus() {
      return docstatus;
  }
}
