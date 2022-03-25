import java.util.*;
import java.sql.*;


public class Book {

    private int book_id; 
    private String title; 
    private String author; 
    private int year; 
    private int edition; 
    private String publisher; 
    private String isbn; 
    private String cover; 
    private String condition; 
    private String price; 
    private String notes; 

    public Book(int book_id, String title, String author, int year, int edition, String publisher, String isbn, String cover, String condition, String price, String notes) {
        this.book_id = book_id; 
        this.title = title;
        this.author = author; 
        this.year = year;
        this.edition = edition; 
        this.publisher = publisher; 
        this.isbn = isbn; 
        this.cover = cover; 
        this.condition = condition; 
        this.price = price; 
        this.notes = notes; 
    }

    public void setBook_id(int book_id){
        this.book_id = book_id;
    }

    public int getBook_id(){
        return this.book_id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setAuthor(String author){
        this.author = author;  
    }

    public String getAuthor(){
        return this.author;
    }    

    public void setYear(int year){
        this.year = year;
    }

    public int getYear(){
        return this.year;
    }

    public void setEdition(int edition){
        this.edition = edition; 
    }

    public int getEdition(){
        return this.edition;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }    

    public String getPublisher(){
        return this.publisher;       
    }

    public void setIsbn(String isbn){
        this.isbn = isbn; 
    }

    public String getIsbn(){
        return this.isbn;
    }

    public void setCover(String cover){
        this.cover = cover; 
    }

    public String getCover(){
        return this.cover; 
    }
    
    public void setCondition(String condition){
        this.condition = condition; 
    }

    public String getCondition(){
        return this.condition; 
    }    

    public void setPrice(String price){
        this.price = price; 
    }

    public String getPrice(){
        return this.price;
    }

    public void setNotes(String notes){
        this.notes = notes; 
    }

    public String getNotes(){
        return this.notes;
    } 
    
    public String toString() {
        return "--------------" + "\nBook_id: " + getBook_id() + "\nTitle: " + getTitle() + " \nAuthor: " + getAuthor() + " \nYear: " + getYear() + " \nEdition: " + getEdition() + "\npublisher: " + getPublisher() + "\nIsbn:" + getIsbn() + "\nCover: " + getCover() + "\ncondition: " + getCondition() + "\nprice: " + getPrice() + "\nNotes: " + getNotes();    
    }   
}