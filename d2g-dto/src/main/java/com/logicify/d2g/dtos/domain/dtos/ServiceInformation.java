package com.logicify.d2g.dtos.domain.dtos;

import com.logicify.d2g.exceptions.D2GBaseException;
import com.logicify.d2g.exceptions.NewD2GBaseExceptionCodes;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * Created by jadencorr on 03.03.17.
 */
public class ServiceInformation {

    private Integer errorCode;

    private String errorMessage;

    private long timestamp;

    private String nodeId;

    public ServiceInformation(){
        this.errorCode= NewD2GBaseExceptionCodes.ALL_CORRECT.getId();
        this.errorMessage= NewD2GBaseExceptionCodes.ALL_CORRECT.getErrorMessage();
        try {
            this.nodeId= String.valueOf(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            this.nodeId="Can not resolve server address";
        }
        this.timestamp=new Date().getTime()/1000;
    }

    public ServiceInformation(D2GBaseException e){
        this.errorCode=e.getErrorCode();
        this.errorMessage=e.getErrorMessage();
        try {
            this.nodeId= String.valueOf(InetAddress.getLocalHost());
        } catch (UnknownHostException error) {
            this.nodeId="Can not resolve server address";
        }
        this.timestamp=new Date().getTime()/1000;
    }

    public ServiceInformation(Exception e){
        this.errorCode=NewD2GBaseExceptionCodes.SOMETHING_WENT_WRONG.getId();
        this.errorMessage=NewD2GBaseExceptionCodes.SOMETHING_WENT_WRONG.getErrorMessage();
        try {
            this.nodeId= String.valueOf(InetAddress.getLocalHost());
        } catch (UnknownHostException error) {
            this.nodeId="Can not resolve server address";
        }
        this.timestamp=new Date().getTime()/1000;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}
