import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { HttpParams } from '@angular/common/http';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  brand: string = "";
  id: string = "";
  title = 'pb_web_ui';
  data:Array<any> = [];
  data1 :any
  var1 :any;
  zipcode = [{ num: "111" }, { num: "222" }, { num: "333" }, { num: "444" }];
  radValue = "Lucene"
  str_Score = ""
  str_Rank = ""
  str_Tweet = ""
  constructor(public http:HttpClient, public router:Router){
  }

  public radchangefunction(event: any){
    this.radValue = event.target.value

  }

  getTextBoxValue(query:any){
    //this.str_Score = "Score"
    this.str_Rank = "Rank"
    this.str_Tweet = "Tweet Content"

    if (String(this.radValue) == "Lucene")
    {
      this.getlucene(String(query.value));
    }
    else if(String(this.radValue) == "Hadoop")
    {
      this.gethadoop(String(query.value));
    } 
    
  }  

  public getlucene(query:string)
  {
    
    
    const params = new HttpParams()
    .set('query',''+query)
    this.http.get('http://localhost:8080/lucene',{params}).subscribe(res => {
      this.data1 = res;
      //const array = JSON.parse(res.toString())
      //console.log(array);
      var rt:Array<any> = Object.values(res);
      console.log(rt)
      const res_arr = rt.filter((thing, index, self) =>
      index === self.findIndex((t) => (
        t.content === thing.content 
      ))
    )
    
    this.data1 = res_arr
    })
      
  }

  public gethadoop(query:string)
  {
    const params = new HttpParams()
    .set('query',''+query)
    this.http.get('http://localhost:8080/hadoop',{params}).subscribe(res => {
      this.data1 = res;
      var rt:Array<any> = Object.values(res);
      console.log(rt)
      const res_arr = rt.filter((thing, index, self) =>
      index === self.findIndex((t) => (
        t.content === thing.content 
      ))
    )
    this.data1 = res_arr      
    })

      
  }


}
