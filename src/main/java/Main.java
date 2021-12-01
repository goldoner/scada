import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws IOException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime now = LocalDateTime.now();

        String newFolderName = (System.currentTimeMillis()) + " [" + dtf.format(now) + "]";

        File mainDir = new File("/home/install/SCADA-ARCHIVE-FILES");
        File[] fileList = mainDir.listFiles();
        assert fileList != null;
        Arrays.sort(fileList);

        for (int i = 0; Objects.requireNonNull(mainDir.listFiles()).length > 30; i++) {
            if (fileList[i].isDirectory()) {
                FileUtils.deleteDirectory(fileList[i]);
            }
        }

        File file = new File("/home/install/SCADA-ARCHIVE-FILES" + "/" + newFolderName);
        file.mkdir();

        File copyFromDir = new File("/mnt/net2dg");

        for (File file1 : Objects.requireNonNull(copyFromDir.listFiles())) {
            if (file1.getName().contains("Test_Feld")) {
                continue;
            }
            FileUtils.copyFile(file1, new File(file.getAbsolutePath() + "/" + file1.getName()));
        }

    }


}
