import { Injectable } from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from "@angular/common/http";
import {TokenStorage} from "./token-storage.service";

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable({
  providedIn: 'root'
})
export class Interceptor implements HttpInterceptor {

  constructor(private tokenStorage: TokenStorage) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    console.log(req);
    let authReq = req;
    const token = this.tokenStorage.getToken();
    console.log(token);
    if (token != null) {
      authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });
    }
    return next.handle(authReq);
  }
}
