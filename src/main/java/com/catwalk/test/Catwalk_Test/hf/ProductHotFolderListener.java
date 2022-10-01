package com.catwalk.test.Catwalk_Test.hf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.io.File;

@Slf4j
@Component
public class ProductHotFolderListener implements HotFolderListener {
    private final File hotfolder;

    public ProductHotFolderListener(HotFolderWatcher hotFolderWatcher) {
        this.hotfolder = hotFolderWatcher.addListener("hot/product" ,this);
    }

    public File getHotFolder(){
        return hotfolder;
    }

    @Override
    public void onChange(File file) {
        log.info("Process File {} Here ", file.getName());
    }
}
