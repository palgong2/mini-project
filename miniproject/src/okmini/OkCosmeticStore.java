package okmini;

import java.util.*;

public class OkCosmeticStore {
    private static OkProductRepository repository = new OkProductRepository();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== 화장품 가게 프로그램 ===");
            System.out.println("1. 화장품 종류 보기");
            System.out.println("3. 재고 변경 (관리자)");
            System.out.println("4. 종료");
            System.out.print("메뉴를 선택하세요: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            if (choice == 4) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (choice == 1) {
                showProductTypes(scanner);
            } else if (choice == 3) {
                handleStockManagement(scanner); // 재고 관리 처리
            } else {
                System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }

        scanner.close();
    }

    private static void showProductTypes(Scanner scanner) {
        Map<String, List<OkProduct>> cosmetics = repository.getCosmetics();

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
                System.out.println("\n=== 제품 상세 정보 ===");
                selectedProduct.printDetails();
            } else {
                System.out.println("입력한 제품을 찾을 수 없습니다.");
            }
        } else {
            System.out.println("입력한 화장품 종류가 없습니다.");
        }
    }

    private static OkProduct findProductByName(List<OkProduct> products, String productName) {
        for (OkProduct product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }

    // 재고 관리 (관리자 권한)
    private static void handleStockManagement(Scanner scanner) {
        if (!OkProductManager.checkAdminPassword(scanner)) {
            System.out.println("잘못된 비밀번호입니다.");
            return;
        }

        Map<String, List<OkProduct>> cosmetics = repository.getCosmetics();
        OkProductManager.manageStock(cosmetics, scanner); // 재고 관리 위임
    }
}