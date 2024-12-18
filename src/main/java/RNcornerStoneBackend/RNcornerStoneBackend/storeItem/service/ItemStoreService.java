package RNcornerStoneBackend.RNcornerStoneBackend.storeItem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import RNcornerStoneBackend.RNcornerStoneBackend.storeItem.bo.BuyStoreItemEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.storeItem.bo.CreateStoreItemEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.storeItem.bo.StoreItemResponse;

@Service
public interface ItemStoreService {
    String addStoreItem(CreateStoreItemEntity request);

    String buyStoreItem(BuyStoreItemEntity request);

    List<StoreItemResponse> getCurrentChildStoreItems();
}
