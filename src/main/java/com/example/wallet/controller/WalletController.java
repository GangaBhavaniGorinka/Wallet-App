package com.example.wallet.controller;

import com.example.wallet.dto.WalletRequest;
import com.example.wallet.model.Wallet;
import com.example.wallet.service.WalletService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class WalletController {

    private final WalletService service;

    public WalletController(WalletService service) {
        this.service = service;
    }

    @PostMapping("/wallet")
    public Wallet update(@RequestBody WalletRequest request) {
        return service.process(
                request.getWalletId(),
                request.getOperationType(),
                request.getAmount()
        );
    }

    @GetMapping("/wallets/{id}")
    public Wallet get(@PathVariable UUID id) {
        return service.get(id);
    }
}