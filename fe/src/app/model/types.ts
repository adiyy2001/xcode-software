export interface CurrencyRequest {
  currency: string;
  name: string;
  date: string;
  value: number;
}

export interface ApiResponseSuccess<T> {
  success: true;
  message: string;
  details: T;
}

export interface ApiResponseFailure {
  success: false;
  message: string;
  details: [];
}

export interface ApiError {
  message: string;
  statusCode?: number;
}

export interface CurrencyValueResponse {
  value: number;
}

export interface ApiResponseSuccess<T> {
  success: true;
  message: string;
  details: T;
}

export interface ApiResponseFailure {
  success: false;
  message: string;
  details: [];
}

export type ApiResponse<T> = ApiResponseSuccess<T> | ApiResponseFailure;

