import java.util.ArrayList;
import java.util.List;

public class GooglePhotosAutoUploader {
    private Object LOCK = new Object();
    private List<String> photosToUploadList = new ArrayList<>();
    public void startAutoUpload() {
        synchronized (LOCK) {
            if (photosToUploadList.isEmpty()) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
            uploadPhotos();
        }
    }
    public void onNewPhotoAdded(String photoPath) {
        synchronized (LOCK) {
            photosToUploadList.add(photoPath);
            LOCK.notify();
            Thread.sleep(1000);
        }

        public void uploadPhotos() {
            System.out.println("Начинаем загрузку...");
            photosToUploadList.forEach(photoToUpload -> System.out.println("Фото " + photoToUpload + " загружается..."));
            System.out.println("Загрузка завершена!");
        }
}
