package com.example.budgetapp.services.impl;

import com.example.budgetapp.services.BudgetService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BudgetServiceImpl implements BudgetService {
    private static final int SALARY = 32_000;

    @Override
    public int getDailyBudget() {
        return SALARY / 30;
    }

    @Override
    public int getBalance() {
        return SALARY - LocalDate.now().getDayOfMonth() * getDailyBudget();
    }
}
