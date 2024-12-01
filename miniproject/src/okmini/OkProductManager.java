package okmini;

import java.util.*;

public class OkProductManager {
    private static final String ADMIN_PASSWORD = "1111";

    public static boolean checkAdminPassword(Scanner scanner) {
        System.out.print("관리자 비밀번호를 입력하세요: ");
        String password = scanner.nextLine();
        return ADMIN_PASSWORD.equals(password);
    }

    public static void manageStock(Map<String, Map<String, List<OkProduct>>> cosmetics, Scanner scanner) {
        System.out.println("=== 화장품 대분류 ===");
        for (String type : cosmetics.keySet()) {
            System.out.println(type);
        }

        System.out.print("화장품 대분류를 입력하세요: ");
        String type = scanner.nextLine();

        if (cosmetics.containsKey(type)) {
            Map<String, List<OkProduct>> subCategories = cosmetics.get(type);

            System.out.println("\n=== " + type + " 소분류 ===");
            for (String subType : subCategories.keySet()) {
                System.out.println("- " + subType);
            }

            System.out.print("소분류를 입력하세요: ");
            String subType = scanner.nextLine();

            if (subCategories.containsKey(subType)) {
                List<OkProduct> products = subCategories.get(subType);

                System.out.println("\n=== " + subType + " 제품 리스트 ===");
                for (int i = 0; i < products.size(); i++) {
                    System.out.println((i + 1) + ". " + products.get(i).getName());
                }

                System.out.print("제품명을 입력하세요: ");
                String productName = scanner.nextLine();

                OkProduct selectedProduct = findProductByName(products, productName);

                if (selectedProduct != null) {
                    System.out.print("변경할 재고 수량을 입력하세요 (현재 재고: " + selectedProduct.getQuantity() + "): ");
                    int quantityChange = scanner.nextInt();
                    scanner.nextLine();

                    if (selectedProduct.getQuantity() + quantityChange < 0) {
                        System.out.println("재고는 음수가 될 수 없습니다.");
                    } else {
                        selectedProduct.setQuantity(selectedProduct.getQuantity() + quantityChange);
                        System.out.println("재고가 " + quantityChange + "만큼 변경되었습니다.");
                        System.out.println("현재 재고는 " + selectedProduct.getQuantity() + "개 입니다.");
                    }
                } else {
                    System.out.println("입력한 제품을 찾을 수 없습니다.");
                }
            } else {
                System.out.println("입력한 소분류가 없습니다.");
            }
        } else {
            System.out.println("입력한 대분류가 없습니다.");
        }
    }

    public static OkProduct findProductByName(List<OkProduct> products, String productName) {
        for (OkProduct product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }
}
