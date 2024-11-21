import { Component, effect, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CurrencyService } from '../../services/currency.service';
import { catchError, finalize, of } from 'rxjs';
import { CurrencyRequest, ApiError } from '../../model/types';

@Component({
  selector: 'app-currency-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './currency-list.component.html',
  styleUrls: ['./currency-list.component.scss']
})
export class CurrencyListComponent {
  requests = signal<CurrencyRequest[]>([]);
  loading = signal(false);
  error = signal<string | null>(null);

  constructor(private currencyService: CurrencyService) {
    window.addEventListener('refreshCurrencyList', () => this.loadRequests());
  }

  ngOnInit(): void {
    this.loadRequests();
  }

  loadRequests(): void {
    this.loading.set(true);
    this.error.set(null);

    this.currencyService
      .getAllRequests()
      .pipe(
        finalize(() => this.loading.set(false)),
        catchError((err: ApiError) => {
          this.error.set(err.message || 'Failed to load requests.');
          return of([]);
        })
      )
      .subscribe((requests) => {
        this.requests.set(requests);
      });
  }
}
