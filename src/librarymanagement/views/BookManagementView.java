package librarymanagement.views;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import librarymanagement.controllers.BookController;
import librarymanagement.models.Book;

public class BookManagementView extends JPanel {
    private JTable table; DefaultTableModel model;
    private boolean readOnly;
    public BookManagementView() { this(false); }
    public BookManagementView(boolean readOnly) {
        this.readOnly = readOnly;
        setLayout(new BorderLayout());
        model = new DefaultTableModel(new Object[]{"ID","Title","Author","Qty"},0){public boolean isCellEditable(int r,int c){return false;}};
        table = new JTable(model);
        refresh();
        add(new JScrollPane(table),BorderLayout.CENTER);
        if(!readOnly){
            JPanel p=new JPanel();
            JButton add=new JButton("Add"), edit=new JButton("Edit"), del=new JButton("Delete");
            p.add(add); p.add(edit); p.add(del); add(p,BorderLayout.SOUTH);
            add.addActionListener(e->addBook());
            edit.addActionListener(e->editBook());
            del.addActionListener(e->deleteBook());
        }
    }
    private void refresh(){ model.setRowCount(0); List<Book> list=BookController.listAll(); for(Book b:list) model.addRow(new Object[]{b.getId(),b.getTitle(),b.getAuthor(),b.getQuantity()}); }
    
    private void addBook(){
        String t=JOptionPane.showInputDialog(this,"Title:");
        if(t==null||t.trim().isEmpty()) return;
        
        String a=JOptionPane.showInputDialog(this,"Author:");
        if(a==null) return;
        
        int q=1;
        while(true){
            try{
                String qStr=JOptionPane.showInputDialog(this,"Quantity:","1");
                if(qStr==null) return;
                q=Integer.parseInt(qStr);
                if(q>0) break;
                JOptionPane.showMessageDialog(this,"Quantity must be positive");
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(this,"Please enter a valid number");
            }
        }
        
        Book b=new Book(0,t,a,"",0,"",q);
        if(BookController.addBook(b)) refresh();
    }
    
    private void editBook(){ 
        int r=table.getSelectedRow(); 
        if(r<0) return;
        
        int id=(int)model.getValueAt(r,0);
        String currentTitle=(String)model.getValueAt(r,1);
        String currentAuthor=(String)model.getValueAt(r,2);
        int currentQty=(int)model.getValueAt(r,3);
        
        String t=JOptionPane.showInputDialog(this,"New Title:",currentTitle);
        if(t==null) return;
        
        String a=JOptionPane.showInputDialog(this,"New Author:",currentAuthor);
        if(a==null) return;
        
        int q=currentQty;
        while(true){
            try{
                String qStr=JOptionPane.showInputDialog(this,"New Quantity:",currentQty);
                if(qStr==null) return;
                q=Integer.parseInt(qStr);
                if(q>0) break;
                JOptionPane.showMessageDialog(this,"Quantity must be positive");
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(this,"Please enter a valid number");
            }
        }
        
        Book b=new Book(id,t,a,"",0,"",q);
        if(BookController.updateBook(b)) refresh();
    }
    
    private void deleteBook(){ 
        int r=table.getSelectedRow(); 
        if(r<0) return;
        
        int id=(int)model.getValueAt(r,0);
        if(BookController.deleteBook(id)) refresh();
    }
}

