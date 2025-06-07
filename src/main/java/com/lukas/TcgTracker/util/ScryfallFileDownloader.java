package com.lukas.TcgTracker.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.lukas.TcgTracker.exception.NoSuchBulkTypeException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

@Component
public class ScryfallFileDownloader {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Set<String> bulkTypes = Set.of(
            "oracle_cards",
            "default_cards",
            "unique_artwork",
            "all_cards",
            "rulings"
    );

    /**
     * This method fetches the available uri from thales API, and then return desired one,
     * If the type isn't available in current type scope, method gonna throw NoSuchBulkTypeException
     * @return String downloadUri
     */

    private String getDownloadUri(String type) {
        if (!bulkTypes.contains(type)) {
            throw new NoSuchBulkTypeException(type);
        }

        String downloadUri = "";
        String BULK_URL = "https://api.scryfall.com/bulk-data";
        JsonNode jsonNode = restTemplate.getForObject(BULK_URL, JsonNode.class);
        if (jsonNode != null) {
            for (JsonNode node : jsonNode.get("data")) {
                if (type.equals(node.get("type").asText())) {
                    downloadUri = node.get("download_uri").asText();
                    break;
                }
            }
        }

        return downloadUri;
    }

    public void downloadJsonFile(String type,String destinationPath) {
        if(isFileExisting(destinationPath)) {
            return;
        }
        String downloadUri = getDownloadUri(type);
        byte[] fileBytes = restTemplate.getForObject(downloadUri, byte[].class);
        try (FileOutputStream out = new FileOutputStream(destinationPath)) {
            out.write(fileBytes);
            System.out.println("File saved to: " + destinationPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isFileExisting(String pathname) {
        File file = new File(pathname);
        return file.exists();
    }
}
