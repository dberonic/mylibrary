package com.dberonic.mylibrary.controllers;

import com.dberonic.mylibrary.models.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @RequestMapping(value = "demo1/{bookid}",
            method = RequestMethod.GET,
            produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> book(@PathVariable("bookid") String bookId){

            List<Book> books = ViewController.getBooks();

        for(int i = 0 ; i < books.size(); i++){

            if(bookId.equals( books.get(i).getId())){
                    System.out.println("Book found");
                    books.get(i).setAvailable(false);
                    books.get(i).setBorrowedBy("user");
                }
            }

        try {
            ResponseEntity<String> responseEntity = new ResponseEntity<String>("OK", HttpStatus.OK);
            System.out.println(bookId);

            return responseEntity;
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "demo2/{bookid}",
            method = RequestMethod.GET,
            produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> book2(@PathVariable("bookid") String bookId){

        List<Book> books = ViewController.getBooks();

        for(int i = 0 ; i < books.size(); i++){

            if(bookId.equals( books.get(i).getId())){
                System.out.println("Book found");
                books.get(i).setAvailable(true);
                books.get(i).setBorrowedBy(null);

            }
        }

        try {
            ResponseEntity<String> responseEntity = new ResponseEntity<String>("OK2", HttpStatus.OK);
            System.out.println(bookId);
            return responseEntity;
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

}
