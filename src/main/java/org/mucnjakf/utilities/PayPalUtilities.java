package org.mucnjakf.utilities;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.mucnjakf.model.Invoice;
import org.mucnjakf.model.Product;
import org.mucnjakf.model.User;

import java.util.ArrayList;
import java.util.List;

public class PayPalUtilities {

    private static final String CLIENT_ID = "AZicLFh9oeI2Vj-Tqp1RqdQjs7wvXbR-Mm5Qzq9XJk0uHcA6FDVG6zCsr6fNzWdY9Crb6jpjo2yNYKQ3";
    private static final String SECRET = "ELEL1hwSz1n49ojvDMFsBbOZmcnlw0WJylOpk7nxgNbxuZuwEaRbO9S45W0UMljdznYhQTwdpYa-pfxu";
    private static final String MODE = "sandbox";

    public String authorizePayment(Invoice invoice) {
        try {
            Payer payer = getPayerInformation(invoice.getUser());
            RedirectUrls redirectUrls = getRedirectUrls();
            List<Transaction> transactions = getTransactionInformation(invoice);

            Payment payment = new Payment();
            payment.setTransactions(transactions);
            payment.setRedirectUrls(redirectUrls);
            payment.setPayer(payer);
            payment.setIntent("authorize");

            APIContext apiContext = new APIContext(CLIENT_ID, SECRET, MODE);

            Payment approvedPayment = payment.create(apiContext);

            return getApprovalLink(approvedPayment);
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static Payer getPayerInformation(User user) {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setEmail(user.getEmail());

        payer.setPayerInfo(payerInfo);

        return payer;
    }

    private static RedirectUrls getRedirectUrls() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/OneShop/basket");
        redirectUrls.setReturnUrl("http://localhost:8080/OneShop/review-paypal-payment-user");

        return redirectUrls;
    }

    private static List<Transaction> getTransactionInformation(Invoice invoice) {
        Details details = new Details();

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(String.valueOf(invoice.getTotalPrice()));
        amount.setDetails(details);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("Transaction invoice ID: " + invoice.getId());

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();

        for (Product product : invoice.getProducts()) {
            Item item = new Item();
            item.setCurrency("USD");
            item.setName(product.getName());
            item.setPrice(String.valueOf(product.getPrice()));
            item.setCategory(product.getCategory().getName());

            items.add(item);
        }

        itemList.setItems(items);
        transaction.setItemList(itemList);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        return transactions;
    }

    private static String getApprovalLink(Payment payment) {
        List<Links> links = payment.getLinks();
        String approvalLink = null;

        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
                break;
            }
        }

        return approvalLink;
    }

    public Payment getPaymentDetails(String paymentId) {
        try {
            APIContext apiContext = new APIContext(CLIENT_ID, SECRET, MODE);
            return Payment.get(apiContext, paymentId);
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Payment executePayment(String paymentId, String payerId) {
        try {
            PaymentExecution paymentExecution = new PaymentExecution();
            paymentExecution.setPayerId(payerId);

            Payment payment = new Payment().setId(paymentId);

            APIContext apiContext = new APIContext(CLIENT_ID, SECRET, MODE);

            return payment.execute(apiContext, paymentExecution);
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            return null;
        }
    }
}
