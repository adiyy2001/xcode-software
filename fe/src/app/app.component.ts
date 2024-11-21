import { FormsModule } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { CurrencyService } from './services/currency.service';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { CurrencyFormComponent } from "./components/currency-form/currency-form.component";
import { CurrencyListComponent } from './components/currency-list/currency-list.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule, RouterOutlet, CurrencyFormComponent, CurrencyListComponent],
})
export class AppComponent implements OnInit {
  currencyCode = '';
  userName = '';
  requests: any[] = [];
  loading = false;

  constructor(private currencyService: CurrencyService) {}

  ngOnInit(): void {
    this.fetchRequests();
  }

  saveRequest(): void {
    if (this.currencyCode && this.userName) {
      this.loading = true;
      this.currencyService.saveCurrencyRequest(this.currencyCode, this.userName).subscribe({
        next: () => {
          this.fetchRequests();
          this.currencyCode = '';
          this.userName = '';
        },
        error: () => {
          this.loading = false;
        },
      });
    }
  }

  fetchRequests(): void {
    this.currencyService.getAllRequests().subscribe({
      next: (data: any) => {
        console.log(data)
        this.requests = data.details || [];
        this.loading = false;
      },
      error: () => {
        this.loading = false;
      },
    });
  }
}
