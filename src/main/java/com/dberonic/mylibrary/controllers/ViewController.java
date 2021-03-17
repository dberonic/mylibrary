package com.dberonic.mylibrary.controllers;

import com.dberonic.mylibrary.models.Book;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ViewController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping({ "/index", "/" })
    public String index() {
        return "index";
    }


    @RequestMapping("/books")
    public String books(Model model) {

        // gets the user role
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        // sets the model for admin
        if(username.equals("admin")){
            model.addAttribute("books", books);
        } else { // sets the model for all other users
            List<Book> avaiableBooks = new ArrayList<>();

            // filteres borrowed books
            for(int i = 0; i < books.size(); i++){
                if(books.get(i).getBorrowedBy().equals("Available") || books.get(i).getBorrowedBy().equals(username)){
                    avaiableBooks.add(books.get(i));
                }
            }
            model.addAttribute("books", avaiableBooks);
        }

        return "books";
    }

    private static final List<Book> books = new ArrayList<>();
    static {
        load();
        System.out.println("Starting");
    }

    public static List<Book> getBooks() {
        return books;
    }

    /**
     * Loads the data from the xml file into the model
     * @return success
     */
    public static boolean load(){
        DocumentBuilder builder = null;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        // Fetch XML File
        Document document = null;
        try {
            document = builder.parse(new File("data.xml"));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        document.getDocumentElement().normalize();

        NodeList nList = document.getElementsByTagName("book");
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String id =  element.getAttribute("id");
                String author = element.getElementsByTagName("author").item(0).getTextContent();
                String title =  element.getElementsByTagName("title").item(0).getTextContent();
                String genre =  element.getElementsByTagName("genre").item(0).getTextContent();
                String price =  element.getElementsByTagName("price").item(0).getTextContent();
                String publish_date =  element.getElementsByTagName("publish_date").item(0).getTextContent();
                String description =  element.getElementsByTagName("genre").item(0).getTextContent();

                books.add(new Book(id,author, title, genre, price, publish_date, description));
            }
        }
        return true;
    }




}
