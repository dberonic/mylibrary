package com.dberonic.mylibrary.controllers;

import com.dberonic.mylibrary.models.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
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
import java.util.UUID;

//@RestController
//@RequestMapping(value = "/books")
@RestController
@RequestMapping("api/ajaxrest")
public class BookController {

    @RequestMapping(value = "demo1",
            method = RequestMethod.GET,
            produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> book(){
        try {
            ResponseEntity<String> responseEntity = new ResponseEntity<String>("OK", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "demo2",
            method = RequestMethod.GET,
            produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> book2(){
        try {
            ResponseEntity<String> responseEntity = new ResponseEntity<String>("OK2", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }








//    private static final List<Book> books = new ArrayList<>();
//    static {
//        load();
//    }
//
//
//    @GetMapping
//    public String getAllBooks(Model model){
//        model.addAttribute("books", books);
//        return "books";
//    }
//
//    public static boolean load(){
//        DocumentBuilder builder = null;
//        try {
//            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        }
//        // Fetch XML File
//        Document document = null;
//        try {
//            document = builder.parse(new File("data.xml"));
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        document.getDocumentElement().normalize();
//
//        NodeList nList = document.getElementsByTagName("book");
//        for (int i = 0; i < nList.getLength(); i++) {
//            Node node = nList.item(i);
//            if (node.getNodeType() == Node.ELEMENT_NODE) {
//                Element element = (Element) node;
//
//                String id =  element.getAttribute("id");
//                String author = element.getElementsByTagName("author").item(0).getTextContent();
//                String title =  element.getElementsByTagName("title").item(0).getTextContent();
//                String genre =  element.getElementsByTagName("genre").item(0).getTextContent();
//                String price =  element.getElementsByTagName("price").item(0).getTextContent();
//                String publish_date =  element.getElementsByTagName("publish_date").item(0).getTextContent();
//                String description =  element.getElementsByTagName("genre").item(0).getTextContent();
//
//                books.add(new Book(id,author, title, genre, price, publish_date, description));
//            }
//        }
//
//
//        return true;
//    }

}
