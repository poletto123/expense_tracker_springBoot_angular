import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs";
import { Expense } from "./expense";
import { environment } from '../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class ExpenseService {
    private apiServerUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient) {}

    public getExpenses(): Observable<Expense[]> {
        return this.http.get<Expense[]>(`${this.apiServerUrl}/expenses`)
    }

    public addExpense(expense: Expense): Observable<Expense> {
        return this.http.post<Expense>(`${this.apiServerUrl}/expenses/add`, expense);
    }

    public updateExpense(expense: Expense): Observable<Expense> {
        return this.http.put<Expense>(`${this.apiServerUrl}/expenses/update`, expense);
    } 

    public deleteExpense(expenseId: number): Observable<void> {
        return this.http.delete<void>(`${this.apiServerUrl}/expenses/delete/${expenseId}`);
    }
}