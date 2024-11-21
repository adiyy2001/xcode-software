import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, map, retry, throwError } from 'rxjs';
import { ApiError, ApiResponse, CurrencyRequest, CurrencyValueResponse } from '../model/types';

@Injectable({
  providedIn: 'root',
})
export class CurrencyService {
  private apiBaseUrl: string = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  saveCurrencyRequest(currency: string, name: string): Observable<CurrencyValueResponse> {
    return this.http
      .post<{ value: number } | ApiResponse<CurrencyValueResponse>>(
        `${this.apiBaseUrl}/currencies/get-current-currency-value-command`,
        { currency, name }
      )
      .pipe(
        map((response) => {
          const result = this.normalizeSaveResponse(response);
          return result;
        }),
        catchError((err) => {
          return this.handleError(err);
        })
      );
  }

  getAllRequests(): Observable<CurrencyRequest[]> {
    return this.http
      .get<ApiResponse<CurrencyRequest[]>>(`${this.apiBaseUrl}/currencies/requests`)
      .pipe(
        map((response) => {
          this.validateApiResponse(response);
          return response.details;
        }),
        catchError((err) => {
          console.error('[ERROR] getAllRequests failed:', err);
          return this.handleError(err);
        })
      );
  }

  private normalizeSaveResponse(
    response: { value: number } | ApiResponse<CurrencyValueResponse>
  ): CurrencyValueResponse {

    if ('value' in response) {
      return { value: response.value };
    } else if (this.isApiResponse<CurrencyValueResponse>(response) && response.success) {
      return response.details;
    }

    console.error('[ERROR] normalizeSaveResponse: Invalid response format.');
    throw new Error('Invalid response format or unsuccessful API call');
  }

  private validateApiResponse<T>(response: ApiResponse<T>): void {
    const requiredKeys: (keyof ApiResponse<T>)[] = ['success', 'message', 'details'];
    requiredKeys.forEach((key) => {
      if (!(key in response)) {
        console.error(`[ERROR] validateApiResponse: Missing key '${key}' in response.`);
        throw new Error(`Invalid API response: missing key '${key}'`);
      }
    });

  }

  private isApiResponse<T>(response: any): response is ApiResponse<T> {
    const isValid =
      'success' in response &&
      'details' in response &&
      'message' in response;

    return isValid;
  }

  private handleError(error: any): Observable<never> {
    console.error('[ERROR] handleError: Handling error:', error);

    let message = 'An unexpected error occurred.';
    if (error.status === 404) {
      message = 'Resource not found.';
    } else if (error.status === 401) {
      message = 'Unauthorized. Please log in.';
    } else if (error.error?.message) {
      message = error.error.message;
    }

    console.error('[ERROR] handleError: Final error message:', message);
    return throwError(() => ({
      message,
      statusCode: error.status,
    } as ApiError));
  }
}
