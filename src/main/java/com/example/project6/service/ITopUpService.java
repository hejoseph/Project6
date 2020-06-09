package com.example.project6.service;

import com.example.project6.model.TopUp;

public interface ITopUpService {

	TopUp getLastTransaction(String email);

}
