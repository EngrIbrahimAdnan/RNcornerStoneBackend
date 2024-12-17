package RNcornerStoneBackend.RNcornerStoneBackend.storeItem.service;

import RNcornerStoneBackend.RNcornerStoneBackend.storeItem.bo.BuyStoreItemEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.storeItem.bo.CreateStoreItemEntity;
import org.springframework.stereotype.Service;

@Service
public interface ItemStoreService {
    String addStoreItem(CreateStoreItemEntity request);
    String buyStoreItem(BuyStoreItemEntity request);

}
