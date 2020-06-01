package com.katsueitlab.katsueserver.staff.Bucket;

public enum BucketName {

    STAFF_FACE("katsue-staff-pics-upload");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
