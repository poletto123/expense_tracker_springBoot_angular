export interface Expense {
    id: number;
    description: string;
    value: number;
    date: Date;
    note: string;
    // user: user.ts;
    // categories: category[];
}