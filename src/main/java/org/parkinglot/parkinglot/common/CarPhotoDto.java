package org.parkinglot.parkinglot.common;

public class CarPhotoDto {
    private Long id;
    private String filename;
    private String fileType;
    private byte[] fileContent;

    public Long getId() {
        return id;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public CarPhotoDto(Long id, String filename, String fileType, byte[] fileContent) {
        this.id = id;
        this.filename = filename;
        this.fileType = fileType;
        this.fileContent = fileContent;
    }

}