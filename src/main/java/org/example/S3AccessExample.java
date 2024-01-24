package org.example;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Object;
public class S3AccessExample {

    public static void main(String[] args) {
        // Replace these values with your AWS S3 credentials and bucket information
        String accessKey = "AKIA4MTWJIJLJ7RBLUMR";
        String secretKey = "ZMqUU1psIpLiNzo3yBlm+aE8LSrsb84dxihnW94U";
        String region = "us-east-2";
        String bucketName = "stories-pictures";
        String objectKey = "Prajyot_Karnik_resume.pdf";

        // Create AWS credentials
        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);

        // Create S3 client
        S3Client s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(() -> credentials)
                .build();

        // Example: List objects in the bucket
        listObjects(s3Client, bucketName);

        // Example: Get metadata of a specific object


        // Close the S3 client
        s3Client.close();
    }

    private static void listObjects(S3Client s3Client, String bucketName) {
        ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .build();

        ListObjectsV2Response listObjectsResponse = s3Client.listObjectsV2(listObjectsRequest);

        for (S3Object content : listObjectsResponse.contents()) {
            System.out.println("Object key: " + content.key() + ", Size: " + content.size());
            getObjectMetadata(s3Client, bucketName, content.key());
        }


    }

    private static void getObjectMetadata(S3Client s3Client, String bucketName, String objectKey) {
        HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();

        HeadObjectResponse headObjectResponse = s3Client.headObject(headObjectRequest);

        System.out.println("Metadata for object " + objectKey + ":");
        System.out.println("Content-Type: " + headObjectResponse.contentType());
        System.out.println("Last-Modified: " + headObjectResponse.lastModified());
        // Add other metadata properties as needed
    }


}