package com.example.wallet.service;

import com.example.wallet.model.Wallet;
import com.example.wallet.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository repo;

    public WalletService(WalletRepository repo) {
        this.repo = repo;
    }

    public Wallet process(UUID id, String operation, double amount) {

        Wallet wallet = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        if (operation.equals("DEPOSIT")) {
            wallet.setBalance(wallet.getBalance() + amount);
        } 
        else if (operation.equals("WITHDRAW")) {
            if (wallet.getBalance() < amount) {
                throw new RuntimeException("Insufficient balance");
            }
            wallet.setBalance(wallet.getBalance() - amount);
        } 
        else {
            throw new RuntimeException("Invalid operation");
        }

        return repo.save(wallet);
    }

    public Wallet get(UUID id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }
}