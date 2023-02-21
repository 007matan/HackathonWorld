package com.cohen.hackathonworld.Model;

public class StoredFiles {

    public static int NUMBER_OF_STOREDFILES = 0;

    public int storedFilesId = -1;
    public int creatorId = 0;
    public FILE_FORMAT fileFormat = null;
    public int views = 0;

    public StoredFiles(){

    }

    public StoredFiles withStoredFilesId(){
        this.storedFilesId = NUMBER_OF_STOREDFILES++;
        return this;
    }
    public StoredFiles withCreatorId(int creatorId){
        this.creatorId = creatorId;
        return this;
    }
    public StoredFiles withFileFormat(FILE_FORMAT fileFormat){
        this.fileFormat = fileFormat;
        return this;
    }

    public int getStoredFilesId() {
        return storedFilesId;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public FILE_FORMAT getFileFormat() {
        return fileFormat;
    }

    public int getViews() {
        return views;
    }

    public void addView(){
        this.views++;
    }
}
