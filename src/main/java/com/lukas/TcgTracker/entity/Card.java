package com.lukas.TcgTracker.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lukas.TcgTracker.dto.ImageUri;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "cards")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Card {
    @Id
    private String id;
    @Field(name = "oracle_id")
    @JsonProperty("oracle_id")
    private String oracleId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("lang")
    private String lang;
    @Field(name = "released_at")
    @JsonProperty("released_at")
    private String releasedAt;
    @Field(name = "mana_cost")
    @JsonProperty("mana_cost")
    private String manaCost;
    @JsonProperty("cmc")
    private double cmc;
    @Field(name = "type_line")
    @JsonProperty("type_line")
    private String typeLine;
    @Field(name = "oracle_text")
    @JsonProperty("oracle_text")
    private String oracleText;
    @JsonProperty("power")
    private String power;
    @JsonProperty("toughness")
    private String toughness;
    @JsonProperty("colors")
    private List<String> colors;
    @Field(name = "color_identity")
    @JsonProperty("color_identity")
    private List<String> colorIdentity;
    @JsonProperty("set")
    private String set;
    @Field(name = "set_name")
    @JsonProperty("set_name")
    private String setName;
    @JsonProperty("rarity")
    private String rarity;
    @Field(name = "collector_number")
    @JsonProperty("collector_number")
    private String collectorNumber;
    @Field(name = "image_uris")
    @JsonProperty("image_uris")
    private List<ImageUri> image_uris;
}
