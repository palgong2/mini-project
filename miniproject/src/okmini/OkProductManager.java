package okmini;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OkProductManager {
    private static final String ADMIN_PASSWORD = "1111"; // 관리자 비밀번호

    // 관리자 비밀번호 확인
    public static boolean checkAdminPassword(Scanner scanner) {
        System.out.print("관리자 비밀번호를 입력하세요: ");
        String password = scanner.nextLine();
        return ADMIN_PASSWORD.equals(password);
    }

    // 재고 변경
    public static void manageStock(Map<String, List<OkProduct>> cosmetics, Scanner scanner) {
        System.out.println("=== 화장품 종류 ===");
        for (String type : cosmetics.keySet()) {
            System.out.println(type);
        }

        System.out.print("화장품 종류를 입력하세요: ");
        String type = scanner.nextLine();

        if (cosmetics.containsKey(type)) {
            List<OkProduct> products = cosmetics.get(type);

            System.out.println("\n=== " + type + " 리스트 ===");
            for (int i = 0; i < products.size(); i++) {
                System.out.println((i + 1) + ". " + products.get(i).getName());
            }

            System.out.print("제품명을 입력하세요: ");
            String productName = scanner.nextLine();

            OkProduct selectedProduct = findProductByName(products, productName);

            if (selectedProduct != null) {
                System.out.print("변경할 재고 수량을 입력하세요 (현재 재고: " + selectedProduct.getQuantity() + "): ");
                int quantityChange = scanner.nextInt();
                scanner.nextLine(); // 버퍼 비우기

                // 재고가 음수가 되지 않도록 처리
                if (selectedProduct.getQuantity() + quantityChange < 0) {
                    System.out.println("재고는 음수가 될 수 없습니다.");
                } else {
                    selectedProduct.setQuantity(selectedProduct.getQuantity() + quantityChange);
                    System.out.println("재고가 " + quantityChange + "만큼 변경되었습니다.");
                    System.out.println("현재 재고는 " + selectedProduct.getQuantity() + "개 입니다");
                }
            } else {
                System.out.println("입력한 제품을 찾을 수 없습니다.");
            }
        } else {
            System.out.println("입력한 화장품 종류가 없습니다.");
        }
    }

    // 제품 이름으로 제품 찾기
    private static OkProduct findProductByName(List<OkProduct> products, String productName) {
        for (OkProduct product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }
}
