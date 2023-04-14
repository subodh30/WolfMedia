package edu.ncsu.dbms.wolfmedia.controllers;

import edu.ncsu.dbms.wolfmedia.services.PaymentsService;
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
    public String makeRoyaltyPayment(@RequestParam Integer songId, @RequestParam int month, @RequestParam int year) throws Exception {
        return paymentsService.makeRoyaltyPayment(songId, month, year);
    }

    @GetMapping("/generateMonthlyRoyalties")
    public List<Map<String, Object>> generateMonthlyRoyalties(@RequestParam int month, @RequestParam int year) throws Exception {
        return paymentsService.generateMonthlyRoyalty(month, year);
    }

    @GetMapping("/makePaymentToPodcastHost")
    public String makePaymentToPodcastHost(@RequestParam int month, @RequestParam int year) throws Exception {
        return paymentsService.makePaymentToPodcastHost(month, year);
    }

    @GetMapping("/receivePaymentFromSubscribers")
    public String receivePaymentFromSubscribers() throws Exception {
        return paymentsService.receivePaymentFromSubscribers();
    }

}
