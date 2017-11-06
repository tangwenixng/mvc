package com.twx.entity;

import javax.persistence.*;

/**
 * Created by twx on 2017/11/2.
 */
@Entity
@Table(name = "t_svl")
public class SVLEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cameraId")
    private CameraEntity camera;

    private String startTime;
    private String endTime;

    private String requestId;
    private String requestTime;

    private String containerNo;
    private String examFieldCode;
    private String examLocationCode;
    private String statusCode;
    private String remark;
    private String thumbnail;
    private String videoUrl;

    public SVLEntity() {
    }

    public SVLEntity(String startTime, String endTime,
                     String containerNo, String examFieldCode,
                     String examLocationCode, String statusCode) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.containerNo = containerNo;
        this.examFieldCode = examFieldCode;
        this.examLocationCode = examLocationCode;
        this.statusCode = statusCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public CameraEntity getCamera() {
        return camera;
    }

    public void setCamera(CameraEntity camera) {
        this.camera = camera;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getContainerNo() {
        return containerNo;
    }

    public void setContainerNo(String containerNo) {
        this.containerNo = containerNo;
    }

    public String getExamFieldCode() {
        return examFieldCode;
    }

    public void setExamFieldCode(String examFieldCode) {
        this.examFieldCode = examFieldCode;
    }

    public String getExamLocationCode() {
        return examLocationCode;
    }

    public void setExamLocationCode(String examLocationCode) {
        this.examLocationCode = examLocationCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
