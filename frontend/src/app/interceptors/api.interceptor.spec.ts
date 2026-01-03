import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { ApiInterceptor } from './api.interceptor';
import { HTTP_INTERCEPTORS, HttpClientModule, HttpRequest, HttpHandler } from '@angular/common/http';

describe('ApiInterceptor', () => {
  let interceptor: ApiInterceptor;
  let httpHandlerSpy: jasmine.SpyObj<HttpHandler>;

  beforeEach(() => {
    const spy = jasmine.createSpyObj('HttpHandler', ['handle']);

    TestBed.configureTestingModule({
      imports: [HttpClientModule, RouterTestingModule],
      providers: [
        ApiInterceptor,
        { provide: HTTP_INTERCEPTORS, useClass: ApiInterceptor, multi: true },
        { provide: HttpHandler, useValue: spy }
      ]
    });

    interceptor = TestBed.inject(ApiInterceptor);
    httpHandlerSpy = TestBed.inject(HttpHandler) as jasmine.SpyObj<HttpHandler>;
  });

  it('should be created', () => {
    expect(interceptor).toBeTruthy();
  });

  it('should add API base URL to requests', () => {
    const requestMock = new HttpRequest('GET', '/api/test');
    
    interceptor.intercept(requestMock, httpHandlerSpy);
    
    expect(httpHandlerSpy.handle).toHaveBeenCalled();
  });
});
