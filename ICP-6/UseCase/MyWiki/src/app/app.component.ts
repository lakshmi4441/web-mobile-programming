import {Component, ElementRef, ViewChild} from '@angular/core';
import { HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  pages: any;
  isLoading = true;
  @ViewChild('searchText') searchTerm: ElementRef;

  constructor(private _http: HttpClient) {}

  submit() {
    this._http.jsonp("http://en.wikipedia.org/w/api.php?format=json&action=query&generator=search&gsrnamespace=0&gsrlimit=10&prop=pageimages|extracts&pilimit=max&exintro&explaintext&exsentences=1&exlimit=max&callback=archive&gsrsearch=" + this.searchTerm.nativeElement.value, 'callback')
    .subscribe((data: any) => {
      this.isLoading = false;
      this.pages = Object.keys(data.query.pages).map(function (k) {
        var i = data.query.pages[k];
        return {title: i.title, body: i.extract, page: 'http://en.wikipedia.org/?curid=' + i.pageid}
      });
      console.log(this.pages);
    });
  }

}
