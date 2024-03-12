package service;

import com.example.servicebankingoperations.model.entity.Client;
import com.example.servicebankingoperations.repositories.ClientRepository;
import com.example.servicebankingoperations.service.TransferBetweenFundsService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import java.sql.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransferBetweenFundsServiceTest {
    private ClientRepository repository = mock(ClientRepository.class);
    private TransferBetweenFundsService service = new TransferBetweenFundsService(repository);
    private static final Client sender = new Client();
    private static final Client recipient = new Client();

    static {
        sender.setId(UUID.fromString("6935EE31-8E07-4962-BAB6-DCBCF06FFB86"));
        sender.setLogin("user1");
        sender.setFullName("Petya");
        sender.setBirthDay(Date.valueOf("01.01.2023"));
        sender.setPassword("password123");
        sender.setAccountNumber("1234567890");
        sender.setStartDeposit(BigDecimal.valueOf(1000));
        sender.setBalance(BigDecimal.valueOf(1000));

        recipient.setId(UUID.fromString("850D655B-8A51-4A6B-8903-2C8A75C15355"));
        recipient.setLogin("user2");
        recipient.setFullName("Valera");
        recipient.setBirthDay(Date.valueOf("01.01.2022"));
        recipient.setPassword("password1234");
        recipient.setAccountNumber("012345");
        recipient.setStartDeposit(BigDecimal.valueOf(500));
        recipient.setBalance(BigDecimal.valueOf(500));
    }

    @Test
    public void testTransferMoney() {
        BigDecimal transferSum = BigDecimal.valueOf(500);

        when(repository.findClientByUUID(sender.getId())).thenReturn(sender);
        when(repository.findClientByUUID(recipient.getId())).thenReturn(recipient);

        service.transferMoney(sender.getId(), recipient.getId(), transferSum);

        assertEquals(sender.getBalance(), BigDecimal.valueOf(500));
        assertEquals(recipient.getBalance(), BigDecimal.valueOf(1500));
    }
}
