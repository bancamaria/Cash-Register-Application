package service;

import model.Coupon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Set;
import java.util.TreeSet;

public final class CouponIOService {
    private static final String FILE_PATH = "src/data/Coupon.csv";
    private static final String FILE_HEADER = "id,firstName,lastName";
    private static CouponIOService instance;

    private CouponIOService() {
    }

    public static CouponIOService getInstance() {
        if (null == instance) {
            instance = new CouponIOService();
        }
        return instance;
    }

    public final Set<Coupon> loadCoupons() {
        final Set<Coupon> coupons = new TreeSet<>();
        BufferedReader fileReader = null;
        try {
            String line;
            fileReader = new BufferedReader(new FileReader(FILE_PATH));
            fileReader.readLine();
            while (null != (line = fileReader.readLine())) {
                final String[] fields = line.split("\\s*,\\s*");
                if (0 < fields.length)
                    coupons.add(new Coupon(Float.parseFloat(fields[1]), Boolean.parseBoolean(fields[2])));
            }
        } catch (final IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (null != fileReader)
                    fileReader.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        LogService.getInstance().log("Loaded coupons", new Timestamp(System.currentTimeMillis()));
        return coupons;
    }

    public void saveCoupons() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(FILE_PATH);
            fileWriter.write(FILE_HEADER + '\n');
            final Set<Coupon> coupons = CouponService.getInstance().getCoupons();
            for (final Coupon coupon : coupons) {
                fileWriter.append(String.valueOf(coupon.getId())).append(",");
                fileWriter.append(String.valueOf(coupon.getDiscount())).append(",");
                fileWriter.append(String.valueOf(coupon.isUsed())).append("\n");
            }
        } catch (final IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (null != fileWriter) {
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        LogService.getInstance().log("Saved coupons", new Timestamp(System.currentTimeMillis()));
    }
}