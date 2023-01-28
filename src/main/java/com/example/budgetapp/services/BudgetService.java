package com.example.budgetapp.services;

import org.springframework.stereotype.Service;

public interface BudgetService {

    int getDailyBudget();

    int getBalance();
}
