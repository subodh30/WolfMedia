package edu.ncsu.dbms.wolfmedia.controllers;

import edu.ncsu.dbms.wolfmedia.services.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PaymentsController {

    private final PaymentsService paymentsService;

    public PaymentsController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @GetMapping("/makeRoyaltyPayment")
    public String makeRoyaltyPayment(@RequestParam Integer songId) {
        return paymentsService.makeRoyaltyPayment(songId);
    }

    @GetMapping("/generateMonthlyRoyalties")
    public List<Map<String, Object>> generateMonthlyRoyalties() {
        return paymentsService.generateMonthlyRoyalties();
    }

    @GetMapping("/makePaymentToPodcastHost")
    public String makePaymentToPodcastHost() {
        return paymentsService.makePaymentToPodcastHost();
    }

    @GetMapping("/receivePaymentFromSubscribers")
    public String receivePaymentFromSubscribers() {
        return paymentsService.receivePaymentFromSubscribers();
    }

}
