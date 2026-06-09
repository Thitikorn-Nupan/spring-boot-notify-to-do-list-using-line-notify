package com.ttknpdev.repository;

import java.util.LinkedHashMap;

public interface LineNotifyRepo {
    LinkedHashMap<String, Object> sendLineNotifyMessageAndSticker(String msg, int stickerPackageId, int stickerId) throws Exception;
}
