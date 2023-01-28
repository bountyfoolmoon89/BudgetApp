package com.example.budgetapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping("/")
    public String appStarted(){
        return "Приложение запущено";
    }
    @GetMapping("/info")
    public String appInfo(){
        return "Имя ученика: Егор. Название проекта: Budget. Дата создания проекта: 28.01.2023. Проект для рассчета бюджета";
    }
}
