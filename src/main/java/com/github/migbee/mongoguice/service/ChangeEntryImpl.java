package com.github.migbee.mongoguice.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import org.mongojack.ObjectId;

import java.util.Date;

/**
 * Change entry representation
 */
public class ChangeEntryImpl {

    public static final String keyName = "_id";

    public ChangeEntryImpl(String version, String name, String author, Date timestamp, String changeLogClass, String changeSetMethodName, boolean isCritical) {
        this.version = version;
        this.name = name;
        this.author = author;
        this.timestamp = timestamp;
        this.changeLogClass = changeLogClass;
        this.changeSetMethodName = changeSetMethodName;
        this.isCritical = isCritical;
    }

    @ObjectId
    private String id;

    @JsonView
    private String version;

    @JsonView
    private String name;

    @JsonView
    private String author;

    @JsonView
    private Date timestamp;

    @JsonView
    private String changeLogClass;

    @JsonView
    private String changeSetMethodName;

    @JsonView
    private Boolean isCritical;

    public ChangeEntryImpl() {
        // Jackson deserialization
    }

    @JsonProperty(keyName)
    public String getId() {
        return id;
    }
    public void setId(String id) { this.id = id; }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    @JsonProperty("timestamp")
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("changeLogClass")
    public String getChangeLogClass() {
        return changeLogClass;
    }

    public void setChangeLogClass(String changeLogClass) {
        this.changeLogClass = changeLogClass;
    }

    @JsonProperty("changeSetMethodName")
    public String getChangeSetMethodName() {
        return changeSetMethodName;
    }

    public void setChangeSetMethodName(String changeSetMethodName) {
        this.changeSetMethodName = changeSetMethodName;
    }

    @JsonProperty("isCritical")
    public boolean getCritical() {
        return isCritical;
    }

    public void setCritical(Boolean critical) {
        isCritical = critical;
    }
}

