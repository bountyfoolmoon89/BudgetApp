package com.example.budgetapp.services.impl;

import com.example.budgetapp.model.Transaction;
import com.example.budgetapp.services.BudgetService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class BudgetServiceImpl implements BudgetService {
    public static final int SALARY = 32_000;

    public static final int SAVING = 5000;

    public static final int DAILY_BUDGET = (SALARY - SAVING) / LocalDate.now().lengthOfMonth();

    public static int balance = 0;


    private static final int AVG_SALARY = (10_000+ 10_500+ 10_500+ 11_100+ 15_000+ 16_800+ 17_000+ 20_800+ 23_990+ 20_000+ 25_000+ 32_000) / 12;

    private static final double AVG_DAYS = 29.3;

    private static Map<Month, Map<Long, Transaction>> transactions = new TreeMap<>();

    @Override
    public int getDailyBudget() {
        return DAILY_BUDGET;
    }

    @Override
    public int getBalance() {
        return SALARY - SAVING - getAllSpend();
    }

    public int getDailyBalance(){
        return DAILY_BUDGET * LocalDate.now().getDayOfMonth() - getAllSpend();
    }

    private int getAllSpend(){
        Map<Long, Transaction> monthTransactions = transactions.getOrDefault(LocalDate.now().getMonth(), new LinkedHashMap<>());
        int sum = 0;
        for (Transaction transaction : monthTransactions.values()) {
            sum += transaction.getSum();
        }
        return sum;
    }

    public void addTransaction(Transaction transaction){
        Map<Long, Transaction> monthTransactions = transactions.getOrDefault(LocalDate.now().getMonth(), new LinkedHashMap<>());
        monthTransactions.put(lastId++, transaction);
    }

    private static long lastId = 0;

    @Override
    public int getVacationBonus(int daysCount){
        double avgDaySalary = AVG_SALARY / AVG_DAYS;
        return (int) (daysCount * avgDaySalary);
    }

    @Override
    public int getSalaryWithVacation(int vacationDaysCount, int vacationWorkingDaysCount, int workingDaysInMonth){
        int salary = SALARY / workingDaysInMonth * (workingDaysInMonth - vacationWorkingDaysCount);
        return salary + getVacationBonus(vacationDaysCount);
    }
}
