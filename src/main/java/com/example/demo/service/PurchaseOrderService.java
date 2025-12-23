package com.example.demo.service;

import com.example.demo.entity.PurchaseOrder;
import java.util.List;

public interface PurchaseOrderService {

    PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder);

    PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder purchaseOrder);

    PurchaseOrder getById(Long id);

    List<PurchaseOrder> getAll();

    List<PurchaseOrder> getOrdersBySupplier(Long supplierId);
}
