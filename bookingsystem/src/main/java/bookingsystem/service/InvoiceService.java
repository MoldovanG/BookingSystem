package com.moldovan.uni.bookingsystem.service;

import com.moldovan.uni.bookingsystem.domain.ExtraService;
import com.moldovan.uni.bookingsystem.domain.Invoice;
import com.moldovan.uni.bookingsystem.domain.Person;
import com.moldovan.uni.bookingsystem.domain.Room;
import com.moldovan.uni.bookingsystem.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class InvoiceService {
    private final PriceCalculator priceCalculator;
    private final DiscountGenerator discountGenerator;
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(PriceCalculator priceCalculator, DiscountGenerator discountGenerator, InvoiceRepository invoiceRepository) {
        this.priceCalculator = priceCalculator;
        this.discountGenerator = discountGenerator;
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice registerInvoice(Person responsiblePerson, Set<Room> bookedRooms, Set<ExtraService> extraServices){
        Invoice invoice = Invoice.builder()
                .isPaid(false)
                .responsiblePerson(responsiblePerson)
                .price(priceCalculator.calculatePrice(bookedRooms, extraServices))
                .discount(discountGenerator.generateDiscount(bookedRooms.size()))
                .build();
        return invoiceRepository.save(invoice);
    }
}
