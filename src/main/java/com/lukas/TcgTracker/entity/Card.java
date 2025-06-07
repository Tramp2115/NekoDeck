package com.lukas.TcgTracker.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Document(collection = "cards")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Card {
    @Id
    private String id;

    private String name;

    @JsonProperty("oracle_text")
    private String description;

    @JsonProperty("type_line")
    private String type;

    private String power;

    private String toughness;

    @Field(name = "mana_cost")
    private String manaCost;

    @JsonProperty("lang")
    private String language;

    @Field(name = "released_at")
    private LocalDate releasedAt;

    @JsonProperty("cmc")
    private double cmc;

    private List<String> colors;

    @Field(name = "color_identity")
    private List<String> colorIdentity;

    @Field(name = "set_name")
    private String setName;

    private String rarity;

    @Field(name = "collector_number")
    private String collectorNumber;

    @Field(name = "image_uri")
    private String imageUri;

    @JsonProperty("image_uris")
    private void unpackImageUris(Map<String, String> imageUris) {
        this.imageUri = imageUris.get("normal");
    }
}
