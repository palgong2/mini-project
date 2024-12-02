package jeonmini;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class JeonCosmeticStore {
    private static JeonProductRepository repository = new JeonProductRepository();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== 화장품 가게 프로그램 ===");
            System.out.println("1. 화장품 종류 보기");
            System.out.println("0. 종료");
            System.out.print("메뉴를 선택하세요: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            if (choice == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (choice == 1) {
                showProductTypes(scanner);
            } else {
                System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }

        scanner.close();
    }

    private static void showProductTypes(Scanner scanner) {
        Map<String, List<JeonProduct>> cosmetics = repository.getCosmetics();

        System.out.println("=== 화장품 종류 ===");
        for (String type : cosmetics.keySet()) {
            System.out.println(type);
        }

        System.out.print("화장품 종류를 입력하세요: ");
        String type = scanner.nextLine();

        if (cosmetics.containsKey(type)) {
            List<JeonProduct> products = cosmetics.get(type);

            System.out.println("\n=== " + type + " 리스트 ===");
            for (int i = 0; i < products.size(); i++) {
                System.out.println((i + 1) + ". " + products.get(i).getName());
            }

            System.out.print("제품명을 입력하세요: ");
            String productName = scanner.nextLine();

            JeonProduct selectedProduct = findProductByName(products, productName);

            if (selectedProduct != null) {
                System.out.println("\n=== 제품 상세 정보 ===");
                selectedProduct.printDetails();
            } else {
                System.out.println("입력한 제품을 찾을 수 없습니다.");
            }
        } else {
            System.out.println("입력한 대분류가 없습니다.");
        }
    }
    
    private static void cosmeticsRecommendation(Scanner scanner) {

    	Set<String> skinTypes = new HashSet<>();

        Map<String, Map<String, List<JeonProduct>>> cosmetics = repository.getCosmetics();
        for (Map<String, List<JeonProduct>> subCategoryMap : cosmetics.values()) {
            for (List<JeonProduct> productList : subCategoryMap.values()) {
                for (JeonProduct product : productList) {
                    skinTypes.add(product.getSkinType());
                }
            }
        }

        System.out.println("다음 중 자신의 피부 타입을 입력해주세요:");
        for (String type : skinTypes) {
            System.out.println("- " + type);
        }

        System.out.print("피부 타입을 입력하세요: ");
        String userSkinType = scanner.nextLine();

        List<JeonProduct> recommendedProducts = new ArrayList<>();
//
        
        for (Map<String, List<JeonProduct>> subCategoryMap : cosmetics.values()) {
            for (List<JeonProduct> productList : subCategoryMap.values()) {
                for (JeonProduct product : productList) {
                    if (product.getSkinType().equalsIgnoreCase(userSkinType)) {
                        recommendedProducts.add(product);
                    }
                }
            }
        }

        // 추천 제품 출력
        if (recommendedProducts.isEmpty()) {
            System.out.println("해당 피부 타입에 맞는 추천 제품이 없습니다.");
        } else {
            System.out.println("\n=== 추천 제품 리스트 ===");
            for (JeonProduct product : recommendedProducts) {
                System.out.println("- " + product.getName());
            }
        }
    }

    private static JeonProduct findProductByName(List<JeonProduct> products, String productName) {
        for (JeonProduct product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }
}
