package main;
import model.*;
import service.*;
import repositories.*;
import service.BDService;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        /*// Declarations
        final CashierService cashierService = CashierService.getInstance();
        final RegisterService registerService = RegisterService.getInstance();
        final ProductCategoryService productCategoryService = ProductCategoryService.getInstance();
        final ProductService productService = ProductService.getInstance();
        final CouponService couponService = CouponService.getInstance();
        final ReceiptService receiptService = ReceiptService.getInstance();
        final SoldProductService soldProductService = SoldProductService.getInstance();

        final CashierIOService cashierIOService = CashierIOService.getInstance();
        final RegisterIOService registerIOService = RegisterIOService.getInstance();
        final ProductCategoryIOService productCategoryIOService = ProductCategoryIOService.getInstance();
        final ProductIOService productIOService = ProductIOService.getInstance();
        final CouponIOService couponIOService = CouponIOService.getInstance();
        final ReceiptIOService receiptIOService = ReceiptIOService.getInstance();
        final SoldProductIOService soldProductIOService = SoldProductIOService.getInstance();

        // Printing
        System.out.println(cashierService.getCashiers());
        System.out.println(registerService.getRegisters());
        System.out.println(productCategoryService.getProductCategories());
        System.out.println(productService.getProducts());
        System.out.println(couponService.getCoupons());
        System.out.println(receiptService.getReceipts());
        System.out.println(soldProductService.getSoldProducts());

        // Saving the data
        cashierIOService.saveCashiers();
        registerIOService.saveRegisters();
        productCategoryIOService.saveProductCategories();
        productIOService.saveProducts();
        couponIOService.saveCoupons();
        receiptIOService.saveReceipts();
        soldProductIOService.saveSoldProducts();*/


        CashierRepository cashierBDRepository = new CashierBDRepository;
        cashierBDRepository.add(c1);
        cashierBDRepository.add(c2);
        cashierBDRepository.add(c3);
        Cashier c4 = cashierBDRepository.getCashierById(123);
        Map<Integer, Cashier> mp = new HashMap<>();
        mp = cashierBDRepository();

        CouponRepository couponBDRepository = new CouponBDRepository;
        couponBDRepository.add(co1);
        couponBDRepository.add(co2);
        couponBDRepository.add(co3);
        Coupon co4 = couponBDRepository.getCouponById(25496);
        Map<Integer, Coupon> mp = new HashMap<>();
        mp = couponBDRepository();

        ProductRepository productBDRepository = new ProductRepository();
        productBDRepository.add(p1);
        productBDRepository.add(p2);
        productBDRepository.add(p3);
        Product p4 = productBDRepository.getProductById(808);
        Map<Integer, Product> mp = new HashMap<>();
        mp = productBDRepository();

        ProductCategoryRepository productCategoryBDRepository = new ProductCategoryRepository();
        productCategoryBDRepository.add(pc1);
        productCategoryBDRepository.add(pc2);
        productCategoryBDRepository.add(pc3);
        ProductCategory pc4 = productCategoryBDRepository.getProductCategoryById(3);
        Map<Integer, ProductCategory> mp = new HashMap<>();
        mp = productCategoryBDRepository();

        ReceiptRepository receiptBDRepository = new ReceiptBDRepository;
        receiptBDRepository.add(r1);
        receiptBDRepository.add(r2);
        receiptBDRepository.add(r3);
        Receipt r4 = receiptBDRepository.getReceiptById(1000);
        Map<Integer, Receipt> mp = new HashMap<>();
        mp = receiptBDRepository();

        RegisterRepository registerBDRepository = new RegisterBDRepository;
        registerBDRepository.add(re1);
        registerBDRepository.add(re2);
        registerBDRepository.add(re3);
        Register re4 = registerBDRepository.getCashierById(2);
        Map<Integer, Register> mp = new HashMap<>();
        mp = registerBDRepository();

        SoldProductRepository soldProductBDRepository = new SoldProductBDRepository;
        soldProductBDRepository.add(sp1);
        soldProductBDRepository.add(sp2);
        soldProductBDRepository.add(sp3);
        SoldProduct sp4 = soldProductBDRepository.getCashierById(5);
        Map<Integer, SoldProduct> mp = new HashMap<>();
        mp = soldProductBDRepository();
    }
}
