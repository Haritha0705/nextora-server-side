package lk.iit.nextora.module.lostandfound.service.impl;

import lk.iit.nextora.module.lostandfound.entity.ItemImage;
import lk.iit.nextora.module.lostandfound.repository.ItemImageRepository;
import lk.iit.nextora.module.lostandfound.service.ItemImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemImageServiceImpl implements ItemImageService {

    private final ItemImageRepository imageRepository;

    @Override
    public void saveImage(Long itemId, String imageUrl, boolean isLostItem) {
        ItemImage image = new ItemImage();
        image.setImageUrl(imageUrl);
        imageRepository.save(image);
    }
}
