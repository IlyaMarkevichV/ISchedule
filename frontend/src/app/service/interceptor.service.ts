import { Injectable } from '@angular/core';
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from "@angular/common/http";
import {TokenStorage} from "./token-storage.service";
import {tap} from "rxjs/operators";
import {Observable} from "rxjs";
import {Router} from "@angular/router";

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable({
  providedIn: 'root'
})
export class Interceptor implements HttpInterceptor {

  constructor(private tokenStorage: TokenStorage, private router: Router) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("Interceptor works");
    let authReq = req;
    if (this.tokenStorage.getToken() != null) {
      authReq = req.clone({headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + this.tokenStorage.getToken())});
    }
    return next.handle(authReq).pipe(tap((event: HttpEvent<any>) => {
        if (event instanceof HttpResponse) {
          console.log("Interceptor log response", event);
        }
      }, (err: any) => {
        console.log('req url :: ' + req.url);
        console.error("Interceptor log error", err);
        if (err instanceof HttpErrorResponse) {
          if (err.status === 401) {
            this.router.navigate(['login']);
          }
        }
      }
    ));
  }

}

