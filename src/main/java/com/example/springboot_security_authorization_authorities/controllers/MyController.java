package com.example.springboot_security_authorization_authorities.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

//AUTHORITIES REPRESENT DB CRUD OPERATIONS: Create, Read, Update, Delete Book
@Controller
public class MyController {

  @ResponseBody
  @PreAuthorize("hasAuthority('book.create')")
  @RequestMapping("/CreateBook")
  public String createBook() {
    return "Only ADMIN can create Book";
  }

  @ResponseBody
  @PreAuthorize("hasAuthority('book.read')")
  @RequestMapping("/ReadBook")
  public String readBook() {
    return "ADMIN and USER can read Book";
  }

  @ResponseBody
  @PreAuthorize("hasAuthority('book.update')")
  @RequestMapping("/UpdateBook")
  public String updateBook() {
    return "Only ADMIN can update Book";
  }

  @ResponseBody
  @PreAuthorize("hasAuthority('book.delete')")
  @RequestMapping("/DeleteBook")
  public String deleteBook() {
    return "Only ADMIN can delete Book";
  }

}

