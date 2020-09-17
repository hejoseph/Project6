package com.example.project6.service;

import com.example.project6.model.BankAccount;
import com.example.project6.model.User;
import com.example.project6.model.WithDraw;

public interface IWithDrawService {

	WithDraw getLastTransaction(String email);

	boolean createTransaction(User user, BankAccount bankAccount, double amount);

}
