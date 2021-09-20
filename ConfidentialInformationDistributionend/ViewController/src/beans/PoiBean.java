package beans;


import com.sun.jmx.snmp.Timestamp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import oracle.adf.model.BindingContainer;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.jbo.Row;

import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;


public class PoiBean {
    public PoiBean() {
    }


        public static BindingContainer getBindingContainer() {
            //return (BindingContainer)JSFUtils.resolveExpression("#{bindings}");
            return (BindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        }

        public static DCBindingContainer getDCBindingContainer() {
            return (DCBindingContainer)getBindingContainer();
        }
      
    public void generateExcelCIDList(FacesContext facesContext, OutputStream outputStream) throws IOException {
        try {
       
            String [] ColHeader = {"No","Transmittal No","Requestor", "Purpose", "Supervisor", "Last Update", "Status"};
                              HSSFWorkbook workbook = new HSSFWorkbook();
                                  HSSFSheet worksheet = workbook.createSheet("CID Status");
            
                              DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                              DCIteratorBinding dcIteratorBindings = bindings.findIteratorBinding("monitoringVO1Iterator");
                                int totalRow = ((int)dcIteratorBindings.getEstimatedRowCount()) + 1;
            ViewObject vo = dcIteratorBindings.getViewObject();
                    RowSetIterator rsi = vo.createRowSetIterator(null);
                              HSSFRow  excelrow = null;
            CellStyle style = workbook.createCellStyle();
               Font font = workbook.createFont();
//               font.setBoldweight(Font.BOLDWEIGHT_BOLD);
               style.setFont(font);
            
                                      // Get all the rows of a iterator
                                      oracle.jbo.Row[] rows = dcIteratorBindings.getAllRowsInRange();
                                  int i = 0;
                           
                                        while (rsi.hasNext()) {
                                                 Row row     = rsi.next();
                                          //for (oracle.jbo.Row row : rows) {

                                              //print header on first row in excel
                                              if (i == 0) {
                                                  excelrow = (HSSFRow)worksheet.createRow((short)i);
                                                  short j = 0;
                                                  for (int k=0;k<ColHeader.length;k++) {
                                                    
                                                          HSSFCell cellA1 = excelrow.createCell((short) j);
                                                          cellA1.setCellValue(ColHeader[j]);
                                                        cellA1.setCellStyle(style);
                                                          j++;
                                                      
                                                  }
                                              }

                                              //print data from second row in excel
                                              ++i;
                                              short j = 1;
                                              excelrow = worksheet.createRow((short)i);
                                              
                                              for (String colName : row.getAttributeNames()) {
                                               //   System.out.println("hello "+row.getAttribute(colName));
                                                 // System.out.println("hello "+colName);
                                                 HSSFCell  cellNo = excelrow.createCell(0);
                                                cellNo.setCellValue(i);
                                                     
                                                if (colName.equalsIgnoreCase("TransmittalNumber")) {
    
                                                            HSSFCell  cell = excelrow.createCell(1);
                                                            cell.setCellValue(row.getAttribute(colName).toString());
                                                        //    System.out.println("colName "+colName+"row.getAttribute(colName).toString()"+row.getAttribute(colName).toString());
                                                        }
                                                if (colName.equalsIgnoreCase("Cidrequestor")) {
                                                      
                                                          HSSFCell  cell = excelrow.createCell(2);
                                                          cell.setCellValue(row.getAttribute(colName).toString());
                                                      //    System.out.println("colName "+colName+"row.getAttribute(colName).toString()"+row.getAttribute(colName).toString());
                                                      } 
                                                if (colName.equalsIgnoreCase("Cidpurpose")) {
    
                                                            HSSFCell  cell = excelrow.createCell(3);
                                                            cell.setCellValue(row.getAttribute(colName).toString());
                                                        //    System.out.println("colName "+colName+"row.getAttribute(colName).toString()"+row.getAttribute(colName).toString());
                                                        }                                                        
                                                if (colName.equalsIgnoreCase("Cidrequestorsupervisor")) {
    
                                                            HSSFCell  cell = excelrow.createCell(4);
                                                            cell.setCellValue(row.getAttribute(colName).toString());
                                                        //    System.out.println("colName "+colName+"row.getAttribute(colName).toString()"+row.getAttribute(colName).toString());
                                                        }                                                        
                                                if (colName.equalsIgnoreCase("LastUpdate")) {
    
                                                            HSSFCell  cell = excelrow.createCell(5);
                                                            cell.setCellValue(row.getAttribute(colName).toString());
                                                        //    System.out.println("colName "+colName+"row.getAttribute(colName).toString()"+row.getAttribute(colName).toString());
                                                        }
                                                if (colName.equalsIgnoreCase("Cidstatusrequest")) {
                                                      
                                                                HSSFCell  cell = excelrow.createCell(6);
                                                                cell.setCellValue(row.getAttribute(colName).toString());
                                                            //    System.out.println("colName "+colName+"row.getAttribute(colName).toString()"+row.getAttribute(colName).toString());
                                                            }
//                                                      if(null!=row.getAttribute(colName)&&!row.getAttribute(colName).toString().matches(".*View.*")){
//                                                      cell.setCellValue(row.getAttribute(colName).toString());
//                                                      }

                                                      j++;
                                                    
                                                  }
                                              
                                                 worksheet.createFreezePane(0, 1, 0, 1);
                                             } 
            rsi.closeRowSetIterator();
            workbook.write(outputStream);
            outputStream.flush();
            
        }
                                          catch (Exception e) {
                                        e.printStackTrace();
                                          }
                                      
    }
    public void generateExcelDocList(FacesContext facesContext, OutputStream outputStream) throws IOException {
        try {
       
            String [] ColHeader = {"No","Document Number","Document Title", "Document Status", "Remarks"};
                              HSSFWorkbook workbook = new HSSFWorkbook();
                                  HSSFSheet worksheet = workbook.createSheet("CID Document");
            
                              DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                              DCIteratorBinding dcIteratorBindings = bindings.findIteratorBinding("DetailCID1Iterator");
            int totalRow = ((int)dcIteratorBindings.getEstimatedRowCount()) + 1;
            ViewObject vo = dcIteratorBindings.getViewObject();
            RowSetIterator rsi = vo.createRowSetIterator(null);
            HSSFRow  excelrow = null;
            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
//            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            style.setFont(font);
            
                  // Get all the rows of a iterator
                  oracle.jbo.Row[] rows = dcIteratorBindings.getAllRowsInRange();
              int i = 0;
            
                    while (rsi.hasNext()) {
                             Row row     = rsi.next();

                                              //print header on first row in excel
                                              if (i == 0) {
                                                  excelrow = (HSSFRow)worksheet.createRow((short)i);
                                                  short j = 0;
                                                  for (int k=0;k<ColHeader.length;k++) {
                                                    
                                                          HSSFCell cellA1 = excelrow.createCell((short) j);
                                                          cellA1.setCellValue(ColHeader[j]);
                                                        cellA1.setCellStyle(style);
                                                          j++;
                                                      
                                                  }
                                              }

                                              //print data from second row in excel
                                              ++i;
                                              short j = 1;
                                              excelrow = worksheet.createRow((short)i);
                                              
                                              for (String colName : row.getAttributeNames()) {
                                               //   System.out.println("hello "+row.getAttribute(colName));
                                                 // System.out.println("hello "+colName);
                                                 HSSFCell  cellNo = excelrow.createCell(0);
                                                cellNo.setCellValue(i);
                                                     
                                                if (colName.equalsIgnoreCase("Ciddocnumber")) {
    
                                                            HSSFCell  cell = excelrow.createCell(1);
                                                            cell.setCellValue(row.getAttribute(colName).toString());
                                                        //    System.out.println("colName "+colName+"row.getAttribute(colName).toString()"+row.getAttribute(colName).toString());
                                                        }
                                                if (colName.equalsIgnoreCase("Ciddoctitle")) {
                                                      
                                                          HSSFCell  cell = excelrow.createCell(2);
                                                          cell.setCellValue(row.getAttribute(colName).toString());
                                                      //    System.out.println("colName "+colName+"row.getAttribute(colName).toString()"+row.getAttribute(colName).toString());
                                                      } 
                                                if (colName.equalsIgnoreCase("Cidstatusdocrequest")) {
    
                                                            HSSFCell  cell = excelrow.createCell(3);
                                                            cell.setCellValue(row.getAttribute(colName).toString());
                                                        //    System.out.println("colName "+colName+"row.getAttribute(colName).toString()"+row.getAttribute(colName).toString());
                                                        }    
                                              if (colName.equalsIgnoreCase("Remarks")) {
                                                          HSSFCell  cell = excelrow.createCell(4);
                                                          cell.setCellValue(row.getAttribute(colName) == null ? "-" :row.getAttribute(colName).toString());
                                                      //    System.out.println("colName "+colName+"row.getAttribute(colName).toString()"+row.getAttribute(colName).toString());
                                                      }  
                                                   //                                                      if(null!=row.getAttribute(colName)&&!row.getAttribute(colName).toString().matches(".*View.*")){
    //                                                      cell.setCellValue(row.getAttribute(colName).toString());
    //                                                      }

                                                      j++;
                                                    
                                                  }
                                              
                                                 worksheet.createFreezePane(0, 1, 0, 1);
                                             } 
            rsi.closeRowSetIterator();
            workbook.write(outputStream);
            outputStream.flush();
            
        }
                                          catch (Exception e) {
                                        e.printStackTrace();
                                          }
                                      
    }
    public void generateExcelHistoryList(FacesContext facesContext, OutputStream outputStream) throws IOException {
        try {
       
            String [] ColHeader = {"No","Action","Date", "Description"};
                              HSSFWorkbook workbook = new HSSFWorkbook();
                                  HSSFSheet worksheet = workbook.createSheet("CID Status");
            
                              DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                              DCIteratorBinding dcIteratorBindings = bindings.findIteratorBinding("HistoryCIDVO1Iterator");
            int totalRow = ((int)dcIteratorBindings.getEstimatedRowCount()) + 1;
            ViewObject vo = dcIteratorBindings.getViewObject();
            RowSetIterator rsi = vo.createRowSetIterator(null);
            HSSFRow  excelrow = null;
            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
//            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            style.setFont(font);
            
                  // Get all the rows of a iterator
                  oracle.jbo.Row[] rows = dcIteratorBindings.getAllRowsInRange();
              int i = 0;
            
                    while (rsi.hasNext()) {
                             Row row     = rsi.next();
                                              //print header on first row in excel
                                              if (i == 0) {
                                                  excelrow = (HSSFRow)worksheet.createRow((short)i);
                                                  short j = 0;
                                                  for (int k=0;k<ColHeader.length;k++) {
                                                    
                                                          HSSFCell cellA1 = excelrow.createCell((short) j);
                                                          cellA1.setCellValue(ColHeader[j]);
                                                        cellA1.setCellStyle(style);
                                                          j++;
                                                      
                                                  }
                                              }

                                              //print data from second row in excel
                                              ++i;
                                              short j = 1;
                                              excelrow = worksheet.createRow((short)i);
                                              
                                              for (String colName : row.getAttributeNames()) {
                                               //   System.out.println("hello "+row.getAttribute(colName));
                                                 // System.out.println("hello "+colName);
                                                 HSSFCell  cellNo = excelrow.createCell(0);
                                                cellNo.setCellValue(i);
                                                     
                                                if (colName.equalsIgnoreCase("Action")) {
    
                                                            HSSFCell  cell = excelrow.createCell(1);
                                                            cell.setCellValue(row.getAttribute(colName).toString());
                                                        //    System.out.println("colName "+colName+"row.getAttribute(colName).toString()"+row.getAttribute(colName).toString());
                                                        }
                                                if (colName.equalsIgnoreCase("Actiondate")) {
                                                      
                                                          HSSFCell  cell = excelrow.createCell(2);
                                                          cell.setCellValue(row.getAttribute(colName).toString());
                                                      //    System.out.println("colName "+colName+"row.getAttribute(colName).toString()"+row.getAttribute(colName).toString());
                                                      } 
                                                if (colName.equalsIgnoreCase("Logdescription")) {
    
                                                            HSSFCell  cell = excelrow.createCell(3);
                                                            cell.setCellValue(row.getAttribute(colName).toString());
                                                        //    System.out.println("colName "+colName+"row.getAttribute(colName).toString()"+row.getAttribute(colName).toString());
                                                        }                                                        
                                                   //                                                      if(null!=row.getAttribute(colName)&&!row.getAttribute(colName).toString().matches(".*View.*")){
    //                                                      cell.setCellValue(row.getAttribute(colName).toString());
    //                                                      }

                                                      j++;
                                                    
                                                  }
                                              
                                                 worksheet.createFreezePane(0, 1, 0, 1);
                                             } 
            
            rsi.closeRowSetIterator();
            workbook.write(outputStream);
            outputStream.flush();
            
        }
                                          catch (Exception e) {
                                        e.printStackTrace();
                                          }
                                      
    }
    
//    public String ExportExcel() {
//        // Add event code here...
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        try{
//        OutputStream outputStream = new FileOutputStream("c:/Temp/testExcelPoi.xls");
//        generateExcel(facesContext, outputStream);
//            outputStream.close();
//        }catch(IOException e){
//            e.printStackTrace();
//            }finally{
//                
//            }
//        return null;
//    }
}
