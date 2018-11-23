import { Injectable } from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {Router} from "@angular/router";
import {TokenStorage} from "./token-storage.service";

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable({
  providedIn: 'root'
})
export class Interceptor implements HttpInterceptor {

  constructor(private tokenStorage: TokenStorage) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req;
    console.log(this.tokenStorage.getToken());
    if (this.tokenStorage.getToken() != null) {
      authReq = req.clone({
        headers: req.headers.set(
          TOKEN_HEADER_KEY,
          'Bearer ' + this.tokenStorage.getToken())
      });

    }
    return next.handle(authReq);
  }
}
