package com.neueda.muskaan1;

import com.neueda.muskaan1.dao.ITransactionRepo;
import com.neueda.muskaan1.dao.TransactionMongoTemplate;
import com.neueda.muskaan1.dto.*;
import com.neueda.muskaan1.entity.Transactions;
import com.neueda.muskaan1.exception.CustomerNotFound;
import com.neueda.muskaan1.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private ITransactionRepo transactionRepo;

    @Mock
    private TransactionMongoTemplate dao;

    @InjectMocks
    private TransactionService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMerchantAmount() {
        List<MerchantAmount> expected = Collections.singletonList(new MerchantAmount());
        when(dao.getAmountForMerchant()).thenReturn(expected);

        List<MerchantAmount> result = service.getMerchantAmount();

        assertEquals(expected, result);
        verify(dao, times(1)).getAmountForMerchant();
    }

    @Test
    void testGetSpendingHistoryByCategory() {
        List<CategoryAmount> expected = Collections.singletonList(new CategoryAmount());
        when(dao.getSpendingHistoryByCategory()).thenReturn(expected);

        List<CategoryAmount> result = service.getSpendingHistoryByCategory();

        assertEquals(expected, result);
        verify(dao, times(1)).getSpendingHistoryByCategory();
    }

    @Test
    void testGetSpendingHistoryByJob() {
        List<JobAmount> expected = Collections.singletonList(new JobAmount());
        when(dao.getSpendingHistoryByJob()).thenReturn(expected);

        List<JobAmount> result = service.getSpendingHistoryByJob();

        assertEquals(expected, result);
        verify(dao, times(1)).getSpendingHistoryByJob();
    }

    @Test
    void testGetSpendingHistoryByGender() {
        List<GenderAmount> expected = Collections.singletonList(new GenderAmount());
        when(dao.getSpendingHistoryByGender()).thenReturn(expected);

        List<GenderAmount> result = service.getSpendingHistoryByGender();

        assertEquals(expected, result);
        verify(dao, times(1)).getSpendingHistoryByGender();
    }

    @Test
    void testGetSpendingHistoryByCity() {
        List<CityAmount> expected = Collections.singletonList(new CityAmount());
        when(dao.getSpendingHistoryByCity()).thenReturn(expected);

        List<CityAmount> result = service.getSpendingHistoryByCity();

        assertEquals(expected, result);
        verify(dao, times(1)).getSpendingHistoryByCity();
    }

    @Test
    void testGetSpendingHistoryByState() {
        List<StateAmount> expected = Collections.singletonList(new StateAmount());
        when(dao.getSpendingHistoryByState()).thenReturn(expected);

        List<StateAmount> result = service.getSpendingHistoryByState();

        assertEquals(expected, result);
        verify(dao, times(1)).getSpendingHistoryByState();
    }

    @Test
    void testGetSpendingHistoryByAmount() {
        List<AmountSpending> expected = Collections.singletonList(new AmountSpending());
        when(dao.getSpendingHistoryByAmount()).thenReturn(expected);

        List<AmountSpending> result = service.getSpendingHistoryByAmount();

        assertEquals(expected, result);
        verify(dao, times(1)).getSpendingHistoryByAmount();
    }

    @Test
    void testGetTopMerchants() {
        List<TopMerchant> expected = Collections.singletonList(new TopMerchant());
        int limit = 10;
        when(dao.getTopMerchants(limit)).thenReturn(expected);

        List<TopMerchant> result = service.getTopMerchants(limit);

        assertEquals(expected, result);
        verify(dao, times(1)).getTopMerchants(limit);
    }



}
