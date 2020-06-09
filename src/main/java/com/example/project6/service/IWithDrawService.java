package com.example.project6.service;

import com.example.project6.model.WithDraw;

public interface IWithDrawService {

	WithDraw getLastTransaction(String email);

}
