package ec.yavirac.yavigestion.modules.core.services.fileuploader;

import org.springframework.stereotype.Service;

@Service
public interface FileUploader {
    public Object upload(byte[] file, String fileName);
}
