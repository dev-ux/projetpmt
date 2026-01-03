import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class ApiInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Add API base URL to requests
    const apiReq = req.clone({
      url: `http://localhost:8080/api${req.url}`
    });
    
    return next.handle(apiReq);
  }
}
