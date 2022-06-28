package com.example.demo;
import java.io.IOException;
import java.io.BufferedReader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.store.FSDirectory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;

public class lucene_ind {
    String query_str;
    lucene_ind(String query_str){
        this.query_str = query_str;
    }

    public JSONArray lucene() throws IOException, ParseException{

        String path = "C:\\.MSCS\\q2\\IR\\fin_result";
        path = path.replace("\\", "/");
        File f= new File(path);
        Directory directory = FSDirectory.open(f.toPath());
        DirectoryReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        Analyzer analyzer = new StandardAnalyzer();

        QueryParser parser = new QueryParser("content", analyzer);
        Query query = parser.parse(this.query_str);
        //System.out.println(query.toString());

        int topHitCount = 100;
        ScoreDoc[] hits = indexSearcher.search(query, topHitCount).scoreDocs;


        JSONArray ind_jsonArray = new JSONArray();
        for (int rank = 0; rank < hits.length; ++rank) {

            Document hitDoc = indexSearcher.doc(hits[rank].doc);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("rank", rank + 1);
            jsonObject.put("score", hits[rank].score);
            jsonObject.put("content", hitDoc.get("content"));
			jsonObject.put("UserID", hitDoc.get("content"));
			jsonObject.put("Date", hitDoc.get("Date"));
			jsonObject.put("Location", hitDoc.get("Location"));
            ind_jsonArray.add(jsonObject);
        }
        indexReader.close();
        directory.close();

        return ind_jsonArray;

    }


}
