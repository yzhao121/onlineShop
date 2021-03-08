package com.ita.onlineshop.service;

import com.ita.onlineshop.dao.SalesOrderDao;
import com.ita.onlineshop.entity.SalesOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesOrderService {

    @Autowired
    private SalesOrderDao salesOrderDao;

    public void addSalesOrder(SalesOrder salesOrder) {
        salesOrderDao.addSalesOrder(salesOrder);
    }
}

