import { Component, computed, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CurrencyService } from '../../services/currency.service';
import Toastify from 'toastify-js';

@Component({
  selector: 'app-currency-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './currency-form.component.html',
  styleUrls: ['./currency-form.component.scss'],
})
export class CurrencyFormComponent {
  userName = signal('');
  currencyCode = signal('');
  loading = signal(false);
  error = signal<string | null>(null);

  constructor(private currencyService: CurrencyService) {}

  onCurrencyCodeChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    this.currencyCode.set(input.value.trim());
  }

  onUserNameChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    this.userName.set(input.value.trim());
  }

  isFormValid = computed(() =>
    this.currencyCode().trim() !== '' && this.userName().trim() !== ''
  );

  async validateCurrencyCode(): Promise<boolean> {
    try {
      const response = await fetch('https://open.er-api.com/v6/latest/USD'); // Example base currency
      const data = await response.json();
      return data.rates.hasOwnProperty(this.currencyCode().toUpperCase());
    } catch (err) {
      this.showToast('Error validating currency. Try again later.', 'error');
      return false;
    }
  }

  async submitRequest(): Promise<void> {
    if (!this.isFormValid()) {
      this.showToast('Currency Code and Name are required.', 'error');
      return;
    }

    this.loading.set(true);
    this.error.set(null);

    const isValidCurrency = await this.validateCurrencyCode();
    if (!isValidCurrency) {
      this.showToast('Invalid Currency Code. Please check and try again.', 'error');
      this.loading.set(false);
      return;
    }

    this.currencyService.saveCurrencyRequest(this.currencyCode(), this.userName()).subscribe({
      next: () => {
        this.currencyCode.set('');
        this.userName.set('');
        this.loading.set(false);
        this.showToast('Request submitted successfully!', 'success');
        window.dispatchEvent(new CustomEvent('refreshCurrencyList'));
      },
      error: (err) => {
        this.error.set(err.message || 'Submission failed.');
        this.showToast(err.message || 'Submission failed.', 'error');
        this.loading.set(false);
      },
    });
  }

  showToast(message: string, type: 'success' | 'error'): void {
    Toastify({
      text: message,
      duration: 3000,
      gravity: 'bottom',
      position: 'left',
      backgroundColor: type === 'success' ? '#4CAF50' : '#F44336',
    }).showToast();
  }
}
