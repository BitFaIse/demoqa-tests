package pages.components;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;

public class FileUploadComponent {

    public void fileUpload(String path) {
        $("#uploadPicture").uploadFile(new File(path));

    }
}
