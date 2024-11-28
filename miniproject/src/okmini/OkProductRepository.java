package okmini;

import java.util.*;

public class OkProductRepository {
    private Map<String, List<OkProduct>> cosmetics;

    public OkProductRepository() {
        cosmetics = new HashMap<>();
        initializeProducts();
    }

    private void initializeProducts() {
        cosmetics.put("클렌징폼", Arrays.asList(
                new OkProduct("딥클렌징폼", "브랜드A", 150, 10, "모든 피부 타입에 적합한 클렌징폼."),
                new OkProduct("수분클렌징폼", "브랜드B", 200, 5, "수분을 공급해주는 클렌징폼.")
        ));

        cosmetics.put("토너", Arrays.asList(
                new OkProduct("진정토너", "브랜드C", 300, 20, "민감한 피부에 적합한 진정 토너."),
                new OkProduct("미백토너", "브랜드D", 250, 15, "피부 톤을 밝게 해주는 미백 토너.")
        ));

        cosmetics.put("로션", Arrays.asList(
                new OkProduct("수분로션", "브랜드E", 200, 12, "건조한 피부에 수분을 제공."),
                new OkProduct("영양로션", "브랜드F", 250, 8, "피부에 영양을 공급.")
        ));
    }

    public Map<String, List<OkProduct>> getCosmetics() {
        return cosmetics;
    }
}
