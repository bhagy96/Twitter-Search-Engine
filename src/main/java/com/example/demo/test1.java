package com.example.demo;


import org.apache.lucene.queryparser.classic.ParseException;
import org.json.simple.JSONArray;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class test1 {
    @CrossOrigin(origins = "*")
    @GetMapping("/lucene")
    public JSONArray query_parser(@RequestParam(name = "query") String query) throws IOException, ParseException {
        //String q = "covid";
        //System.out.println(query);
        lucene_ind lc = new lucene_ind(query);

        JSONArray ja = lc.lucene();
        //System.out.println(ja);
        return ja;
    }
	@GetMapping("/hadoop")
	public JSONArray hadoop_query_parser(@RequestParam(name = "query") String query) throws IOException, ParseException {
        
        hadoop_ind hi= new hadoop_ind(query);

        JSONArray ja = hi.lucene();
        return ja;
    }
    public String print(){
        return "Hello world";
    }

}
